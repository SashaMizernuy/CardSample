package com.example.cardsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.example.cardsample.Presenter.LoginPresenter
import com.example.cardsample.View.InterfaceView

import kotlinx.android.synthetic.main.activity_main1.*

class MainActivity : AppCompatActivity(),InterfaceView {
    override fun onResult(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }

    internal lateinit var loginPresenter: LoginPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)


        loginPresenter= LoginPresenter(this)



        btn_addCard.setOnClickListener {
            loginPresenter.onLogin(edit_name.text.toString(),
                edit_cardNumber.text.toString()+
                        edit_cardNumber1.text.toString()+
                        edit_cardNumber2.text.toString()+
                        edit_cardNumber3.text.toString(),
                edit_expireDate.text.toString(),
                edit_securityCode.text.toString()) }

        cursorMoveToNext()
        addDateDivider()
    }


    fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }
        })
    }


    fun cursorMoveToNext(){
        edit_cardNumber.afterTextChanged {
            if(edit_cardNumber.text.count()==4) {
                Log.i("script","count=");
                edit_cardNumber.clearFocus()
                edit_cardNumber1.requestFocus()
            }
        }
        edit_cardNumber1.afterTextChanged {
            if(edit_cardNumber1.text.count()==4) {
                edit_cardNumber1.clearFocus()
                edit_cardNumber2.requestFocus()
            }else if (edit_cardNumber1.text.count()==0){
                edit_cardNumber1.clearFocus()
                edit_cardNumber.requestFocus()
            }
        }
        edit_cardNumber2.afterTextChanged {
            if(edit_cardNumber2.text.count()==4) {
                edit_cardNumber2.clearFocus()
                edit_cardNumber3.requestFocus()
            }else if (edit_cardNumber2.text.count()==0){
                edit_cardNumber2.clearFocus()
                edit_cardNumber1.requestFocus()
            }
        }
        edit_cardNumber3.afterTextChanged {
            if(edit_cardNumber3.text.count()==4) {
                edit_cardNumber3.clearFocus()
            }else if (edit_cardNumber3.text.count()==0) {
                edit_cardNumber3.clearFocus()
                edit_cardNumber2.requestFocus()
            }
        }
    }
    fun addDateDivider(){
        val ch="/"
        edit_expireDate.afterTextChanged {
            if (edit_expireDate.text.count() == 2) {
                edit_expireDate.text.insert(2, Editable.Factory.getInstance().newEditable(ch).append())
                edit_expireDate.clearFocus()
                edit_expireDate.requestFocus(4)
            }
        }

    }
}


