package com.rubenjromo.lcrdicegame

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    lateinit var mAdView : AdView
    private lateinit var mInterstitialAd: InterstitialAd



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this, getString(R.string.AdMobAppID))

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = getString(R.string.InsterstitialID)
        mInterstitialAd.loadAd(AdRequest.Builder().build())
        mInterstitialAd.adListener = object : AdListener() {
            override fun onAdClosed() {
                mInterstitialAd.loadAd(AdRequest.Builder().build())
            }
        }




    var numberOfTimesRolled = 0


        roll_button.setOnClickListener {
            rollDice()
            if (numberOfTimesRolled % 10 == 0 && mInterstitialAd.isLoaded) {
                mInterstitialAd.show()
            } else {
                Toast.makeText(this, "$numberOfTimesRolled.", Toast.LENGTH_SHORT).show()
            }
            numberOfTimesRolled ++
        }
    }

    fun rollDice(){
        val randomInt = Random.nextInt(6) + 1
        val drawableResource = when (randomInt){
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        dice_image.setImageResource(drawableResource)
    }

}
