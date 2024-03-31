package com.example.photoalbumapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_images")
class MyImages(
    val imageTitle: String,
    val imageDescription: String,

    //You can save images in few ways:
    //BLOB -> Binary Large Object
    //String -> Base64 format

    val imageAsString: String
) {

    @PrimaryKey(autoGenerate = true)
    var imageId = 0


}