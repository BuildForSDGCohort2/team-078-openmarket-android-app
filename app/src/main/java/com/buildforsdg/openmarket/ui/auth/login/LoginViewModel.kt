package com.buildforsdg.openmarket.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buildforsdg.openmarket.extension.toErrorMessage
import com.buildforsdg.openmarket.ui.auth.model.GoogleAuthRequest
import com.buildforsdg.openmarket.ui.auth.model.LoginRequest
import com.buildforsdg.openmarket.ui.utils.EventUtils
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {
    private val _progress = MutableLiveData<EventUtils<Boolean>>()
    val progress : LiveData<EventUtils<Boolean>> = _progress

    private val _error = MutableLiveData<EventUtils<String>>()
    val error : LiveData<EventUtils<String>> = _error

    private val _loginStatus =  MutableLiveData<EventUtils<String>>()
    val loginStatus : LiveData<EventUtils<String>> = _loginStatus

    private val _googleAuthHandshake =  MutableLiveData<EventUtils<String>>()
    val googleAuthHandshake : LiveData<EventUtils<String>> = _googleAuthHandshake

    fun signInViaEmail(request: LoginRequest){
        viewModelScope.launch {
            _progress.value = EventUtils(true)
            try {
                val response = repository.userLogin(request)
                response.status?.let {
                    _loginStatus.value = EventUtils(it)
                }
            }catch (ex: Exception){
                _error.value = EventUtils(ex.toErrorMessage())
            }finally {
                _progress.value = EventUtils(false)
            }
        }
    }

    fun authenticateWithGoogle(request : GoogleAuthRequest){
        viewModelScope.launch {
            _progress.value = EventUtils(true)
            try {
                val response = repository.googleAuth(request)
                response.status?.let {
                    _googleAuthHandshake.value = EventUtils(it)
                }
            }catch (ex: Exception){
                _error.value = EventUtils(ex.toErrorMessage())
            }finally {
                _progress.value = EventUtils(false)
            }
        }
    }
}