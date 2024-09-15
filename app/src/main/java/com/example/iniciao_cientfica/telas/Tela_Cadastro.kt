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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Phone
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iniciao_cientfica.R

class Tela_Cadastro : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Cadastro()
        }
    }
}

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Cadastro() {
    val nome = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val senha = remember { mutableStateOf("") }
    val telefone = remember { mutableStateOf("") }
    val contexto = LocalContext.current

    val emailIconColor = remember { mutableStateOf(Color.Gray) }
    val senhaIconColor = remember { mutableStateOf(Color.Gray) }
    val nomeIconColor = remember { mutableStateOf(Color.Gray) }
    val telefoneIconColor = remember { mutableStateOf(Color.Gray) }
    val senhaVisibility = remember { mutableStateOf(false) }

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
                contentDescription = "Logo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(350.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .padding(bottom = 30.dp)
            )

            OutlinedTextField(
                value = nome.value,
                onValueChange = { nome.value = it },
                label = { Text("Nome") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .onFocusChanged { focusState ->
                        nomeIconColor.value = if (focusState.isFocused) Color(0xFF2463b3) else Color.Black
                    },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF2463b3),
                    unfocusedBorderColor = Color.Black,
                    focusedLabelColor = Color(0xFF2463b3),
                    unfocusedLabelColor = Color.Black,
                    cursorColor = Color.Black
                ),
                leadingIcon = {
                    Icon(Icons.Default.AccountCircle, contentDescription ="" , tint = Color.Black)
                },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text("Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .onFocusChanged { focusState ->
                        emailIconColor.value = if (focusState.isFocused) Color(0xFF2463b3) else Color.Black
                    },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF2463b3),
                    unfocusedBorderColor = Color.Black,
                    focusedLabelColor = Color(0xFF2463b3),
                    unfocusedLabelColor = Color.Black,
                    cursorColor = Color.Black
                ),
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.email),
                        contentDescription = "Registrar o usuário",
                        colorFilter = ColorFilter.tint(emailIconColor.value)
                    )
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
                    .clip(RoundedCornerShape(12.dp))
                    .onFocusChanged { focusState ->
                        senhaIconColor.value = if (focusState.isFocused) Color(0xFF2463b3) else Color.Black
                    },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF2463b3),
                    unfocusedBorderColor = Color.Black,
                    focusedLabelColor = Color(0xFF2463b3),
                    unfocusedLabelColor = Color.Black,
                    cursorColor = Color.Black
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Password,
                        contentDescription = "Registrar o usuário",
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

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = telefone.value,
                onValueChange = { newValue ->
                    if (newValue.length <= 11) {
                        telefone.value = newValue.filter { it.isDigit() }
                    }
                },
                label = { Text("Telefone") },
                visualTransformation = PhoneVisualTransformation(),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .onFocusChanged { focusState ->
                        telefoneIconColor.value = if (focusState.isFocused) Color(0xFF2463b3) else Color.Black
                    },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF2463b3),
                    unfocusedBorderColor = Color.Black,
                    focusedLabelColor = Color(0xFF2463b3),
                    unfocusedLabelColor = Color.Black,
                    cursorColor = Color.Black
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = "Registrar o usuário",
                        tint = telefoneIconColor.value
                    )
                }
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
                    .clip(RoundedCornerShape(12.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0288d1))
            ) {
                Text(text = "Cadastrar", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Já tem uma conta? Entre",
                modifier = Modifier.clickable {
                    contexto.startActivity(Intent(contexto, MainActivity::class.java))
                },
                fontSize = 14.sp,
                color = Color(0xFF2463b3)
            )
        }
    }
}

class PhoneVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.text.length >= 11) text.text.substring(0, 11) else text.text
        val out = StringBuilder()

        for (i in trimmed.indices) {
            if (i == 0) out.append("(")
            else if (i == 2) out.append(") ")
            else if (i == 7) out.append("-")
            out.append(trimmed[i])
        }

        val transformedText = out.toString()

        val offsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return when {
                    offset == 0 -> 1
                    offset <= 2 -> offset + 1
                    offset <= 7 -> offset + 3
                    else -> offset + 4
                }.coerceAtMost(transformedText.length)
            }

            override fun transformedToOriginal(offset: Int): Int {
                return when {
                    offset <= 1 -> 0
                    offset <= 5 -> offset - 1
                    offset <= 10 -> offset - 3
                    else -> offset - 4
                }.coerceAtLeast(0)
            }
        }

        return TransformedText(AnnotatedString(transformedText), offsetTranslator)
    }
}

