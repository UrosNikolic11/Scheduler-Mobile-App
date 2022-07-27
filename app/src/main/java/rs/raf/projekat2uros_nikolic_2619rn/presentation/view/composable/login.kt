package rs.raf.projekat2uros_nikolic_2619rn.presentation.view.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue


@Composable
fun LoginLayout(
    modifier: Modifier = Modifier,
    onClick: (String,String) -> Unit
)
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UsernameTextField(onClick)
    }
}

@Composable
fun UsernameTextField(onClick: (String, String) -> Unit) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = text,
        label = { Text(text = "Unesite ime") },
        onValueChange = {
            text = it
        }
    )
    PinTextField(onClick,text.text)
}

@Composable
fun PinTextField(onClick: (String, String) -> Unit, username: String) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = text,
        label = { Text(text = "Unesite pin") },
        onValueChange = {
            text = it
        }
    )
    Button(onClick = {
        onClick(username,text.text)
    }) {
        Text(text = "Log in")
    }
}

