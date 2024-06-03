package com.congntph34559.fpoly.lab7_kot104_ph34559_application.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName

    private val _pass = MutableLiveData<String>()
    val pass: LiveData<String> = _pass

    private val _rememberMe = MutableLiveData<Boolean>()
    val rememberMe: LiveData<Boolean> = _rememberMe

    private val _isAuthenticated = MutableLiveData<Boolean?>()
    val isAuthenticated: LiveData<Boolean?> = _isAuthenticated

    fun login(userName: String, password: String, rememberMe: Boolean) {
        _isAuthenticated.value = userName == "admin" && password == "123"

        //if (userName == "admin" && password == "123") {
        //_isAuthenticated.value = true
        //} else {
        // _isAuthenticated.value = false
        //}
    }

    fun resetAuthenticationState() {
        _isAuthenticated.value = null
    }

}