package com.android.multicolored_circles

import android.content.Context
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.android.multicolored_circles.databinding.ActivityMainBinding
import com.android.multicolored_circles.viewmodel.MyViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var myViewModel: MyViewModel
    lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        mediaPlayer = MediaPlayer.create(this, R.raw.multicolored_circles_audio)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {

        myViewModel.sound.observe(this) {

            if(myViewModel.sound.value == true) {
                mediaPlayer.start()
            } else {
                mediaPlayer.pause()
            }
        }
        return super.onCreateView(name, context, attrs)
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer.pause()
    }

    override fun onResume() {
        super.onResume()
        mediaPlayer.start()
    }
}
