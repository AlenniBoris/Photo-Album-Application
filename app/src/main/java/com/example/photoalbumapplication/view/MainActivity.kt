package com.example.photoalbumapplication.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.photoalbumapplication.adapter.MyImagesAdapter
import com.example.photoalbumapplication.databinding.ActivityMainBinding
import com.example.photoalbumapplication.viewmodel.MyImagesViewModel

class MainActivity : AppCompatActivity() {

    lateinit var myImagesViewModel: MyImagesViewModel
    lateinit var mainBinding: ActivityMainBinding
    lateinit var myImagesAdapter: MyImagesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(mainBinding.root)

        myImagesViewModel = ViewModelProvider(this)[MyImagesViewModel::class.java]

        mainBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        myImagesAdapter = MyImagesAdapter(this)
        mainBinding.recyclerView.adapter = myImagesAdapter

        myImagesViewModel.getAllImages().observe(this, Observer {images ->

            //Update UI
            myImagesAdapter.setImage(images)

        })

        mainBinding.floatingActionButton.setOnClickListener{
            var intent : Intent = Intent(this, AddImageActivity::class.java)
            startActivity(intent)
        }

        ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                //dialog message or snackbar message
                myImagesViewModel.delete(myImagesAdapter.returnItemAtGivenPosition(viewHolder.adapterPosition))
            }

        }).attachToRecyclerView(mainBinding.recyclerView)
    }
}