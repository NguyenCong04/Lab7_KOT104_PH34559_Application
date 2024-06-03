package com.congntph34559.fpoly.lab7_kot104_ph34559_application

import android.os.Bundle
import android.util.Log.v
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.congntph34559.fpoly.lab7_kot104_ph34559_application.model.MainViewModel
import com.congntph34559.fpoly.lab7_kot104_ph34559_application.model.Movie
import com.congntph34559.fpoly.lab7_kot104_ph34559_application.ui.navigation.GetLayoutScreenNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            GetLayoutScreenNavigation()
        }
    }
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

}