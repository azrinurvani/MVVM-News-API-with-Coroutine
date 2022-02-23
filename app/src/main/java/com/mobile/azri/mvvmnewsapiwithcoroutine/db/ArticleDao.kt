package com.mobile.azri.mvvmnewsapiwithcoroutine.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mobile.azri.mvvmnewsapiwithcoroutine.models.Article

@Dao
interface ArticleDao {

    /*onConflict berfungsi untuk memberi action di database apabila insert data baru,
     jika terdapat data lama maka akan di replace
    * */

    /*pada coroutine wajib memakai method suspend*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: Article) : Long

    /*Method dengan return Live Data tidak bisa bekerja di method suspend*/
    @Query("SELECT * FROM articles")
    fun getAllArticles() : LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}