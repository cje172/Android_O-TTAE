package com.example.o_ttae.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.o_ttae.data.model.MyGiftList
import com.example.o_ttae.databinding.ItemMyRecyclerBinding
import java.util.*

class MyGiftListAdapter(private val giftList: ArrayList<MyGiftList>) :
    RecyclerView.Adapter<MyGiftListAdapter.ViewHolder>() {

    interface MyItemClickListener {
        fun onItemClick(giftList: MyGiftList)
    }

    private lateinit var mGiftClickListener: MyItemClickListener
    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        mGiftClickListener = itemClickListener
    }

    fun addItem(myGiftList: MyGiftList) {
        giftList.add(myGiftList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding: ItemMyRecyclerBinding =
            ItemMyRecyclerBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(giftList[position])
        holder.itemView.setOnClickListener { mGiftClickListener.onItemClick(giftList[position]) }
    }

    override fun getItemCount(): Int = giftList.size

    inner class ViewHolder(private val binding: ItemMyRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(giftList: MyGiftList) {
            binding.giftCategory.text = giftList.friendItem
            binding.giftName.text = giftList.productName
            binding.giftPrice.text = giftList.price
            binding.giftPhoto.setImageResource(giftList.coverImg!!)
        }
    }
}