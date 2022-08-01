package com.example.o_ttae.ui.product.price

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.o_ttae.data.model.Product
import com.example.o_ttae.data.adapter.ProductRVAdapter
import com.example.o_ttae.R
import java.util.ArrayList

class PriceOneFragment : Fragment() {

    private var productData = ArrayList<Product>()
    lateinit var priceOneProductRv: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_price, container, false)

        priceOneProductRv = view.findViewById(R.id.price_product_rv)

        productData.apply {
            add(Product("임페리얼", "데니쉬버터쿠키셀렉션 500g(대용량)", "9,900", R.drawable.product_list_snack_img))
            add(Product("클럽클리오", "페리페라 잉크 더 에어리벨벳 물복딱복", "7,200", R.drawable.product_list_tint_img))
            add(Product("solful", "내열 이중유리컵 동물컵 글라스 280ml", "8,500", R.drawable.product_list_animal_cup_img))
            add(Product("EGOIDSM", "미니 빔프로젝터 무드 세트", "9,800", R.drawable.product_list_beam_img))
        }

        // 어댑터와 데이터 리스트 연결
        val priceAllProductRVAdapter = ProductRVAdapter(productData)
        priceOneProductRv.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )
        priceOneProductRv.adapter = priceAllProductRVAdapter

        return view
    }
}