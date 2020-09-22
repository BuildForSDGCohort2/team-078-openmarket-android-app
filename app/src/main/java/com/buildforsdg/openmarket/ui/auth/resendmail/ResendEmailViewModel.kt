package com.buildforsdg.openmarket.ui.auth.resendmail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buildforsdg.openmarket.extension.toErrorMessage
import com.buildforsdg.openmarket.ui.auth.login.LoginRepository
import com.buildforsdg.openmarket.ui.utils.EventUtils
import kotlinx.coroutines.launch

class ResendEmailViewModel(private val repository: LoginRepository) : ViewModel() {
    private val _progress = MutableLiveData<EventUtils<Boolean>>()
    val progress : LiveData<EventUtils<Boolean>> = _progress

    private val _error = MutableLiveData<EventUtils<String>>()
    val error : LiveData<EventUtils<String>> = _error

    private val _linkStatus = MutableLiveData<EventUtils<String>>()
    val linkStatus : LiveData<EventUtils<String>> = _linkStatus


    fun resendLinkTo(emailAddress : String){
        viewModelScope.launch {
            _progress.value = EventUtils(true)
            try {
                val response = repository.resendVerificationLink(emailAddress)
                response.message?.let {
                    _linkStatus.value = EventUtils(it)
                }
            }catch (ex: Exception){
                _error.value = EventUtils(ex.toErrorMessage())
            }finally {
                _progress.value = EventUtils(false)
            }
        }
    }
}