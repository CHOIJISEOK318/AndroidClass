package com.example.state

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
        }
    }
}

@Composable
fun HomeScreen(viewModel: MainViewModel = viewModel()) {
    val text1: MutableState<String> = remember {
        mutableStateOf("Hello World!")
    }

    var text2: String by remember {
        mutableStateOf("Hello World!")
    }

    val(text: String, setText:(String) -> Unit) = remember{
        mutableStateOf("Hello World!")
    }

    Column {
        Text(text = "Hello World!")
        Button(onClick = {
            text1.value = "변경"
            print(message = text1.value)
            text2 = "변경"
            print(text2)
            setText("변경")
            viewModel.changeValue("변경")
        }) {
            Text(text = "클릭")
        }

    }

    TextField(value = text, onValueChange = setText)

    val text3: State<String> = viewModel.liveData.observeAsState("Hello World!")
    
}

class MainViewModel: ViewModel(){
    private val _value: MutableState<String> = mutableStateOf("Hello World")
    val value: State<String> = _value

    private val _liveData = MutableLiveData<String>()
    val liveData = _liveData

    fun changeValue(value: String){
        _value.value = value
    }
}
