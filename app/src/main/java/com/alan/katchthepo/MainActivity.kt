package com.alan.katchthepo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var score=0
    var imageArray=ArrayList<ImageView>()
    var handler=Handler()
    var runnable=Runnable{ }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView:ImageView=findViewById(R.id.imageView)
        val imageView2:ImageView=findViewById(R.id.imageView2)
        val imageView3:ImageView=findViewById(R.id.imageView3)
        val imageView4:ImageView=findViewById(R.id.imageView4)
        val imageView5:ImageView=findViewById(R.id.imageView5)
        val imageView6:ImageView=findViewById(R.id.imageView6)
        val imageView7:ImageView=findViewById(R.id.imageView7)
        val imageView8:ImageView=findViewById(R.id.imageView8)
        val imageView9:ImageView=findViewById(R.id.imageView9)

        imageArray.add(imageView)
        imageArray.add(imageView2)
        imageArray.add(imageView3)
        imageArray.add(imageView4)
        imageArray.add(imageView5)
        imageArray.add(imageView6)
        imageArray.add(imageView7)
        imageArray.add(imageView8)
        imageArray.add(imageView9)

        hideImages()  // burda tanimladik ki oncreate acildigi gibi gorselller gizlensin

      //  imageView.visibility=View.INVISIBLE  imageview i gorunmez yapti

        //CountDown Timer
        object :CountDownTimer(15100,1000){
            override fun onTick(millisUntilFinished: Long) {
                val timeText:TextView=findViewById(R.id.timeText)
                timeText.text="Time: "+millisUntilFinished/1000
            }

            override fun onFinish() {
                val timeText:TextView=findViewById(R.id.timeText)
                timeText.text="Time:0"

                handler.removeCallbacks(runnable)

                for(image in imageArray){
                    image.visibility=View.INVISIBLE
                }
                //alert
                val alert=AlertDialog.Builder(this@MainActivity)

                alert.setTitle("Game Over")
                alert.setMessage("Restart The Game")
                alert.setPositiveButton("Yes"){ dialog, which->
                    //Restart
                    val intent=intent
                    finish()
                    startActivity(intent) // Activityi bastan calistirir

                }
                alert.setNegativeButton("No"){ dialog, which->
                    Toast.makeText(this@MainActivity,"Game Over",Toast.LENGTH_LONG).show()
                }
                alert.show()

            }

        }.start()

    }


    fun hideImages(){  //gorselleri gizlemek icin fonksiyon tanimladik

        runnable=object : Runnable{
            override fun run() {  // run fonksiyonunu cagirmamiz gerekiyor

                for (image in imageArray){
                    image.visibility=View.INVISIBLE
                }

                val random= Random()
                val randomIndex=random.nextInt(9) // 0 ile 8 arasinda rastgele bir indeks verecek(int tipinde)
                imageArray[randomIndex].visibility=View.VISIBLE

                handler.postDelayed(runnable,500)
            }
        }
    handler.post(runnable)

    }

    fun increaseScore(view:View){
        val scoreText:TextView=findViewById(R.id.scoreText)
        score= score+1
        scoreText.text="Score: $score "
    }


}