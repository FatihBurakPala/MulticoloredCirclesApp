package com.android.multicolored_circles.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {

    /* ----------------------SOUND---------------------- */
    var sound = MutableLiveData(true)

    fun setSound() {
        if(sound.value == true) {
            sound.value = false
        }
        else if(sound.value == false) {
            sound.value = true
        }
    }

    /* ----------------------VIBRATION---------------------- */
    var vibration = MutableLiveData(true)

    fun setVibration() {
        if(vibration.value == true) {
            vibration.value = false
        }
        else if(vibration.value == false) {
            vibration.value = true
        }
    }

    /* ----------------------LANGUAGE---------------------- */
    var language = MutableLiveData(true)

    fun setLanguage() {
        if(language.value == true) {
            language.value = false
        }
        else if(language.value == false) {
            language.value = true
        }
    }

    /* ----------------------LEVEL LOCK---------------------- */
    var lock2 = MutableLiveData(false)
    var lock3 = MutableLiveData(false)
    var lock4 = MutableLiveData(false)
    var lock5 = MutableLiveData(false)
}
