package com.cheise_proj.e_commerce.data.repository

import com.cheise_proj.e_commerce.data.service.IAccountService
import com.cheise_proj.e_commerce.extension.onSuccess
import com.cheise_proj.e_commerce.utils.FakeAuthService
import com.cheise_proj.e_commerce.utils.Success
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class UserRepositoryTest {
    companion object {
        private const val EMAIL = "any_email"
        private const val PASSWORD = "any_password"
        private const val DISPLAY_NAME = "any_display_name"
    }

    @Mock
    lateinit var iUserRepository: IUserRepository

    @Mock
    lateinit var iAccountService: IAccountService


    private lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        userRepository = UserRepository(iAccountService)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Create new user account success`() = runBlockingTest {
        val actual = FakeAuthService.getUser()
        Mockito.`when`(iAccountService.createNewAccount(EMAIL, PASSWORD))
            .thenReturn(Success(actual))

        Mockito.`when`(iUserRepository.registerUser(EMAIL, PASSWORD, DISPLAY_NAME))
            .thenReturn(Success(actual))

        userRepository.registerUser(EMAIL, PASSWORD, DISPLAY_NAME)
            .onSuccess { user -> println("user: $user") }
    }
}