package com.example.chapter5_5



import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.setFragmentResultListener
import java.util.*


class ResultFragment : Fragment() {

    lateinit var dbManager: DBManager
    lateinit var sqlitedb: SQLiteDatabase
    lateinit var mainActivity: MainActivity
    lateinit var friendItem: TextView
    lateinit var productName: TextView
    lateinit var giftImg:ImageView
    lateinit var price: TextView
    //lateinit var taker_info_first_name_edt:EditText
    private var result: String? = null    //전역변수로 사용


    override fun onAttach(context: Context) {
        super.onAttach(context)

        // 2. Context를 액티비티로 형변환해서 할당
        mainActivity = context as MainActivity
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setFragmentResultListener("requestKey") { requestKey, bundle ->
//            val result = bundle.getString("bundleKey")
//            // Do something with the result
//        }
        arguments?.let {
            result = it.getString("result")    //데이터 수신
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        val view: View = inflater.inflate(R.layout.fragment_result, container, false)


//        var result = arrayOfNulls<String>(5)
//        setFragmentResultListener("requestKey") { requestKey, bundle ->
//             result = bundle.getStringArray("bundleKey") as Array<String?>
//            // Do something with the result
//        }
       // var answer:String
//        setFragmentResultListener("request") { key, bundle ->
////            bundle.getString("senderKey")?.let { value ->
////                binding.textView.text = value
//            var answer = bundle.getString("senderKey")
//            }


        //answer로 스위치문작성 -> 결과지 세팅    할 예정

        dbManager = DBManager(mainActivity,"my",null,1)
        friendItem = view.findViewById(R.id.friendItem)
        productName = view.findViewById(R.id. productName)
        price = view.findViewById(R.id.price)
        giftImg=view.findViewById(R.id.coverImg)
       // taker_info_first_name_edt =view.findViewById(R.id.taker_info_first_name_edt)
       // var myId:String="qqq"
        //임의로 사용자 아이디 설정
        var myId:String="qqq"
        var friendItemTemp:String =friendItem.text.toString()
        var productNameTemp:String =productName.text.toString()
        var priceTemp:String =price.text.toString()
        var imgTemp:Int=R.drawable.wonder_visitor_ball_cap
        var imgTemp2:Int=R.drawable.product_list_cup_img

        sqlitedb =dbManager.writableDatabase

        sqlitedb.execSQL("INSERT INTO my VALUES('"+myId+"','"+friendItemTemp+"','"+productNameTemp+"','"+priceTemp+"',"+imgTemp+");")
        sqlitedb.execSQL("INSERT INTO my VALUES('"+myId+"','"+friendItemTemp+"','"+productNameTemp+"','"+priceTemp+"',"+imgTemp2+");")
        sqlitedb.execSQL("INSERT INTO my VALUES('"+productNameTemp+"','"+friendItemTemp+"','"+productNameTemp+"','"+priceTemp+"',"+imgTemp+");")

        sqlitedb.close()


      //  title="테스트입니다"
      price.text=result.toString()

        giftImg.setImageResource(imgTemp!!)
        return  view
    }


}