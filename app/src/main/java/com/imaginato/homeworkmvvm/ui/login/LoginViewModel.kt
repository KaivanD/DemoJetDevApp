package com.imaginato.homeworkmvvm.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.imaginato.homeworkmvvm.data.remote.login.WebService.APIServiceFactory
import com.imaginato.homeworkmvvm.data.remote.login.request.LoginRequest
import com.imaginato.homeworkmvvm.data.remote.login.response.LoginResponse


class LoginViewModel : ViewModel() {
    private var validationRepository: ValidationRepository = ValidationRepository()
    private var retroRepository: APIServiceFactory = APIServiceFactory()
    // private val retroObservable: LiveData<LoginResponse>


    fun validateCredentials(username: String, passWord: String): LiveData<String> {
        return validationRepository.validateCredentials(username, passWord)
    }

    fun getLoginObservable(loginRequest: LoginRequest): LiveData<LoginResponse> {
        return retroRepository.loginWebService(loginRequest)
    }
}