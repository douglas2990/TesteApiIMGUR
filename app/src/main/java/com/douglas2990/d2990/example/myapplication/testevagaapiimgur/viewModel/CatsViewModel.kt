package com.douglas2990.d2990.example.myapplication.testevagaapiimgur.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.douglas2990.d2990.example.myapplication.testevagaapiimgur.R
import com.douglas2990.d2990.example.myapplication.testevagaapiimgur.api.RestManager
import com.douglas2990.d2990.example.myapplication.testevagaapiimgur.model.ImgurModelCats
import com.douglas2990.d2990.example.myapplication.testevagaapiimgur.screenstate.CatsScreenState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CatsViewModel: ViewModel() {
    //val zero = 0
    val state: MutableLiveData<CatsScreenState> by lazy{
        MutableLiveData<CatsScreenState>()
    }
    init {
        state.value = CatsScreenState.Loading
        RestManager.getEndpoints().getCats().enqueue(object : Callback<ImgurModelCats> {
            override fun onResponse(
                call: Call<ImgurModelCats>,
                response: Response<ImgurModelCats>
            ) {
                response.body()?.let { body ->
                    state.value = CatsScreenState.Success(body.data)
                } ?: run {state.value = CatsScreenState.Error(R.string.app_name)}
            }

            override fun onFailure(call: Call<ImgurModelCats>, t: Throwable) {
                state.value = CatsScreenState.Error(R.string.app_name)
            }
        })
    }
}