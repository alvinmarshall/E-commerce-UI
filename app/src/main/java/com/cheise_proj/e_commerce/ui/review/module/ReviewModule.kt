package com.cheise_proj.e_commerce.ui.review.module

import com.cheise_proj.e_commerce.ui.review.RatingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ReviewModule {
    @ContributesAndroidInjector
    fun contributeRatingFragment(): RatingFragment
}