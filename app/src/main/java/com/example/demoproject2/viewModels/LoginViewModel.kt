package com.example.demoproject2.viewModels

import android.content.Context
import android.util.Patterns
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoproject2.R
import com.example.demoproject2.SharedPreferences.MySharedPreferences
import com.example.demoproject2.repositories.AuthRepository
import com.google.firebase.installations.FirebaseInstallations
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

    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var logInObserver = MutableLiveData<Boolean>()
    var isReadyToGo = false
    fun onClick(view: View, viewModel: ViewModel) {
        when (view.id) {
            R.id.login_btn -> {
                signInApi()
            }
        }
    }

    fun signInApi() {
        if (!isValid()) return
        showProcessingLoader()
        isReadyToGo = false

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
                        } else {
                            hideProcessingLoader()
                            showErrorDialog.postValue(message)
                        }
                    }
                }

        }
    }
}

    private fun isValid(): Boolean {
        return when {
            email.value.isNullOrEmpty() -> {
                showToast(context.getString(R.string.pleaseenteremail))
                false
            }
            !Patterns.EMAIL_ADDRESS.matcher(email.value.toString()).matches() -> {
                showToast(context.getString(R.string.pleaseentervailedemail))
                false
            }
            password.value.isNullOrEmpty() -> {
                showToast(context.getString(R.string.pleaseenterpassword))
                false
            }
            else -> true
        }
    }
}