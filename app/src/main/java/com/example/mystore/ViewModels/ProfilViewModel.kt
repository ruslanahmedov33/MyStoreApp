package com.example.mystore.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mystore.data.ProfileeeData
import com.google.firebase.firestore.FirebaseFirestore

class ProfilViewModel: ViewModel() {
    private val fireStore = FirebaseFirestore.getInstance()


    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean> get() = _success

    private val _data = MutableLiveData<ProfileeeData>()
    val data: LiveData<ProfileeeData> get() = _data


    fun getData(token: String) {
        fireStore.collection("Profiles").document(token).addSnapshotListener { value, error ->
            if (error != null) {
                _errorMessage.value = "FAILURE"
                return@addSnapshotListener
            }
            if (value != null) {
                value.data?.let { data ->
                    val name = data["userName"] as String
                    _data.value= ProfileeeData(name)

                }
            }
        }
    }
}