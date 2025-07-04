package com.example.r8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.r8.fake.Fake
import com.example.r8.impl.FirstImpl
import com.example.r8.impl.SecondImpl
import com.example.r8.ui.theme.R8Theme

class MainActivity : ComponentActivity() {

    private  val firstImpl=FirstImpl()
    private val secondImpl=SecondImpl()
    private val fake= Fake (name="Dimalsha")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            R8Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    myCustomFunction()
                    Greeting(
                        name = fake.name.plus(firstImpl.getCustomMessage()).plus(secondImpl.getCustomMessage()),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
    fun myCustomFunction(){
        val p="vvchbhcsbjbj"
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    R8Theme {
        Greeting("Android")
    }
}