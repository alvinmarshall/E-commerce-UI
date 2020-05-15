package com.cheise_proj.e_commerce.data.service

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.GoogleAuthProvider
import javax.inject.Inject

interface ISocialService {
    fun googleSignIn(idToken: String?): AuthCredential
    fun faceBookSignIn(idToken: String): AuthCredential
}


class SocialService @Inject constructor(): ISocialService {
    override fun googleSignIn(idToken: String?): AuthCredential {
        return GoogleAuthProvider.getCredential(idToken, null)

    }

    override fun faceBookSignIn(idToken: String): AuthCredential {
        return FacebookAuthProvider.getCredential(idToken)
    }
}