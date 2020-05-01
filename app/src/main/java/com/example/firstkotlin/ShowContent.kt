package com.example.firstkotlin
import android.os.Bundle
import android.text.Html
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_show_content.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class ShowContent:YouTubeBaseActivity() {

    var onInitializedListener: YouTubePlayer.OnInitializedListener? = null
    internal lateinit var button:Button
    private lateinit var imageView:ImageView
    private var url1 = "https://meta.etherealwork.net/top-ten-website/"
    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_content)
        imageView = findViewById(R.id.back)
        imageView.setOnClickListener {
            finish()
        }
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        val title: String?  = intent.extras?.getString("title")
        val name: String? = intent.extras?.getString("name")
        val summary: String? = intent.extras?.getString("summary")
        val created: String? = intent.extras?.getString("created")
        val content: String? = intent.extras?.getString("content")
        val image: String? = intent.extras?.getString("image")
        val video: String? = intent.extras?.getString("video")

        contenttitle.text = title
        contentname.text=name
        contentcreated_at.text = created
        val document: Document = Jsoup.parse(content)


        // extracting text with html tags
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N)
        {
            showcontent.text = Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY)
        }
        else
        {
            showcontent.text = Html.fromHtml(content)
        }
        //  showcontent.text =  content    //document.text()  //can be for removing HTML tags......
        // set image using Glide
        Glide.with(this)
            .load(url1 + image)
            .into(contentimage)


        onInitializedListener = object: YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(provider:YouTubePlayer.Provider, youTubePlayer:YouTubePlayer, b:Boolean) {

                youTubePlayer.loadVideo(video)
            }
            override fun onInitializationFailure(provider: YouTubePlayer.Provider, youTubeInitializationResult: YouTubeInitializationResult) {
                Toast.makeText(this@ShowContent, "Sorry there is no video available", Toast.LENGTH_SHORT).show()
            }
        }
        buttonstart.setOnClickListener {
                if (video == null)
                {
                    Toast.makeText(this@ShowContent, "Sorry there is no video available", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    youtube.initialize(YouTubeConfig.getApi_key(), onInitializedListener)
                }
            }
    }

    }
