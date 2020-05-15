package com.cheise_proj.e_commerce.data.service

import com.cheise_proj.e_commerce.extension.onError
import com.cheise_proj.e_commerce.utils.INetworkState
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class AccountServiceTest {
    companion object {
        private const val EMAIL = "any_email"
        private const val PASSWORD = "any_password"
        private const val idToken = "any_token"
    }

    @Mock
    lateinit var firebaseAuth: FirebaseAuth

    @Mock
    lateinit var networkState: INetworkState

    @Mock
    lateinit var authResult: Task<AuthResult>

    @Mock
    lateinit var socialService: ISocialService

    @Mock
    lateinit var authCredential: AuthCredential

    @Mock
    lateinit var firebaseUser: FirebaseUser


    private lateinit var accountService: AccountService


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        accountService = AccountService(networkState, firebaseAuth, socialService)

    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Create new account success, No network`() = runBlockingTest {
        Mockito.`when`(networkState.isConnected()).thenReturn(false)
        Mockito.`when`(firebaseAuth.createUserWithEmailAndPassword(EMAIL, PASSWORD))
            .thenReturn(authResult)

        val result = accountService.createNewAccount(EMAIL, PASSWORD)
        result.onError {
            println("error: ${it.message}")
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `SignIn with email, No network`() = runBlockingTest {
        Mockito.`when`(networkState.isConnected()).thenReturn(false)
        Mockito.`when`(firebaseAuth.signInWithEmailAndPassword(EMAIL, PASSWORD))
            .thenReturn(authResult)

        val result = accountService.createNewAccount(EMAIL, PASSWORD)
        result.onError {
            println("error: ${it.message}")
        }
    }


    @ExperimentalCoroutinesApi
    @Test
    fun `SignIn with google success, No network`() = runBlockingTest {
        Mockito.`when`(networkState.isConnected()).thenReturn(false)
        Mockito.`when`(firebaseAuth.signInWithCredential(authCredential))
            .thenReturn(authResult)

        val result = accountService.signInWithGoogle(idToken)
        result.onError {
            println("error: ${it.message}")
        }

    }

    @ExperimentalCoroutinesApi
    @Test
    fun `SignIn with face success, No network`() = runBlockingTest {
        Mockito.`when`(networkState.isConnected()).thenReturn(false)
        Mockito.`when`(firebaseAuth.signInWithCredential(authCredential))
            .thenReturn(authResult)

        val result = accountService.signInWithEmail(EMAIL, PASSWORD)
        result.onError {
            println("error: ${it.message}")
        }

    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Is user login success`() = runBlockingTest {
        Mockito.`when`(networkState.isConnected()).thenReturn(false)
        Mockito.`when`(firebaseAuth.currentUser).thenReturn(firebaseUser)
        val status = accountService.isLogin()
        println("status: $status")
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `SignOut user success`() = runBlockingTest {
        accountService.signOut()
        Mockito.verify(firebaseAuth, times(1)).signOut()
    }


}