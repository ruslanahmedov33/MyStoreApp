package com.example.mystore.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mystore.data.CategoryData.CategoryDTO
import com.example.mystore.Repository.ApiRepoImpl
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {

    private val repo=ApiRepoImpl()

    private val _loading= MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error= MutableLiveData<String>()
    val error: LiveData<String> get() = _error


    private val _success= MutableLiveData<CategoryDTO>()
    val success: LiveData<CategoryDTO> = _success

    fun getItemByCategory(categoryName:String){
        _loading.value=true

        viewModelScope.launch {
            val response = repo.getProductsByCategory(categoryName)
            if (response.isSuccessful || response.body()!=null){
                _loading.value=false
                _success.postValue(response.body())
            }else{
                //burda error
            }
        }
    }
}