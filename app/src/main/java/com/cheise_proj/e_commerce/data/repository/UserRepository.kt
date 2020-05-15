package com.cheise_proj.e_commerce.data.repository

import com.cheise_proj.e_commerce.data.service.IAccountService
import com.cheise_proj.e_commerce.model.User
import com.cheise_proj.e_commerce.utils.Result
import javax.inject.Inject

class UserRepository @Inject constructor(private val accountService: IAccountService) :
    IUserRepository {

    override suspend fun registerUser(
        email: String,
        password: String,
        displayName: String
    ): Result<User?> = accountService.createNewAccount(email, password)

    override suspend fun signInWithEmail(email: String, password: String): Result<User?> =
        accountService.signInWithEmail(email, password)

    override suspend fun signOut() {
        accountService.signOut()
    }
}