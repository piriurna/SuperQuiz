package com.piriurna.superquiz.presentation.onboarding.models

import com.piriurna.superquiz.R

enum class OnboardingImage(val id : String, val image: Int) {
    BANANA("banana", R.drawable.ic_banana_svgrepo_com),
    STRAWBERRY("strawberry", R.drawable.ic_strawberries),
    LIME("lime",R.drawable.ic_lime);


    companion object {
        fun getById(id : String) : OnboardingImage {
            return values().firstOrNull { it.id == id }?:BANANA
        }
    }
}