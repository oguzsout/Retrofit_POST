package com.oguzdogdu.retrofit_post.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.oguzdogdu.retrofit_post.viewmodel.MainViewModel
import com.oguzdogdu.retrofit_post.model.User
import com.oguzdogdu.retrofit_post.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        binding.buttonCreate.setOnClickListener {
            createUser()
        }
    }

    private fun createUser() {

        val user = User(
            "",
            binding.editTextName.text.toString(),
            binding.editTextEmail.text.toString(),
            "Active",
            "Male"
        )
        viewModel.createNewUser(user)
    }

    private fun initViewModel() {

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.getCreateNewUserObserver().observe(this, {
            if (it == null) {
                Toast.makeText(this@MainActivity, "Failed to create User", Toast.LENGTH_LONG).show()
            } else {
                //{"code":201,"meta":null,"data":{"id":2877,"name":"xxxxxaaaaabbbbb","email":"xxxxxaaaaabbbbb@gmail.com","gender":"male","status":"active"}}
                Toast.makeText(this@MainActivity, "Successfully created User", Toast.LENGTH_LONG)
                    .show()
            }
        })
    }
}