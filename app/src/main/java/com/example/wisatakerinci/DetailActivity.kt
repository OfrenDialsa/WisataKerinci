package com.example.wisatakerinci

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {

    companion object {
        const val DESTINATION_KEY = "destination_key"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val dataDest = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Destinasi>(DESTINATION_KEY, Destinasi::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Destinasi>(DESTINATION_KEY)
        }

        val ivDetailPhoto: ImageView = findViewById(R.id.img_det_photo)
        val tvDetJudul: TextView = findViewById(R.id.tv_det_judul)
        val tvDetDesc: TextView = findViewById(R.id.tv_det_description)
        val tvLoc: TextView = findViewById(R.id.tv_lokasi)
        val tvKodePos: TextView = findViewById(R.id.tv_kodePos)


        if (dataDest != null) {
            tvDetJudul.text = dataDest.title
            tvLoc.text = dataDest.lokasi
            tvKodePos.text = dataDest.kodePos
            tvDetDesc.text = dataDest.desc
            ivDetailPhoto.setImageResource(dataDest.photo)
        } else {
            tvDetJudul.text = "Kalo ini muncul berarti gagal"
            tvDetDesc.text = "dataDest.desc"
            //ivDetailPhoto.setImageResource(dataDest.photo)
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.share, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val dataDest = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Destinasi>(DESTINATION_KEY, Destinasi::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Destinasi>(DESTINATION_KEY)
        }



        when (item.itemId) {
            android.R.id.home->{
                finish()
            }
            R.id.action_share -> {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND_MULTIPLE
                    putExtra(Intent.EXTRA_TITLE, dataDest?.title)
                    putExtra(Intent.EXTRA_TEXT, "Berikut adalah ${dataDest?.title} \n ${dataDest?.desc}")
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
        }
        return true
    }
}