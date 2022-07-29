package com.example.chapter5_5

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView

class LoginPage : AppCompatActivity() {


    lateinit var logIn_email: EditText
    lateinit var logIn_password: EditText
    lateinit var dbManager: DBManager
    lateinit var sqlitedbR: SQLiteDatabase
    lateinit var logIn_button:ImageButton
    lateinit var toReg_button:Button
    lateinit var notExEmailText:TextView
    lateinit var misMatchText:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        logIn_email=findViewById(R.id.logIn_id)
        logIn_password=findViewById(R.id.logIn_password)
        logIn_button=findViewById(R.id.logIn_button)
        toReg_button=findViewById(R.id.toReg_button)
        notExEmailText=findViewById(R.id.notExEmailText)
        misMatchText=findViewById(R.id.misMatchText)

        //db

       // var userEmail:String=logIn_email.text.toString()

       // cursor =sqlitedbR.rawQuery("SELECT * FROM user WHERE email='$userEmail';",null)
        //이메일이 db에 존재하는지 체크
        //이메일와 비밀번호가 일치하는지 체크
        //메인화면으로 전환
        logIn_button.setOnClickListener {
            dbManager = DBManager(this,"user",null,1)
            sqlitedbR =dbManager.readableDatabase

            var cursor: Cursor
             var userEmail:String=logIn_email.text.toString()
            var passwordS:String=logIn_password.text.toString()
            cursor =sqlitedbR.rawQuery("SELECT * FROM user WHERE email='$userEmail' and password='$passwordS';",null)
            if (availableLogin(cursor)) {
//                misMatchText.visibility=View.INVISIBLE
//                notExEmailText.visibility=View.INVISIBLE
                cursor.close()
                sqlitedbR.close()
                dbManager.close()
                startActivity(Intent(this, MainActivity::class.java))
            }

        }
        //회원가입 전환
        toReg_button.setOnClickListener {
            misMatchText.visibility=View.INVISIBLE
            notExEmailText.visibility=View.INVISIBLE
            startActivity(Intent(this, RegisterPage::class.java))
        }
    }

    private fun availableLogin(cursor: Cursor):Boolean
    {




        if(cursor.count>0)//아이디존재
        {


//            var cursor2: Cursor
//            cursor2 =sqlitedbR.rawQuery("SELECT * FROM user;",null)
//            notExEmailText.visibility=View.VISIBLE
//            notExEmailText.text=cursor2.count.toString()
//            notExEmailText.visibility=View.INVISIBLE
//            misMatchText.visibility=View.VISIBLE
//            // password_mismatch.

              // var passwordTemp:String=cursor2.getString((cursor.getColumnIndex("password"))).toString()
              //  notExEmailText.visibility=View.VISIBLE
               // notExEmailText.text=emailTemp
            return true
//
//            if(logIn_password.text.toString().equals(passwordTemp))
//                return true
//            else
//            {
//                //아이디는 존재 비밀번호 일치 x
//                //이메일과 비밀번호가 일치하지 않습니다.
//                    notExEmailText.visibility=View.INVISIBLE
//                    misMatchText.visibility=View.VISIBLE
//                return false
//            }
        }
        else
        {
            //가입된 적이 없는 이메일이라고 메시지 띠우기

            misMatchText.visibility=View.VISIBLE
            misMatchText.text="존재하지 않거나 비밀번호가 틀렸습니다."
            return false
        }


    }

}