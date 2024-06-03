package com.example.mystore.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mystore.data.SettingsData.ProductDTO
import com.example.mystore.Repository.ApiRepoImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AnaSehifeViewModel : ViewModel() {



    private val repo=ApiRepoImpl()

    private val _loading=MutableLiveData<Boolean>()
    val loading:LiveData<Boolean> = _loading

    private val _error=MutableLiveData<String>()
    val error:LiveData<String> get() = _error

    private val _success=MutableLiveData<ProductDTO>()
    val success:LiveData<ProductDTO> = _success

    init {
        getProducts()
    }
    fun getProducts() {
        _loading.value=true

        repo.getProducts().enqueue(object :Callback<ProductDTO>{
            override fun onResponse(call: Call<ProductDTO>, response: Response<ProductDTO>) {

                _loading.value=false
                if (response.isSuccessful){
                    response.body()?.let {
                        _success.value=it
                    }
                }else{
                    _error.value="Error"
                }
            }

            override fun onFailure(call: Call<ProductDTO>, t: Throwable) {
                _loading.value=false
                _error.value=t.localizedMessage
            }

        })

    }

}