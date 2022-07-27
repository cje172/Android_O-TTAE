package com.example.chapter5_5


import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.*

class DBManager(
    context: Context,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("CREATE TABLE user (name text,email text,password text)")
        db!!.execSQL("CREATE TABLE my (user text,friendName text, itemName text,price text,coverImg Int)")




    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
    //
    //insert 메소드
    fun insertGift(gift:MyGiftList){
        val values = ContentValues()
        //넘겨줄 컬럼의 매개변수 지정
        values.put("friendItem",gift.friendItem)
        values.put("productName",gift.productName)
        values.put("price",gift.price)
        values.put("coverImg",gift.coverImg)


        //쓰기나 수정이 가능한 데이터베이스 변수
        val wd = writableDatabase
        wd.insert("my",null,values)
        //사용이 끝나면 반드시 close()를 사용하여 메모리누수 가 되지않도록 합시다.
        wd.close()
    }


    //select 메소드
    fun selectProDuct(): ArrayList<MyGiftList>{//MutableList<Memo>{
        val list = ArrayList<MyGiftList>()
        //전체조회
        val selectMy = "SELECT * FROM my WHERE friendItem ='ooo님을 위한 패션 아이템'"
        //"SELECT * FROM my WHERE friendItem ='ooo님을 위한 패션 아이템'"
        //읽기전용 데이터베이스 변수
        val rd = readableDatabase
        //데이터를 받아 줍니다.
        val cursor = rd.rawQuery(selectMy,null)

        //반복문을 사용하여 list 에 데이터를 넘겨 줍시다.
        while(cursor.moveToNext()){
            val friendItem = cursor.getString(cursor.getColumnIndex("friendName"))
            val productName = cursor.getString(cursor.getColumnIndex("productName"))
            val price= cursor.getString(cursor.getColumnIndex("price"))
            val coverImg = cursor.getInt(cursor.getColumnIndex("coverImg"))

            list.add(MyGiftList(friendItem,productName,price,coverImg))
        }
        cursor.close()
        rd.close()

        return list
    }

    //update 메소드
    fun updateMemo(gift:MyGiftList){
        val values = ContentValues()

        values.put("friendItem",gift.friendItem)
        values.put("productName",gift.productName)
        values.put("price",gift.price)
        values.put("coverImg",gift.coverImg)

        val wd = writableDatabase
        wd.update("my",values,"id=${gift.friendItem}",null)
        wd.close()

    }

    //delete 메소드
    fun deleteMemo(gift: MyGiftList){
        val delete = "delete from my where id = ${gift.friendItem}"
        val db = writableDatabase
        db.execSQL(delete)
        db.close()

    }
}