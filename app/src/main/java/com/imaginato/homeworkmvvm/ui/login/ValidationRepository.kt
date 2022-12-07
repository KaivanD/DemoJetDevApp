package com.imaginato.homeworkmvvm.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ValidationRepository() {


    fun validateCredentials(username: String, password: String): LiveData<String> {
        val loginErrorMessage = MutableLiveData<String>()
        if (username.trim().isNotEmpty()) {
            if (password.trim().isEmpty()) {
                loginErrorMessage.value = "Please Enter Password"
            } else if (password.length < 7) {
                loginErrorMessage.value = "Invalid Password"
            } else {
                loginErrorMessage.value = "Successful Login"
            }
        } else {
            loginErrorMessage.value = "Please Enter User Name"
        }

        return loginErrorMessage
    }

}
