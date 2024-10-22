package com.ssmy.cuddle.ui.main.contents.profile.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * doc 주석
 * @author wookjin
 * @since 9/9/24
 **/
@Parcelize
data class Pet(
    val id: Int = 0,
    val name: String = "",
    val gender: Int = 0,
    val breed: String = "",
    val birthday: String = "",
    val weight: String = "",
    val isNeutered: Boolean = false,
    val daysTogether: String = ""
) : Parcelable
