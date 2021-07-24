package com.udacity.shoestore.models

import android.os.Parcelable
import androidx.databinding.BaseObservable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Shoe(
    var name: String,
    var size: Double,
    var company: String,
    var description: String,
    val images: List<String> = mutableListOf()


) : Parcelable, BaseObservable() {
    override fun toString(): String {
        return "\nName: $name\n" +
                "Company: $company\n" +
                "Size: $size\n" +
                "Description: $description"
    }
}