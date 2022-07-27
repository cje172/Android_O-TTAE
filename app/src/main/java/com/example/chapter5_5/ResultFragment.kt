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




class ResultFragment : Fragment() {

    lateinit var dbManager: DBManager
    lateinit var sqlitedb: SQLiteDatabase
    lateinit var mainActivity: MainActivity
    lateinit var friendItem: TextView
    lateinit var productName: TextView
    lateinit var giftImg:ImageView
    lateinit var price: TextView


    override fun onAttach(context: Context) {
        super.onAttach(context)

        // 2. Context를 액티비티로 형변환해서 할당
        mainActivity = context as MainActivity
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment



        val view: View = inflater.inflate(R.layout.fragment_result, container, false)
        dbManager = DBManager(mainActivity,"myDB",null,1)
        friendItem = view.findViewById(R.id.friendItem)
        productName = view.findViewById(R.id. productName)
        price = view.findViewById(R.id.price)
        giftImg=view.findViewById(R.id.coverImg)

       // var myId:String="qqq"
        var friendItemTemp:String =friendItem.text.toString()
        var productNameTemp:String =productName.text.toString()
        var priceTemp:String =price.text.toString()

        sqlitedb =dbManager.writableDatabase

        sqlitedb.execSQL("INSERT INTO myDB VALUES('"+friendItemTemp+"','"+productNameTemp+"','"+priceTemp+"','"+priceTemp+"');")

        sqlitedb.close()

        giftImg.setImageResource(R.drawable.wonder_visitor_ball_cap)
        return  view
    }


}