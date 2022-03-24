package com.mobile.azri.mvvmnewsapiwithcoroutine.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mobile.azri.mvvmnewsapiwithcoroutine.models.Article

@Database(
    entities = [Article::class],
    version = 2,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class ArticleDatabase : RoomDatabase(){

    abstract fun getArticleDao() : ArticleDao

    companion object{

        /*Volatile bermaksud di THREAD lain bisa melihat perubahan di THREAD yg lain
        ketika instance sebuah database
        */
        @Volatile
        private var instance: ArticleDatabase? = null

        /*variable LOCK berfungsi untuk memastikan instance database hanya dibikin 1 kali*/
        private val LOCK = Any()

        operator fun invoke(context:Context) = instance ?: synchronized(LOCK){
            /*ini berfungsi untuk benar-benar memastikan instance tidak membuat database berkali-kali
             dan tidak mempengaruhi variable instance jika instance null*/
            instance ?: createDatabase(context).also{ instance = it}
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
            "article_db.db"
            ).build()

    }

}