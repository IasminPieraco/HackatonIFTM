package com.example.iniciao_cientfica.telas

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iniciao_cientfica.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Login()
        }
    }
}

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login() {
    val email = remember { mutableStateOf("") }
    val senha = remember { mutableStateOf("") }
    val emailIconColor = remember { mutableStateOf(Color.Gray) }
    val senhaIconColor = remember { mutableStateOf(Color.Gray) }
    val senhaVisibility = remember { mutableStateOf(false) }
    val contexto = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFBBA6A6))
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.eduboot_logo),
                contentDescription = "Central Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(350.dp)
                    .clip(RoundedCornerShape(50.dp))
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text("E-mail") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .onFocusChanged { focusState ->
                        emailIconColor.value =
                            if (focusState.isFocused) Color(0xFF2463b3) else Color.Black
                    },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF2463b3),
                    unfocusedBorderColor = Color.Black,
                    focusedLabelColor = Color(0xFF2463b3),
                    unfocusedLabelColor = Color.Black,
                    cursorColor = Color.Black
                ),
                leadingIcon = {
                    Icon(painter = Icons.Default.Acc, contentDescription = )
                },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = senha.value,
                onValueChange = { senha.value = it },
                label = { Text("Senha") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .onFocusChanged { focusState ->
                        senhaIconColor.value =
                            if (focusState.isFocused) Color(0xFF0288d1) else Color.Black
                    },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF0288d1),
                    unfocusedBorderColor = Color.Black,
                    focusedLabelColor = Color(0xFF0288d1),
                    unfocusedLabelColor = Color.Black,
                    cursorColor = Color.Black
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Password,
                        contentDescription = "Registrar a senha do usuário",
                        tint = senhaIconColor.value
                    )
                },
                trailingIcon = {
                    IconButton(onClick = { senhaVisibility.value = !senhaVisibility.value }) {
                        Icon(
                            imageVector = if (senhaVisibility.value) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = if (senhaVisibility.value) "Ocultar senha" else "Mostrar senha",
                            tint = senhaIconColor.value
                        )
                    }
                },
                singleLine = true,
                visualTransformation = if (senhaVisibility.value) VisualTransformation.None else PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Esqueceu a senha?",
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 24.dp)
                    .clickable { },
                fontSize = 14.sp,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    contexto.startActivity(Intent(contexto, Tela_Principal::class.java))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(50.dp)
                    .clip(RoundedCornerShape(8.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0288d1))
            ) {
                Text(text = "Entrar", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xffe3e3e3))
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Cadastre-se",
                modifier = Modifier.clickable {
                    contexto.startActivity(Intent(contexto, Tela_Cadastro::class.java))
                },
                fontSize = 16.sp,
                color = Color(0xFF2463b3),
                textDecoration = TextDecoration.Underline,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))


            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "Google",
                    modifier = Modifier
                        .size(40.dp)
                        .clickable { },
                    tint = Color.Black
                )

                Icon(
                    painter = painterResource(id = R.drawable.linkedln),
                    contentDescription = "LinkedIn",
                    modifier = Modifier
                        .size(40.dp)
                        .clickable { },
                    tint = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(32.dp))


        }
    }
}
