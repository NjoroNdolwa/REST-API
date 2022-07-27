package com.environment.restapi_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.environment.restapi_app.adapter.CharacterAdapter
import com.environment.restapi_app.api.RickyMorty
import com.environment.restapi_app.model.CharacterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val adapter by lazy { CharacterAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myProgressBar = findViewById<ProgressBar>(R.id.progressBar)
        val myRecyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        RickyMorty.api.getCharacters().enqueue(object :Callback<CharacterResponse>{
            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>
            ) {
                myProgressBar.isVisible= false
               if (response.isSuccessful){
                   adapter.submitList(response.body()?.results)
                   myRecyclerView.adapter = adapter

               }
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                myProgressBar.isVisible = false
            }

        })

    }
}