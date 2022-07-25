package com.example.chapter5_5

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chapter5_5.databinding.ItemHomeCategoryProductBinding
import java.util.*

class ProductRVAdapter(private val productList: ArrayList<Product>) :
    RecyclerView.Adapter<ProductRVAdapter.ViewHolder>() {

    interface MyItemClickListener {
        fun onItemClick(product: Product)
    }

    private lateinit var mItemClickListener: MyItemClickListener
    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    fun addItem(product: Product) {
        productList.add(product)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ProductRVAdapter.ViewHolder {
        val binding: ItemHomeCategoryProductBinding =
            ItemHomeCategoryProductBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductRVAdapter.ViewHolder, position: Int) {
        holder.bind(productList[position])
        holder.itemView.setOnClickListener { mItemClickListener.onItemClick(productList[position]) }
    }

    override fun getItemCount(): Int = productList.size

    inner class ViewHolder(val binding: ItemHomeCategoryProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.itemCompanyTv.text = product.company
            binding.itemProductNameTv.text = product.name
            binding.itemProductPriceTv.text = product.price
            binding.itemProductImgIv.setImageResource(product.coverImg!!)
        }
    }

}