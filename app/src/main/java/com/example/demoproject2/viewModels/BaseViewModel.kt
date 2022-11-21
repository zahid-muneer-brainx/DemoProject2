package com.example.demoproject2.viewModels

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


open class BaseViewModel : ViewModel() {

    //region properties
    val loadingObserver = MutableLiveData<Boolean>()
    val messageObserver = MutableLiveData<Any?>()
    var showErrorDialog = MutableLiveData<String>()
//    endregion

//    region public methods

    fun showProcessingLoader() = loadingObserver.postValue(true)
    fun hideProcessingLoader() = loadingObserver.postValue(false)
    fun showToast(content: Any? = null) = messageObserver.postValue(content)
    fun showErrorDialog(content: Any? = null) = messageObserver.postValue(content)


//    endregion
}