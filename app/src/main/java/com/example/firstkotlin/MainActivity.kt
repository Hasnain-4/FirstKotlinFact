package com.example.firstkotlin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.final_factapp.MainAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: MainAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayoutManager=LinearLayoutManager(this)
        recyclerview.layoutManager=linearLayoutManager

       /* recyclerview.layoutManager=LinearLayoutManager(this)
        recyclerview.adapter=MainAdapter()*/

        fetchJson()
    }

    fun fetchJson(){
        val url="https://meta.etherealwork.net/top-ten-website/api/get-facts"

        val postlist: retrofit2.Call<Json4Kotlin_Base> = BloggerApi.getService().getpostList()

        postlist.enqueue(object : retrofit2.Callback<Json4Kotlin_Base>{
            override fun onFailure(call: retrofit2.Call<Json4Kotlin_Base>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failed to execute", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(

                call: retrofit2.Call<Json4Kotlin_Base>,
                response: retrofit2.Response<Json4Kotlin_Base>
            ) {

                var list = response.body()
                recyclerview.setAdapter(MainAdapter(this@MainActivity, list!!.posts))
                Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()


              /*  adapter = MainAdapter()

                recyclerview.adapter = adapter*/

            }


        })


    }
}
