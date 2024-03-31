package com.example.photoalbumapplication.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.photoalbumapplication.model.MyImages
import com.example.photoalbumapplication.room.MyImagesDao
import com.example.photoalbumapplication.room.MyImagesDatabase

class MyImagesRepository(application: Application) {

    var myImagesDao: MyImagesDao

    var imageList: LiveData<List<MyImages>>

    init {
        val database = MyImagesDatabase.getDatabaseInstance(application)
        myImagesDao = database.myImagesDao()
        imageList = myImagesDao.getAllImages()
    }

    suspend fun insert(myImages: MyImages){
        myImagesDao.insert(myImages)
    }

    suspend fun update(myImages: MyImages){
        myImagesDao.update(myImages)
    }

    suspend fun delete(myImages: MyImages){
        myImagesDao.delete(myImages)
    }

    fun getAllImages(): LiveData<List<MyImages>>{
        return imageList
    }

    suspend fun getItemById(id: Int): MyImages{
        return myImagesDao.getItemById(id)
    }

}