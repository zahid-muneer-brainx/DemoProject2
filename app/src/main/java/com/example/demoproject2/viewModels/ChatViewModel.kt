package com.example.demoproject2.viewModels

import android.content.Context
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoproject2.R
import com.example.demoproject2.SharedPreferences.MySharedPreferences
import com.example.demoproject2.models.ChatModel
import com.example.demoproject2.models.MetaData
import com.example.demoproject2.repositories.AuthRepository
import com.google.firebase.installations.FirebaseInstallations
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ChatViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    @ApplicationContext val context: Context,
) : BaseViewModel() {
    var chatObserver = MutableLiveData<ArrayList<ChatModel>>()
    var metaData=MutableLiveData<MetaData>()
    fun getChatData(page: Int) {
        viewModelScope.launch(Dispatchers.IO)
        {
            authRepository.getChatList(page)
            { data, message, status
                ->
                if (status) {
                    if (data != null) {
                        chatObserver.postValue(data.chatModel)
                        println(data.chatModel.toString())
                        metaData.postValue(data.metaData)
                    }
                } else {
                    hideProcessingLoader()
                    showErrorDialog.postValue(message)
                }
            }
        }

    }
}