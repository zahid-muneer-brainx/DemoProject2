package com.example.demoproject2.viewModels

import android.content.Context
import android.util.Patterns
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.demoproject2.R
import com.example.demoproject2.SharedPreferences.MySharedPreferences
import com.example.demoproject2.repositories.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    @ApplicationContext val context: Context,
    private val dataManager: MySharedPreferences
) : BaseViewModel() {

    var email=MutableLiveData<String>()
    var password =MutableLiveData<String>()
    var logInObserver = MutableLiveData<Boolean>()
    var errorObserver=MutableLiveData<String>()
    fun onClick(view: View)
    {
        signInApi()
    }
    fun signInApi() {
        if (!isValid()) {
            return
        }

        isLoading.postValue(true)
        email.value?.let { it1 ->
            password.value?.let { it2 ->
                viewModelScope.launch(Dispatchers.IO) {
                    authRepository.signIn(
                        it1,
                        it2
                    ) { data, message, status ->
                        dataManager.isuserlogin = status
                        dataManager.notFirstTimeLogin = data?.first_login ?: true
                        if (status) {
                            logInObserver.postValue(status)
                            isLoading.postValue(false)
                        } else {
                            isLoading.postValue(false)
                            errorObserver.postValue(message)
                        }
                    }
                }

        }
    }
}

    private fun isValid(): Boolean {
        return when {
            email.value.isNullOrEmpty() -> {
                showErrorDialog.postValue(context.getString(R.string.pleaseenteremail))
                false
            }
            !Patterns.EMAIL_ADDRESS.matcher(email.value.toString()).matches() -> {
                showErrorDialog.postValue(context.getString(R.string.pleaseentervailedemail))
                false
            }
            password.value.isNullOrEmpty() -> {
                showErrorDialog.postValue(context.getString(R.string.pleaseenterpassword))
                false
            }
            else -> true
        }
    }
}