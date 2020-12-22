package com.android.fundamentals.workshop02

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.fundamentals.domain.login.LoginInteractor
import com.android.fundamentals.domain.login.LoginResult
import kotlinx.coroutines.launch
import java.util.logging.LogRecord

class Workshop2ViewModel(
    private val interactor: LoginInteractor
) : ViewModel() {

    //TODO 06: Create private property MutableLiveData for state storing.
    // Pass initial value "State.Default" in constructor: MutableLiveData(State.Default()).
    private val _state = MutableLiveData<State>(State.Default)

    //TODO 07: Create public property LiveData as a getter to provide state outside.
    // Init this public getter with your private MutableLiveData.
    val state: LiveData<State> = _state

    fun login(userName: String, password: String) {
        viewModelScope.launch {
            //TODO 08: Set "State.Loading()" to the private liveData's value.
            _state.value = State.Loading

            val loginResult = interactor.login(userName = userName, password = password)

            //TODO 09: Handle "loginResult" with "when()".
            val state = when (loginResult) {
                LoginResult.Error.UserName -> State.UserNameError
                LoginResult.Error.Password -> State.PasswordError
                LoginResult.Success -> State.Success
            }
            _state.value = state
            // Set actual "State. ...()" to the private liveData's value (success, errors).
        }
    }

    sealed class State {
        object Default : State()
        object Loading : State()
        object UserNameError : State()
        object PasswordError : State()
        object Success : State()
    }
}