package com.example.kotlin_omdbapi2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

class MovieResponse {

    @SerializedName("Response"     ) var Response     : String?           = null
    @SerializedName("Search"       ) var Search       : ArrayList<MovieResults> = arrayListOf()
    @SerializedName("totalResults" ) var totalResults : String?           = null
}


@Entity
data class MovieResults (
    @ColumnInfo(name = "id")
    @SerializedName("id"                 ) var id               : Int?                 = null,
    @ColumnInfo(name = "Title")
    @SerializedName("Title"  ) var Title  : String? = null,
    @ColumnInfo(name = "Year")
    @SerializedName("Year"   ) var Year   : String? = null,
    @ColumnInfo(name = "imdbID")
    @SerializedName("imdbID" ) var imdbID : String? = null,
    @ColumnInfo(name = "Type")
    @SerializedName("Type"   ) var Type   : String? = null,
    @ColumnInfo(name = "Poster")
    @SerializedName("Poster" ) var Poster : String? = null
){
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}