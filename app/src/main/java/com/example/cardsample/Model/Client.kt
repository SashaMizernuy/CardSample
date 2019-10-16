package com.example.cardsample.Model

class Client (override val name:String,override val cardNumber:String,override val expireDate:String,override val secureCode:String):InterfaceClient{
    override val Proving: Boolean
        get() = (cardNumber.length==16&&expireDate.length==5&&secureCode.length==4)






    }

