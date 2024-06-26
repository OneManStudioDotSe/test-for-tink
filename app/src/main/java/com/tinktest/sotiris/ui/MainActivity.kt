package se.onemanstudio.test.tink.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import se.onemanstudio.test.tink.R

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setTheme(R.style.OneManTheme) //so that we can show the nice' splash screen'-like logo while the app is loading
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}