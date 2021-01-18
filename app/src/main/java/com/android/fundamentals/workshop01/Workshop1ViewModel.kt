package com.android.fundamentals.workshop01

import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class Workshop1ViewModel(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _mutableLoginState = MutableLiveData<LoginResult>()
    private val _mutableLogoutState = MutableLiveData<LogoutResult>()

    val loginState: LiveData<LoginResult> get() = _mutableLoginState
    val logoutState: LiveData<LogoutResult> get() = _mutableLogoutState

    fun login(userName: String, password: String) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                _mutableLoginState.value = LoginResult.Loading

                withContext(Dispatchers.IO) {
                    delay(DELAY_MILLIS)
                }

                _mutableLoginState.value = when {
                    userName.isEmpty() -> LoginResult.Error.UserName
                    password.isEmpty() -> LoginResult.Error.Password
                    else -> {
                        val newToken = UUID.randomUUID().toString()
                        // TODO 02: create updateUserToken fun that will add or update user token in SP
                        updateUserToken(newToken)
                        LoginResult.Success
                    }
                }
            }
        }
    }

    // TODO 03: create logout fun that will wait for logout clear user token from SP
    fun logout() {
        viewModelScope.launch {
            _mutableLogoutState.value = LogoutResult.Loading
            delay(DELAY_MILLIS)
            clearUserToken()
            _mutableLogoutState.value = LogoutResult.Success
        }
        // TODO 04: create clearUserToken fun that will clear user token from SP when user is logged in
    }

    fun checkUserIsLoggedIn(): Boolean {
        // TODO 05: check sharedPreferences is it has saved user token
        return sharedPreferences.contains(TOKEN)
    }

    private fun updateUserToken(token: String) {
        sharedPreferences.edit { putString(TOKEN, token) }
    }

    private fun clearUserToken() {
        sharedPreferences.edit { remove(TOKEN) }
    }

    companion object {
        const val DELAY_MILLIS: Long = 3_000
        const val TOKEN = "token"
    }
}

sealed class LoginResult {

    object Loading : LoginResult()

    object Success : LoginResult()

    sealed class Error : LoginResult() {

        object UserName : Error()

        object Password : Error()
    }
}

sealed class LogoutResult {

    object Loading : LogoutResult()

    object Success : LogoutResult()

    sealed class Error : LogoutResult()
}