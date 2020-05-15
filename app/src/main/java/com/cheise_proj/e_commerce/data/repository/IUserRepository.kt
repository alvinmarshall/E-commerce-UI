package com.cheise_proj.e_commerce.data.repository

import com.cheise_proj.e_commerce.model.User
import com.cheise_proj.e_commerce.utils.Result

interface IUserRepository {
  suspend  fun registerUser(email: String, password: String, displayName: String): Result<User?>
  suspend  fun signInWithEmail(email: String, password: String): Result<User?>
  suspend fun signOut()
}