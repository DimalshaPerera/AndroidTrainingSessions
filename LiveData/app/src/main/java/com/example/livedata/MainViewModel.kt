package com.example.livedata

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {
    private val _seconds=MutableLiveData<Int>()
    private val _finished=MutableLiveData<Boolean>()


    //getterss
    fun seconds():LiveData<Int>{
        return _seconds
    }
    //getters
    fun finished():LiveData<Boolean>{
        return _finished
    }

    fun startCounter(){
        object:CountDownTimer(5000,100){
            override fun onTick(millisUntilFinished: Long) {

                val time = millisUntilFinished / 1000
                _seconds.value = time.toInt()
            }

            override fun onFinish() {
                _finished.value=true
            }
        }.start()

    }


}