package com.example.mystore.ViewModels

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AuthViewModel() : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val fireStore = FirebaseFirestore.getInstance()

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean> get() = _success


    fun register(email: String, password: String, ad: String, checkbox: Boolean) {

        if (ad.isEmpty() || email.isEmpty() || password.isEmpty()) {
            _errorMessage.value = "Fields Are Empty"
            return
        }
        if (password.length < 6) {
            _errorMessage.value = "Password too short"
            return
        }

        if (!checkbox){
            _errorMessage.value="Not Agree"
            return
        }


        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                if (it.user == null) {
                    _errorMessage.value = "User is Empty"
                } else {
                        it.user?.let { user ->
                            if (checkbox) {
                                _success.value = true
                                createProfile(user.uid, ad, email, password)

                        }
                    }
                }
            }
            .addOnFailureListener {
                _errorMessage.value = "Create Error"
            }


    }

    fun createProfile(token: String, ad: String, email: String, password: String) {
        val data = HashMap<String, Any>()
        data["userName"] = ad
        data["userEmail"] = email
        data["userPassword"] = password


        fireStore.collection("Profiles").document(token).set(data)
            .addOnSuccessListener {
            }
            .addOnFailureListener {
            }


    }


    fun login(email: String, password: String, checkbox: Boolean, sp: SharedPreferences) {

        if (email.isEmpty() || password.isEmpty()) {
            _errorMessage.value = "Fields Are Empty"
            return
        }

        if (!checkbox){
            _errorMessage.value="Not Agree"
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                if (checkbox) {
                    sp.edit().putString("token", it.user?.uid).apply()
                }
                _success.value = true
            }
            .addOnFailureListener {
                _errorMessage.value = "Email or Password is wrong"
            }

    }

    fun resetPassword(email: String) {
        auth.sendPasswordResetEmail(email)
            .addOnSuccessListener {
                _success.value = true
            }
            .addOnFailureListener {
                _errorMessage.value = "User is not Empty"
            }
    }


}








