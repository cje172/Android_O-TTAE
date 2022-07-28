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

        //결과지 세팅할 것들
        friendItem = view.findViewById(R.id.friendItem)
        productName = view.findViewById(R.id. productName)
        price = view.findViewById(R.id.price)
        giftImg=view.findViewById(R.id.coverImg)

        //친구 이름 임의로 설정, 나중에 가져오기
        var friendName:String="혜온"
       // answer로 스위치문작성 -> 결과지 세팅    할 예정
        when (result) {
//            1. I ,집. 허약, 보부상, 한식 -> 보드게임
//            10100
            "10100" -> {
                //보드게임
                friendItem.text="집에서 혼자 잘 노는 "//+friendName+" 님을 위해\n"+"보드게임 어때?"
                productName.text="할리갈리"//임의
                price.text="13,000"//임의
                giftImg.setImageResource(R.drawable.product_list_perfume_img)//임의
            }

//                2.E,밖,운동,보부상,양식 -> 운동인을 위한 운동가방
//            01001
            "01001" -> {
                //운동가방
                friendItem.text="보부상 운동인 "+friendName+" 님을 위해\n"+"운동가방 어때?"
                productName.text="나이키 운동가방"//임의
                price.text="34,000"//임의
                giftImg.setImageResource(R.drawable.product_list_perfume_img)//임의
            }
//                3.I,집, 운동,미니멀, 양식 -> 단백질을 챙기고 집에만 있는 운동인을 위한 치킨 (배민 쿠폰)
//            10011
            "10011" -> {
                //치킨 기프티콘콘
                //friendItem.text="단백질이 중요한 내향형 운동인 "+friendName+" 님을 위해\n"+"치킨 기프티콘 어때?"
                productName.text="푸라닭 악마치킨"//임의
                price.text="21,000"//임의
                giftImg.setImageResource(R.drawable.product_list_perfume_img)//임의
           }
//                4.E,밖,허약,미니멀,한식-> 밖에 돌아다니는 걸 좋아하지만 허약한 친구를 위한 비타민
//                01110
            "01110" -> {
                //비타민
              //  friendItem.text="밖에 돌아다니는 걸 좋아하지만 허약한 "+friendName+" 님을 위해\n"+"비타민 어때?"
                productName.text="비타민 C"//임의
                price.text="21,000"//임의
                giftImg.setImageResource(R.drawable.product_list_perfume_img)//임의

            }

//            5.E,밖,허약,보부상,양식-> 밖을 잘 돌아다니는 친구를 위한 보조배터리
//                01101
            "01101" -> {
                //보조배터리
               // friendItem.text="밖을 잘 돌아다니는 보부상 "+friendName+" 님을 위해\n"+"보조배터리  어때?"
                productName.text="카카오 보조배터리"//임의
                price.text="23,000"//임의
                giftImg.setImageResource(R.drawable.product_list_perfume_img)//임의

            }

//            6.E,밖,운동인,보부상, 한식-> 외출을 자주하는 친구를 위한 계절밥상 기프티콘
//                01000
            "01000" -> {
                //빕스
                //friendItem.text="외출을 자주하는 한식파 "+friendName+" 님을 위해\n"+"계절밥상 상품권 어때?"
                productName.text="계절밥상"//임의
                price.text="21,000"//임의
                giftImg.setImageResource(R.drawable.product_list_perfume_img)//임의

            }
//            7.I,집,운동,미니멀,한식 -> I이지만 운동을 좋아하는 친구를 위한 운동기구 (덤벨)
//            10010
            "10010" -> {
                //덤벨
               // friendItem.text="운동을 좋아하는 내향인 "+friendName+" 님을 위해\n"+"덤벨 어때?"
                productName.text="덤벨"//임의
                price.text="41,000"//임의
                giftImg.setImageResource(R.drawable.product_list_perfume_img)//임의

            }

//                8.I,집,허약,미니멀,양식 -> 집에서 노는 친구를 위한 미니멀한 찻잔 세트(그릇세트) 양식메뉴에도 어울려!
//        10111
            "10111" -> {
                //찻잔세트
             //   friendItem.text="미니멀리스트 내향인 "+friendName+" 님을 위해\n"+"찻잔세트 어때?"
                productName.text="찻잔세트"//임의
                price.text="11,000"//임의
                giftImg.setImageResource(R.drawable.product_list_perfume_img)//임의
            }


            else -> {
                //아무거나 넣기
              //  friendItem.text="미니멀리스트 내향인 "+friendName+" 님을 위해\n"+"찻잔세트 어때?"
                productName.text="설정 X"//임의
                price.text="11,000"//임의
                giftImg.setImageResource(R.drawable.product_list_perfume_img)//임의
            }
        }


        dbManager = DBManager(mainActivity,"my",null,1)
//        friendItem = view.findViewById(R.id.friendItem)
//        productName = view.findViewById(R.id. productName)
//        price = view.findViewById(R.id.price)
//        giftImg=view.findViewById(R.id.coverImg)
      //  taker_info_first_name_edt =view.findViewById(R.id.taker_info_first_name_edt)
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