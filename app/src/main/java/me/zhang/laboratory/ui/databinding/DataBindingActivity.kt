package me.zhang.laboratory.ui.databinding

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import me.zhang.laboratory.R
import me.zhang.laboratory.databinding.ActivityBinding

class DataBindingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityBinding>(this, R.layout.activity_binding)
        val userViewModel by viewModels<UserViewModel>()
        userViewModel.setUser(name = "Zhang", age = 31, arrayListOf("Coding", "Cooking", "Surfing"))
        userViewModel.setAccount(username = "zhang", password = "123456")
        binding.viewModel = userViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}