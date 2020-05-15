package com.cheise_proj.e_commerce.di.module.auth

import com.cheise_proj.e_commerce.data.repository.IUserRepository
import com.cheise_proj.e_commerce.data.repository.UserRepository
import com.cheise_proj.e_commerce.data.service.AccountService
import com.cheise_proj.e_commerce.data.service.IAccountService
import com.cheise_proj.e_commerce.data.service.ISocialService
import com.cheise_proj.e_commerce.data.service.SocialService
import com.google.firebase.auth.FirebaseAuth
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [AuthModule.Binders::class])
class AuthModule {
    @Module
    interface Binders {
        @Binds
        fun bindUserRepository(userRepository: UserRepository): IUserRepository

        @Binds
        fun bindAccountService(accountService: AccountService): IAccountService


        @Binds
        fun bindSocialService(socialService: SocialService): ISocialService


    }

    @Singleton
    @Provides
    fun providesFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()
}