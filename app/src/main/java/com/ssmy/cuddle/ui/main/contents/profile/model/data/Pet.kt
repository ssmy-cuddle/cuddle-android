package com.ssmy.cuddle.ui.main.contents.profile.model.data

/**
 * doc 주석
 * @author wookjin
 * @since 9/9/24
 **/
data class Pet(
    val id: Int,
    val name: String,
//    val imageUrl: String,
    val gender: Int,
    val breed: String,
    val birthday: String,
    val weight: String,
    val isNeutered: Boolean,
    val daysTogether: String,
//    val isDeceased: Boolean
)
