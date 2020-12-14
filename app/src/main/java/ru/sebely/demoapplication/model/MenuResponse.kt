package ru.sebely.demoapplication.model

import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

//
// @Parcelize shouldn't be here at all
//

@Parcelize
data class Product(
    val id : Int,
    val title : String,
    val price : Double,
    val description :  String,
    val parameters : String,
    val image : String
) : Parcelable

@Parcelize
data class MenuCategory(
    val id : Int,
    val categoryName: String,
    val products: List<Product>
) : Parcelable

data class MenuResponse(
    val categories: List<MenuCategory>
)