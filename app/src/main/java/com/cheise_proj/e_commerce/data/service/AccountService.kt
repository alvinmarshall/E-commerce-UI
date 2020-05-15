package com.cheise_proj.e_commerce.data.service

import com.cheise_proj.e_commerce.model.User
import com.cheise_proj.e_commerce.utils.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface IAccountService {
    suspend fun createNewAccount(email: String, password: String): Result<User?>
    suspend fun signInWithEmail(email: String, password: String): Result<User?>
    suspend fun signOut()
    suspend fun isLogin(): Boolean
    suspend fun signInWithGoogle(idToken: String?): Result<User?>
    suspend fun signInWithFaceBook(idToken: String): Result<User?>
}


class AccountService @Inject constructor(
    private val networkState: INetworkState,
    private val auth: FirebaseAuth,
    private val socialService: ISocialService
) : IAccountService {
    override suspend fun createNewAccount(email: String, password: String): Result<User?> {
        if (!networkState.isConnected()) return Error(Exception(NO_INTERNET_CONNECTION))

        return try {
            val data = auth.createUserWithEmailAndPassword(email, password).await()
            val user = getUser(data.user)
            Success(user)

        } catch (e: Exception) {
            Error(e)
        }

    }

    override suspend fun signInWithEmail(email: String, password: String): Result<User?> {
        if (!networkState.isConnected()) return Error(Exception(NO_INTERNET_CONNECTION))
        return try {
            val data = auth.signInWithEmailAndPassword(email, password).await()
            val user = getUser(data.user)
            Success(user)
        } catch (e: Exception) {
            Error(e)
        }
    }

    override suspend fun signOut() = auth.signOut()

    override suspend fun isLogin() = auth.currentUser != null

    override suspend fun signInWithGoogle(
        idToken: String?
    ): Result<User?> {
        if (!networkState.isConnected()) return Error(Exception(NO_INTERNET_CONNECTION))
        return try {
            val data = auth.signInWithCredential(socialService.googleSignIn(idToken)).await()
            val user = getUser(data.user)
            Success(user)

        } catch (e: Exception) {
            Error(e)
        }
    }

    override suspend fun signInWithFaceBook(idToken: String): Result<User?> {
        if (!networkState.isConnected()) return Error(Exception(NO_INTERNET_CONNECTION))
        return try {
            val data = auth.signInWithCredential(socialService.faceBookSignIn(idToken)).await()
            val user = getUser(data.user)
            Success(user)

        } catch (e: Exception) {
            Error(e)
        }

    }


    private fun getUser(firebaseUser: FirebaseUser?): User? {
        var user: User?
        with(firebaseUser) {
            user = User(
                uid = this?.uid,
                displayName = this?.displayName,
                email = this?.email,
                photoUrl = this?.photoUrl.toString()
            )
            return user
        }
    }


}