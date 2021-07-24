package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeStoreViewModel: ViewModel() {

    private val _shoeList = MutableLiveData<ArrayList<Shoe>>()
    val shoeList: LiveData<ArrayList<Shoe>>
        get() = _shoeList

    private val _userList = MutableLiveData<HashMap<String, String>>()

    init {
        _shoeList.value = ArrayList()
        _userList.value = HashMap()
    }

    fun addShoeToList(shoe: Shoe) {
        _shoeList.value?.add(shoe)
    }

    fun registerUser(user: User): Boolean {
        if (_userList.value?.containsKey(user.email) == true) {
            return false
        }

        _userList.value?.put(user.email, user.password)
        return true
    }

    fun loginUser(user: User): Boolean {
        if (_userList.value?.containsKey(user.email) == false) {
            return false
        }

        if (!_userList.value?.get(user.email).equals(user.password)) {
            return false
        }

        return true
    }
}