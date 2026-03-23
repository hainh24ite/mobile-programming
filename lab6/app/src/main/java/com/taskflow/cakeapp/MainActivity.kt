package com.taskflow.cakeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.taskflow.cakeapp.ui.theme.CakeappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CakeappTheme {
                CupcakeApp()
            }
        }
    }
}