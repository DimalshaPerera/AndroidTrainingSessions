package com.example.mvvm

import android.util.Patterns
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class ViewModel:BaseObservable() {
    private var model:Model=(Model("",""))
    private val successMsg="Login successful"
    private val errorMsg="email or pw is not valid"

    @get:Bindable
    var toastMsg:String?=null
        private set(value) {
            field = value
            notifyPropertyChanged(BR.toastMsg)
        }
    @get:Bindable
    var userEmail: String?
        get() = model.email
        set(value) {
            model.email = value
            notifyPropertyChanged(BR.userEmail)
        }

    @get:Bindable
    var userPasssword:String?
        get()=model.password
        set(value) {
            model.password=value
            notifyPropertyChanged(BR.userPasssword)
        }

    fun onButtonClicked() {
        toastMsg = if (isValid()) successMsg else errorMsg
    }


    private fun isValid(): Boolean {
        return !userEmail.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(userEmail!!).matches()
                && !userPasssword.isNullOrEmpty() && userPasssword!!.length > 5
    }

}