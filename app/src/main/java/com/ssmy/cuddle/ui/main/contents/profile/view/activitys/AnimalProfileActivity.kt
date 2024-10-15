package com.ssmy.cuddle.ui.main.contents.profile.view.activitys

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.ssmy.cuddle.R
import com.ssmy.cuddle.databinding.ActivityAnimalProfileBinding
import com.ssmy.cuddle.ui.base.BaseActivity
import com.ssmy.cuddle.ui.main.contents.profile.viewmodels.AnimalProfileViewModel

class AnimalProfileActivity : BaseActivity<AnimalProfileViewModel>() {

    override val viewModel: AnimalProfileViewModel by viewModels()
    private lateinit var binding: ActivityAnimalProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_animal_profile)
    }
}