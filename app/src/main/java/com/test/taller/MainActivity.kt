package com.test.taller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.test.taller.ui.theme.TallerTheme
import kotlinx.coroutines.launch

data class Item(
    val title: String,
    val description: String
)

val fakeList = listOf(
    Item(
        title = "Cake 1",
        description = "Cake Description 1"
    ),
    Item(
        title = "Cake 2",
        description = "Cake Description 2"
    ),
    Item(
        title = "Cake 3",
        description = "Cake Description 3"
    ),
    Item(
        title = "Cake 4",
        description = "Cake Description 4"
    ),
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TallerTheme {
                // A surface container using the 'background' color from the theme
                val snackBar = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FakeList(
                        snackBarHost = snackBar,
                        didTapItem = {
                            scope.launch {
                                snackBar.showSnackbar(
                                    message = it,
                                    duration = SnackbarDuration.Short
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun FakeList(
    snackBarHost: SnackbarHostState,
    didTapItem: (String) -> Unit
) {
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHost) }
    ) {
        LazyColumn(
            modifier = Modifier.padding(it)
        ) {
            items(fakeList) { item ->
                Box(
                    modifier = Modifier
                        .clickable { didTapItem(item.title) }
                        .padding(8.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Column {
                        Text(text = item.title)
                        Text(text = item.description)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TallerTheme {
//        FakeList()
    }
}