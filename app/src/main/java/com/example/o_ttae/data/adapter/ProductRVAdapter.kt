package com.example.o_ttae.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.o_ttae.data.model.Product
import com.example.o_ttae.databinding.ItemHomeCategoryProductBinding
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
    ): ViewHolder {
        val binding: ItemHomeCategoryProductBinding =
            ItemHomeCategoryProductBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(productList[position])
        holder.itemView.setOnClickListener { mItemClickListener.onItemClick(productList[position]) }
    }

    override fun getItemCount(): Int = productList.size

    inner class ViewHolder(private val binding: ItemHomeCategoryProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.itemCompanyTv.text = product.company
            binding.itemProductNameTv.text = product.name
            binding.itemProductPriceTv.text = product.price
            binding.itemProductImgIv.setImageResource(product.coverImg!!)
        }
    }
}