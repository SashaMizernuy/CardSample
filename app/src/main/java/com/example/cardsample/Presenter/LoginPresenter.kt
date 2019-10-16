package com.example.cardsample.Presenter

import android.util.Log
import com.example.cardsample.Model.Client
import com.example.cardsample.View.InterfaceView
import org.json.JSONObject

class LoginPresenter (internal var iCardView:InterfaceView):InterfacePresenter{
    override fun onLogin(name: String, cardNumber: String, expireDate: String, secureCode: String) {
        val client=Client(name,cardNumber,expireDate,secureCode)

        val cardSucces=client.Proving
        if(cardSucces)
            iCardView.onResult("Card succes")
        else
            iCardView.onResult("Card error")





    }
}