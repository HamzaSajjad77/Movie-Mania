package com.example.moviemania.db

import androidx.room.TypeConverter
import androidx.room.TypeConverters

@TypeConverters
class MovieTypeConverter {

    @TypeConverter
    fun fromListOfIntegersToString(integers: List<Int>?): String {
        return integers?.joinToString(separator = ",") ?: ""
    }

    @TypeConverter
    fun fromStringToListOfIntegers(data: String?): List<Int> {
        return data?.split(",")?.map { it.toInt() } ?: emptyList()
    }
}