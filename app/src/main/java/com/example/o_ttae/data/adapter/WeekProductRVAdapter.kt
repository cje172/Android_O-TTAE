package com.example.o_ttae.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.o_ttae.data.model.WeekProduct
import com.example.o_ttae.databinding.ItemWeekProductBinding
import java.util.ArrayList

class WeekProductRVAdapter(private val weekProductList: ArrayList<WeekProduct>) :
    RecyclerView.Adapter<WeekProductRVAdapter.ViewHolder>() {

    interface MyItemClickListener {
        fun onItemClick(weekProduct: WeekProduct)
    }

    private lateinit var mItemClickListener: MyItemClickListener
    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    fun addItem(weekProduct: WeekProduct) {
        weekProductList.add(weekProduct)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding: ItemWeekProductBinding =
            ItemWeekProductBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(weekProductList[position])
        holder.itemView.setOnClickListener { mItemClickListener.onItemClick(weekProductList[position]) }
    }

    override fun getItemCount(): Int = weekProductList.size

    inner class ViewHolder(private val binding: ItemWeekProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(weekProduct: WeekProduct) {
            binding.itemWeekProductCaptionTv.text = weekProduct.caption
            binding.itemWeekProductNameTv.text = weekProduct.name
            binding.itemWeekProductImgIv.setImageResource(weekProduct.coverImg!!)
        }
    }

}