package com.example.photoalbumapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.photoalbumapplication.model.MyImages
import com.example.photoalbumapplication.repository.MyImagesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyImagesViewModel(application: Application): AndroidViewModel(application) {

    var repository: MyImagesRepository
    var imageList: LiveData<List<MyImages>>

    init {
        repository = MyImagesRepository(application)
        imageList = repository.getAllImages()
    }

    fun insert(myImages: MyImages) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(myImages)
    }

    fun update(myImages: MyImages) = viewModelScope.launch(Dispatchers.IO){
        repository.update(myImages)
    }

    fun delete(myImages: MyImages) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(myImages)
    }

    fun getAllImages() : LiveData<List<MyImages>>{
        return imageList
    }

    suspend fun getItemById(id: Int): MyImages{
        return repository.getItemById(id)
    }
}