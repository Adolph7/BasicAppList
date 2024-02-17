package com.example.basicapplist
import android.graphics.Paint.Style
import android.graphics.fonts.FontFamily
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.basicapplist.ui.theme.BasicAppListTheme
import java.time.format.TextStyle


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    var collecion by remember { mutableStateOf(listOf<String>("a","c","c")) }
    var itemToAdd by remember { mutableStateOf("") }


    BasicAppListTheme {

        Surface(modifier = Modifier
            .fillMaxSize(),
            color = MaterialTheme.colorScheme.background) {

            Column (modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)) {
                //Text(text = collecion[1])
                // Text(text = collecion[2])

                TextField(value = itemToAdd, onValueChange = {itemToAdd = it},
                    modifier = Modifier.fillMaxWidth())

                Button(onClick = {
                    collecion = collecion + itemToAdd
                    itemToAdd =""
                }) {
                    Text(text = "Add")
                }
                Spacer(modifier = Modifier.width(8.dp))

                if (collecion.isEmpty()) {
                    Text(text = "Nothing to do yet")
                }

                LazyColumn{
                    items(collecion.size, itemContent = {index ->
                        todoItem(item = collecion[index], onItemDelete = {
                            collecion = collecion - it
                        })

                    })
                }

                // for (item in collecion){
                //    todoItem(item = item)
                // }
            }
        }
    }
}

@Composable
fun todoItem(item: String, onItemDelete: (String)-> Unit) {
    var isChecked by remember { mutableStateOf(false) }
    Row (modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = isChecked, onCheckedChange = {
            isChecked= it
        })
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = item,)

        Spacer(modifier = Modifier.weight(1f))

        IconButton(onClick = {
            onItemDelete(item)
        }) {
            Icon(imageVector = Icons.TwoTone.Delete, contentDescription = "delete",
                modifier = Modifier.padding(PaddingValues(end=8.dp)))
        }
    }


}


@Preview(showBackground = true, widthDp = 360, name = "Collection")
@Composable
fun AppPrev() {
    App()

}
