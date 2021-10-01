package com.oguzdogdu.retrofit_post.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oguzdogdu.retrofit_post.service.RetroInstance
import com.oguzdogdu.retrofit_post.service.RetroService
import com.oguzdogdu.retrofit_post.model.User
import com.oguzdogdu.retrofit_post.model.UserResponse
import retrofit2.Call
import retrofit2.Response

class MainViewModel : ViewModel() {

    var createNewUserLiveData: MutableLiveData<UserResponse> = MutableLiveData()

    fun getCreateNewUserObserver(): MutableLiveData<UserResponse> {
        return createNewUserLiveData
    }

    fun createNewUser(user: User) {
        val retroService = RetroInstance.getRetroInstance().create(RetroService::class.java)
        retroService.createUser(user)

        val call = retroService.createUser(user)
        call.enqueue(object : retrofit2.Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {

                if (response.isSuccessful) {
                    createNewUserLiveData.postValue(response.body())
                } else {
                    createNewUserLiveData.postValue(null)
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                createNewUserLiveData.postValue(null)
            }

        })


    }


}

