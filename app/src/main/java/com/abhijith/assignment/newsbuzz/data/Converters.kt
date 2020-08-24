package com.abhijith.assignment.newsbuzz.data

import androidx.room.TypeConverter
import com.abhijith.assignment.newsbuzz.models.Source

// Room can handle only primitive datatypes. so we need to create a type
// converter class to tell room on how to interpret an non primitive type class. for eg: Source in Article
class Converters {

    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name:String): Source {
        return Source(name, name) // we are not bothered about id here... so passing it twice
    }
}