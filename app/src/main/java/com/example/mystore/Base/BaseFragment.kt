package com.example.mystore.Base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment <VB:ViewBinding>(
    private val bindingInflater:(inflater:LayoutInflater)->VB
) : Fragment() {
    private var _binding:VB?=null
    val binding : VB get()=_binding!!

    abstract fun setup()
    abstract fun setClicks()
    abstract fun subscribe()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=bindingInflater.invoke(layoutInflater)
        if(_binding==null){
            throw IllegalStateException("binding is null")
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        setClicks()
        subscribe()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null

    }}

