package com.example.o_ttae.ui.gift.question

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.o_ttae.data.db.DBManager
import com.example.o_ttae.ui.MainActivity
import com.example.o_ttae.R

class ResultFragment : Fragment() {

    lateinit var dbManager: DBManager
    lateinit var sqlitedb: SQLiteDatabase
    lateinit var mainActivity: MainActivity
    lateinit var friendItem: TextView
    lateinit var productName: TextView
    lateinit var giftImg: ImageView
    lateinit var price: TextView
    lateinit var saveResultBtn: ImageButton
    lateinit var againButton: ImageButton
    lateinit var resultItemBg: ImageView
    lateinit var resultItemIntro: TextView

    lateinit var resultImg: ImageView
    private var result: String? = null
    private var myId: String? = null
    private var friendName: String? = null
    private var imgTemp: Int? = null

    private var friendItemComment: String = ""

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            // QuestionFragment에서 검사한 결과를 받아오기
            result = it.getString("result")  // 데이터 수신
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_result, container, false)

        // 결과지에 세팅할 것들
        friendItem = view.findViewById(R.id.friend_item)
        productName = view.findViewById(R.id.product_name)
        price = view.findViewById(R.id.gift_price)
        giftImg = view.findViewById(R.id.cover_img)
        resultImg = view.findViewById(R.id.result_img)
        resultItemBg = view.findViewById(R.id.result_item_bg)
        resultItemIntro = view.findViewById(R.id.result_item_intro)
        saveResultBtn = view.findViewById(R.id.save_result_btn)

        val transaction = (context as MainActivity).supportFragmentManager.beginTransaction()

        // 다시 검사하기 버튼
        againButton = view.findViewById(R.id.again_btn)
        againButton.setOnClickListener {
            transaction.replace(R.id.main_frm, TakerInfoFirstFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        // result로 조건문 작성 -> 결과지 세팅
        when (result) {
            // 1. I, 집, 허약, 보부상, 한식 -> 보드게임
            // 10100
            "10100" -> {
                loadData()

                // 보드게임
                friendItem.text = "집에서 혼자 잘 노는 " + friendName + " 님을 위해\n" + "보드게임 어때?"
                productName.text = "상프앙 / ARCHI MEMORY CARD GAME"
                price.text = "13,000"
                imgTemp = R.drawable.result_board_game1_img
                resultImg.setImageResource(R.drawable.result_board_game2_img)
                giftImg.setImageResource(R.drawable.result_board_game1_img)
                friendItemComment = friendName + " 님을 위한 보드게임"
                resultItemBg.setImageResource(R.drawable.result_board_game3_img)
                resultItemIntro.text = getString(R.string.result_item_board_game_intro)
            }

            // 2. E, 밖, 운동, 보부상, 양식 -> 운동인을 위한 운동가방
            // 01001
            "01001" -> {
                loadData()

                // 운동가방
                friendItem.text = "보부상 운동인 " + friendName + " 님을 위해\n" + "운동가방 어때?"
                productName.text = "시랜드 / Hero_Black"
                price.text = "34,000"
                imgTemp = R.drawable.result_sports_bag1_img
                resultImg.setImageResource(R.drawable.result_sports_bag2_img)
                giftImg.setImageResource(R.drawable.result_sports_bag1_img)
                friendItemComment = friendName + " 님을 위한 운동가방"
                resultItemBg.setImageResource(R.drawable.result_sports_bag3_img)
                resultItemIntro.text = getString(R.string.result_item_sports_bag_intro)
            }

            // 3. I, 집, 운동, 미니멀, 양식 -> 단백질을 챙기고 집에만 있는 운동인을 위한 치킨 (배민 쿠폰)
            // 10011
            "10011" -> {
                loadData()

                // 치킨 기프티콘
                friendItem.text = "단백질이 중요한 내향형 운동인 " + friendName + " 님을 위해\n" + "치킨 기프티콘 어때?"
                productName.text = "bhc 치킨 "
                price.text = "21,000"
                imgTemp = R.drawable.result_chicken1_img
                resultImg.setImageResource(R.drawable.result_chicken2_img)
                giftImg.setImageResource(R.drawable.result_chicken1_img)
                friendItemComment = friendName + " 님을 위한 치킨 기프티콘"
                resultItemBg.setImageResource(R.drawable.result_chicken3_img)
                resultItemIntro.text = getString(R.string.result_item_chicken_intro)
            }

            // 4. E, 밖, 허약, 미니멀, 한식 -> 밖에 돌아다니는 걸 좋아하지만 허약한 친구를 위한 비타민
            // 01110
            "01110" -> {
                loadData()

                // 비타민
                friendItem.text = "밖에 돌아다니는 걸 좋아하지만 허약한\n" + friendName + " 님을 위해 비타민 어때?"
                productName.text = "바이너랩 / 글로시 30포 레모나맛"
                price.text = "21,000"
                imgTemp = R.drawable.result_vitamin1_img
                resultImg.setImageResource(R.drawable.result_vitamin2_img)
                giftImg.setImageResource(R.drawable.result_vitamin1_img)
                friendItemComment = friendName + " 님을 위한 비타민"
                resultItemBg.setImageResource(R.drawable.result_vitamin3_img)
                resultItemIntro.text = getString(R.string.result_item_vitamin_intro)
            }

            // 5. E, 밖, 허약, 보부상, 양식 -> 밖을 잘 돌아다니는 친구를 위한 보조배터리
            // 01101
            "01101" -> {
                loadData()

                // 보조배터리
                friendItem.text = "밖을 잘 돌아다니는 보부상 " + friendName + " 님을 위해\n" + "보조배터리  어때?"
                productName.text = "어프어프 / CLEANER PUNI-BLACK"
                price.text = "13,000"
                imgTemp = R.drawable.result_battery1_img
                resultImg.setImageResource(R.drawable.result_battery2_img)
                giftImg.setImageResource(R.drawable.result_battery1_img)
                friendItemComment = friendName + " 님을 위한 보조배터리"
                resultItemBg.setImageResource(R.drawable.result_battery3_img)
                resultItemIntro.text = getString(R.string.result_item_battery_intro)
            }

            // 6. E, 밖, 운동인, 보부상, 한식 -> 외출을 자주하는 친구를 위한 계절밥상 기프티콘
            // 01000
            "01000" -> {
                loadData()

                // 계절밥상
                friendItem.text = "외출을 자주 하는 한식파 " + friendName + " 님을 위해\n" + "계절밥상 상품권 어때?"
                productName.text = "계절밥상 상품권"
                price.text = "21,000"
                imgTemp = R.drawable.result_food1_img
                resultImg.setImageResource(R.drawable.result_food2_img)
                giftImg.setImageResource(R.drawable.result_food1_img)
                friendItemComment = friendName + " 님을 위한 계절밥상 상품권"
                resultItemBg.setImageResource(R.drawable.result_food3_img)
                resultItemIntro.text = getString(R.string.result_item_food_intro)
            }

            // 7. I, 집, 운동, 미니멀, 한식 -> I이지만 운동을 좋아하는 친구를 위한 운동기구 (덤벨)
            // 10010
            "10010" -> {
                loadData()

                // 덤벨
                friendItem.text = "운동을 좋아하는 내향인 " + friendName + " 님을 위해\n" + "덤벨 어때?"
                productName.text = "와이벨 / Y-Bell 와이벨 XS (4.5kg)"
                imgTemp = R.drawable.result_fitness1_img
                price.text = "31,000"
                resultImg.setImageResource(R.drawable.result_fitness2_img)
                giftImg.setImageResource(R.drawable.result_fitness1_img)
                friendItemComment = friendName + " 님을 위한 덤벨"
                resultItemBg.setImageResource(R.drawable.result_fitness3_img)
                resultItemIntro.text = getString(R.string.result_item_fitness_intro)
            }

            // 8. I, 집, 허약, 미니멀, 양식 -> 집에서 노는 친구를 위한 미니멀한 찻잔 세트(그릇세트) 양식메뉴에도 어울려!
            // 10111
            "10111" -> {
                loadData()

                // 찻잔세트
                friendItem.text = "미니멀리스트 내향인 " + friendName + " 님을 위해\n" + "찻잔세트 어때?"
                productName.text = "1537 / 컵 앤 소서 - 커피잔 세트 (3종)"
                price.text = "21,000"
                imgTemp = R.drawable.result_teaset1_img
                resultImg.setImageResource(R.drawable.result_teaset2_img)
                giftImg.setImageResource(R.drawable.result_teaset1_img)
                friendItemComment = friendName + " 님을 위한 찻잔세트"
                resultItemBg.setImageResource(R.drawable.result_teaset3_img)
                resultItemIntro.text = getString(R.string.result_item_teaset_intro)
            }

            else -> {
                loadData()

                // 아무거나 넣기
                friendItem.text = "미니멀리스트 내향인 " + friendName + " 님을 위해\n" + "찻잔세트 어때?"
                productName.text = "설정 X"
                price.text = "21,000"
                imgTemp = R.drawable.result_teaset1_img
                resultImg.setImageResource(R.drawable.result_teaset2_img)
                giftImg.setImageResource(R.drawable.result_teaset1_img)
                friendItemComment = friendName + " 님을 위한 찻잔세트"
                resultItemBg.setImageResource(R.drawable.result_teaset3_img)
                resultItemIntro.text = getString(R.string.result_item_teaset_intro)
            }
        }


        //결과 저장하기 버튼
        saveResultBtn.setOnClickListener {
            dbManager = DBManager(mainActivity, "my", null, 1)
            sqlitedb = dbManager.writableDatabase
            saveResult()
            sqlitedb.close()
        }

        return view
    }

    // 저장 버튼 누르면 호출되는 함수
    private fun saveResult() {
        var productNameTemp: String = productName.text.toString()
        var priceTemp: String = price.text.toString()

        loadUserName()
        // 테이블에 검사 결과 저장
        sqlitedb.execSQL("INSERT INTO my VALUES('" + myId.toString() + "','" + friendItemComment + "','" + productNameTemp + "','" + priceTemp + "'," + imgTemp + ");")
    }

    // 추천받을 친구 이름 가져오는 함수
    private fun loadData() {
        var pref = this.activity?.getPreferences(0)
        var name = pref?.getString("friendName", "")

        friendName = name
    }

    // 현재 검사를 진행하고 이름 user아이디 가져오는 함수
    private fun loadUserName() {
        var pref = this.activity?.getSharedPreferences("name", 0)
        myId = pref?.getString("name", "0")
    }
}