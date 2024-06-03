package com.example.mystore.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mystore.data.ProfileData
import com.google.firebase.firestore.FirebaseFirestore

class ChangePassViewModel:ViewModel() {

    private val fireStore = FirebaseFirestore.getInstance()

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean> get() = _success

    private val _data=MutableLiveData<ProfileData>()
    val data :LiveData<ProfileData>get()=_data



    fun getData(token: String){
        _loading.value=true
        fireStore.collection("Profiles").document(token).addSnapshotListener { value, error ->
            if (error!= null){
                _errorMessage.value="FAILURE"
                return@addSnapshotListener
            }
            if (value!=null){
                _loading.value=false
                value.data?.let {data->
                    val email=data["userEmail"] as String
                    val password=data["userPassword"] as String
                        _data.value = ProfileData(email, password)

                }
            }
        }
    }

    fun updateData(token: String,email:String,password:String){
        if (password.length < 6) {
            _errorMessage.value="Password too short"
            return
        }

        val newData = hashMapOf<String, Any>()
        newData["userEmail"] = email
        newData["userPassword"] = password

        fireStore.collection("Profiles")
            .document(token)
            .update(newData)

            .addOnSuccessListener {
                _success.value=true
            }
            .addOnFailureListener {
                _errorMessage.value="Failure"
            }
    }

}