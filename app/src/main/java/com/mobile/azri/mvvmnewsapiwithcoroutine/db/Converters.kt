package com.mobile.azri.mvvmnewsapiwithcoroutine.db

import androidx.room.TypeConverter
import com.mobile.azri.mvvmnewsapiwithcoroutine.models.Source

/*Class converter ini berfungsi untuk convert tipe data
dari suatu object ke tipe data primitif(int,string,booelan, dll),
karena pada RoomDB hanya mengenal tipe data primitif di suatu Object,
seperti di Article terdapat object source : Source, tipe data Source adalah custom data
yg juga merupakan model dari suatu object data
* */
class Converters {

    @TypeConverter
    fun fromSource(source: Source): String{
        //sample data yg akan di convert adalah source.name
        return source.name
    }

    @TypeConverter
    fun toSource(name:String): Source{
        return Source(name,name)
    }

}