package com.buildforsdg.openmarket.ui.auth.register

import androidx.lifecycle.*
import com.buildforsdg.openmarket.extension.toErrorMessage
import com.buildforsdg.openmarket.ui.auth.model.RegisterRequest
import com.buildforsdg.openmarket.ui.utils.EventUtils
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: RegisterRepository) : ViewModel() {

    private val _progress = MutableLiveData<EventUtils<Boolean>>()
    val progress : LiveData<EventUtils<Boolean>> = _progress

    private val _error = MutableLiveData<EventUtils<String>>()
    val error : LiveData<EventUtils<String>> = _error

    private val _registrationStatus =  MutableLiveData<EventUtils<String>>()
    val registrationStatus : LiveData<EventUtils<String>> = _registrationStatus


    fun register(request: RegisterRequest){
        viewModelScope.launch {
            _progress.value = EventUtils(true)
            try {
                val response = repository.registerUser(request)
                response.status?.let {
                    _registrationStatus.value = EventUtils(it)
                }
            }catch (ex: Exception){
                _error.value = EventUtils(ex.toErrorMessage())
            }finally {
                _progress.value = EventUtils(false)
            }
        }
    }

//    fun registerSeller(request: RegisterRequest){
//        repository.registerUser(request)
//            .onStart { _progress.value = EventUtils(true) }
//            .catch { _error.value = EventUtils(it.localizedMessage) }
//            .asLiveData()
//
//        registeredUserData = liveData {
//            repository.registerUser(request)
//                .onStart { _progress.postValue(EventUtils(true)) }
//                .catch { _error.value = EventUtils(it.localizedMessage) }
//                .onCompletion {  }
//        }
//    }
}