package com.cheise_proj.e_commerce.utils

const val NO_INTERNET_CONNECTION = "No network connection"
const val DELAY_MILL = 1000L

enum class ClickOption {
    VIEW, VIEW_ALL
}

enum class CatalogOption {
    VIEW, FAVORITE
}

enum class HorizontalAdapterOption {
    VIEW, FAVORITE
}

enum class FavoriteOption {
    CLOSE, CART
}

enum class CartOptions {
    INCREASE, DECREASE, FAVORITE, REMOVE
}

enum class CheckoutOption {
    ADDRESS_CHANGE, PAYMENT_CHANGE,DELIVERY
}


const val MASTER_CARD_LOGO_URL =
    "https://toppng.com/uploads/preview/mastercard-logo-png-image-background-logo-mastercard-11563000929p7h8ctftya.png"
const val VISA_CARD_LOGO_URL =
    "https://img.favpng.com/6/18/12/credit-card-debit-card-mastercard-logo-visa-png-favpng-Dr8ZuXU7Db5sQ39iMeCh8ZxC8.jpg"

const val PRICE_VIEW = 0
const val COLORS_VIEW = 1
const val SIZE_VIEW = 2
const val CATEGORY_VIEW = 3
const val BRAND_VIEW = 4
const val LAYOUT_PADDING_SMALL = 5

const val DATABASE_NAME = "ecommerce.db"