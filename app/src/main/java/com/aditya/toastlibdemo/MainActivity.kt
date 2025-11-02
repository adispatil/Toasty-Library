package com.aditya.toastlibdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aditya.toastlibdemo.ui.theme.ToastLibraryTheme
import com.aditya.toastylibrary.ToastHelper

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToastLibraryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ToastDemoScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ToastDemoScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Toast Library Demo",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        
        Button(
            onClick = {
                ToastHelper.showShort(context, "This is a short toast message!")
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Show Short Toast")
        }
        
        Button(
            onClick = {
                ToastHelper.showLong(context, "This is a long toast message that will stay on screen longer!")
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Show Long Toast")
        }
        
        Button(
            onClick = {
                ToastHelper.showShort(context, "Hello from ToastHelper!")
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Greeting Toast")
        }
        
        Button(
            onClick = {
                ToastHelper.showShort(context, "Success! Operation completed.")
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Success Toast")
        }
        
        Button(
            onClick = {
                ToastHelper.showLong(context, "Error occurred. Please try again later.")
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Error Toast (Long)")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToastDemoScreenPreview() {
    ToastLibraryTheme {
        ToastDemoScreen()
    }
}