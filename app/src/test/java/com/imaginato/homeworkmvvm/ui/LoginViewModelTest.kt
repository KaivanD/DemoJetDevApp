package com.imaginato.homeworkmvvm.ui

import androidx.lifecycle.LiveData
import com.imaginato.homeworkmvvm.ui.login.LoginViewModel
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test


class LoginViewModelTest {

    private val FAKE_STRING = "Login was successful"

    @Test
    fun readStringFromContext_LocalizedString() {
        val myObjectUnderTest = LoginViewModel()

        // ...when the string is returned from the object under test...
        val result: LiveData<String> = myObjectUnderTest.validateCredentials("username", "11111111")

        // ...then the result should be the expected one.
        assertThat(result, `is`(FAKE_STRING))
    }
}