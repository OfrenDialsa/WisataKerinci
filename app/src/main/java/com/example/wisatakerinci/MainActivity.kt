package com.example.wisatakerinci

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvDest: RecyclerView
    private val list = ArrayList<Destinasi>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rvDest = findViewById(R.id.rv_dest)
        rvDest.setHasFixedSize(true)

        list.addAll(getListDestination())
        showRecyclerList()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val moveIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(moveIntent)
            }
        }
        return true
    }

    @SuppressLint("Recycle")
    private fun getListDestination(): ArrayList<Destinasi> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataLocation = resources.getStringArray(R.array.data_location)
        val dataKodePos = resources.getStringArray(R.array.data_kode_pos)
        val listDest = ArrayList<Destinasi>()
        for (i in dataName.indices) {
            val dest = Destinasi(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1), dataLocation[i], dataKodePos[i])
            listDest.add(dest)
        }
        return listDest
    }


    private fun showSelectedDest(dest: Destinasi) {
            val detailActivityIntent = Intent(this@MainActivity, DetailActivity::class.java)
            detailActivityIntent.putExtra(DetailActivity.DESTINATION_KEY, dest)
            startActivity(detailActivityIntent)
    }


    private fun showRecyclerList() {
        rvDest.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListDestinasiAdapter(list)
        rvDest.adapter = listHeroAdapter

        listHeroAdapter.setOnItemClickCallback(object : ListDestinasiAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Destinasi) {
                showSelectedDest(data)
            }
        })
    }


}