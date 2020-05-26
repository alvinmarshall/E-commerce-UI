package com.cheise_proj.e_commerce.di.module.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cheise_proj.e_commerce.di.key.ViewModelKey
import com.cheise_proj.e_commerce.factory.ViewModelFactory
import com.cheise_proj.e_commerce.ui.bag.BagViewModel
import com.cheise_proj.e_commerce.ui.category.CategoryViewModel
import com.cheise_proj.e_commerce.ui.favorite.FavoriteViewModel
import com.cheise_proj.e_commerce.ui.product.ProductViewModel
import com.cheise_proj.e_commerce.ui.review.ReviewViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule.Binders::class])
class ViewModelModule {

    @Module
    interface Binders {
        @Binds
        fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory


        @Binds
        @IntoMap
        @ViewModelKey(CategoryViewModel::class)
        fun bindCategoryViewModel(categoryViewModel: CategoryViewModel): ViewModel

        @Binds
        @IntoMap
        @ViewModelKey(ProductViewModel::class)
        fun bindProductViewModel(productViewModel: ProductViewModel): ViewModel

        @Binds
        @IntoMap
        @ViewModelKey(ReviewViewModel::class)
        fun bindReviewViewModel(reviewViewModel: ReviewViewModel): ViewModel

        @Binds
        @IntoMap
        @ViewModelKey(FavoriteViewModel::class)
        fun bindFavoriteViewModel(favoriteViewModel: FavoriteViewModel): ViewModel

        @Binds
        @IntoMap
        @ViewModelKey(BagViewModel::class)
        fun bindBagViewModel(bagViewModel: BagViewModel): ViewModel

    }


}