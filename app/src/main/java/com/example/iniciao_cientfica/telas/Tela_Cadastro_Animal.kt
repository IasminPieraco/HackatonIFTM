package com.example.iniciao_cientfica.telas

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Vaccines
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iniciao_cientfica.R
import com.example.iniciao_cientfica.banco.BancoDados
import com.example.iniciao_cientfica.classes.Animal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random


class Tela_Cadastro_Animal : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeuApp {
                TelaCentro(intent)
            }
        }
    }
}

@Composable
fun MeuApp(content: @Composable () -> Unit) {
    MaterialTheme {
        Surface(color = Color.Black) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.DarkGray)
            ) {
                content()
            }
        }
    }
}

@Composable
fun TelaCentro(intent: Intent) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val chave = remember { mutableStateOf(gerarChaveAleatoria()) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Navigation(drawerState, scope)
        }
    ) {
        Scaffold(
            topBar = {
                ToppBar(drawerState, scope)
            },
            content = { innerPadding ->
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.terra),
                        contentDescription = "Imagem de fundo",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )
                    val scrollState = rememberScrollState()
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .verticalScroll(scrollState)
                    ) {
                        CameraPicker()
                        IconBox()
                        Spacer(modifier = Modifier.height(15.dp))
                        ChaveAleatoria(chave)
                        ConteudoAnimal(inner = innerPadding, intent, chave)
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        )
                        {

                        }
                        Box(
                            modifier = Modifier.align(Alignment.BottomStart),
                            contentAlignment = Alignment.Center
                        ) {
                            RoundedButton(iconId = R.drawable.cegonha) {
                            }
                        }
                    }
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToppBar(drawerState: DrawerState, scope: CoroutineScope) {
    val contextoVoltar = LocalContext.current
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    scope.launch {
                        if (drawerState.isOpen) {
                            drawerState.close()
                        } else {
                            drawerState.open()
                        }
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Navigation",
                        tint = Color.White
                    )
                }
                Text(
                    text = "Cadastro Animal",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f),
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)

                )
                IconButton(onClick = { contextoVoltar.startActivity(Intent(contextoVoltar, Tela_Principal::class.java))}) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBackIosNew,
                        contentDescription = "Voltar",
                        tint = Color.White
                    )
                }
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color(0xFF3D7C17),
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White,
            actionIconContentColor = Color.White
        )
    )
}

@Composable
fun CameraPicker() {
    val contexto = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            imageUri = it
            bitmap = MediaStore.Images.Media.getBitmap(contexto.contentResolver, it)
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .clickable { launcher.launch("image/*") }
                .background(Color.Gray)
                .padding(6.dp)
        ) {
            if (bitmap != null) {
                Image(
                    bitmap = bitmap!!.asImageBitmap(),
                    contentDescription = "Foto do Cliente",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.camera),
                    contentDescription = "Foto do Cliente",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .scale(0.5f)
                )
            }
        }
    }
}

@Composable
fun Navigation(drawerState: DrawerState, scope: CoroutineScope) {
    val contexto = LocalContext.current
    ModalDrawerSheet(
        modifier = Modifier
            .fillMaxWidth(0.75f)
            .background(Color(0xFF3D7C17))
    ) {
        Column(
            modifier = Modifier
                .background(Color(0xFF3D7C17))
                .fillMaxSize()
                .padding(10.dp)
        ) {
            IconButton(onClick = {
                scope.launch {
                    drawerState.close()
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = Color.Black,
                    modifier = Modifier.size(40.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = "Logo",
                    tint = Color.Black,
                    modifier = Modifier.size(250.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            //Início
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 25.dp)
                    .clickable {
                        contexto.startActivity(
                            Intent(
                                contexto,
                                Tela_Principal::class.java
                            )
                        )
                    }
            ) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Início",
                    tint = Color.Black,
                    modifier = Modifier.size(25.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text("Início", color = Color.Black, fontSize = 25.sp)
            }
            //Vacinas
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 25.dp)
                    .clickable { contexto.startActivity(Intent(contexto, Mostrar_Vacina::class.java)) }
            ) {
                Icon(
                    imageVector = Icons.Default.Vaccines,
                    contentDescription = "Vacinas",
                    tint = Color.Black,
                    modifier = Modifier.size(25.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text("Vacinas", color = Color.Black, fontSize = 25.sp)
            }
            //Eventos
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 25.dp)
                    .clickable { contexto.startActivity(Intent(contexto, Mostrar_Evento::class.java)) }
            ) {
                Icon(
                    imageVector = Icons.Default.Event,
                    contentDescription = "Eventos",
                    tint = Color.Black,
                    modifier = Modifier.size(25.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text("Eventos", color = Color.Black, fontSize = 25.sp)
            }
            //Doenças
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 25.dp)
                    .clickable { contexto.startActivity(Intent(contexto, Mostrar_Doenca::class.java)) }
            ) {
                Icon(
                    imageVector = Icons.Default.HealthAndSafety,
                    contentDescription = "Doenças",
                    tint = Color.Black,
                    modifier = Modifier.size(25.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text("Doenças", color = Color.Black, fontSize = 25.sp)
            }
            //Conta
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 25.dp)
                    .clickable { contexto.startActivity(Intent(contexto, Tela_Conta::class.java)) }
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Conta",
                    tint = Color.Black,
                    modifier = Modifier.size(25.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text("Conta", color = Color.Black, fontSize = 25.sp)
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Usuário: Iasmin",
                    color = Color.Black,
                    fontSize = 18.sp,
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = {
                    contexto.startActivity(Intent(contexto, MainActivity::class.java))
                }) {
                    Icon(
                        imageVector = Icons.Default.ExitToApp,
                        contentDescription = "Sair",
                        tint = Color.Black,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
        }
    }
}





@Composable
fun RoundedButton(iconId: Int, onClick: () -> Unit) {
    Box(modifier = Modifier.padding(16.dp)) {
        FloatingActionButton(
            onClick = onClick,
            containerColor = Color(0xFF3D7C17),
            modifier = Modifier.size(50.dp),
            shape = RoundedCornerShape(percent = 50)
        ) {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = "Round Button Icon",
                tint = Color(0xFF000000),
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

fun gerarChaveAleatoria(): String {
    return List(9) { Random.nextInt(0, 10) }.joinToString("")
}

@Composable
fun ChaveAleatoria(chave: MutableState<String>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 1.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.LightGray)
            .border(1.dp, Color.Gray.copy(alpha = 0.5f), RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Key,
                contentDescription = null,
                modifier = Modifier.size(32.dp),
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = chave.value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
        }
    }
}

@Composable
fun IconBox() {
    val contextoBox = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(color = Color(0x0F44FF00))
            .shadow(1.dp, RoundedCornerShape(1.dp), clip = true)
            .border(1.dp, Color.Gray, RoundedCornerShape(15.dp)),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            FloatingActionButton(
                onClick = { contextoBox.startActivity(Intent(contextoBox, Tela_Doença::class.java)) },
                containerColor = Color(0xFFAE6627),
                shape = CircleShape,
                modifier = Modifier.size(60.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.doenca),
                    contentDescription = "Evento",
                    tint = Color(0xFF000000),
                    modifier = Modifier.size(30.dp)
                )
            }

            FloatingActionButton(
                onClick = { contextoBox.startActivity(Intent(contextoBox, Tela_Vacina::class.java)) },
                containerColor = Color(0xFFAE6627),
                shape = CircleShape,
                modifier = Modifier.size(60.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.vacina),
                    contentDescription = "Vacina",
                    tint = Color(0xFF000000),
                    modifier = Modifier.size(30.dp)
                )
            }

            FloatingActionButton(
                onClick = { contextoBox.startActivity(Intent(contextoBox, Tela_Evento::class.java)) },
                containerColor = Color(0xFFAE6627),
                shape = CircleShape,
                modifier = Modifier.size(60.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.calendario),
                    contentDescription = "Evento",
                    tint = Color(0xFF000000),
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConteudoAnimal(inner: PaddingValues, intent: Intent, chave: MutableState<String>) {
    val animal: Animal? = intent.getParcelableExtra("animal")

    var id_animal by remember { mutableStateOf(TextFieldValue(chave.value)) }
    var raca by remember { mutableStateOf(TextFieldValue(animal?.raca ?: "")) }
    var sexo by remember { mutableStateOf(TextFieldValue(animal?.sexo ?: "")) }
    var data_nascimento by remember { mutableStateOf(TextFieldValue(animal?.data_nascimento ?: "")) }
    var peso by remember { mutableStateOf(TextFieldValue(animal?.peso ?: "")) }
    var pastagem by remember { mutableStateOf(TextFieldValue(animal?.pastagem ?: "")) }

    var TipoIconColor = remember { mutableStateOf(Color.Gray) }
    var RacaIconColor = remember { mutableStateOf(Color.Gray) }
    var SexoIconColor = remember { mutableStateOf(Color.Gray) }
    var NasciIconColor = remember { mutableStateOf(Color.Gray) }
    var PesoIconColor = remember { mutableStateOf(Color.Gray) }
    var PastagemIconColor = remember { mutableStateOf(Color.Gray) }

    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(animal?.raca ?: "Tipo de Raça") }
    val items = listOf("Gado de Corte", "Gado de Leite")
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp), //  .padding(innerPadding)

    ) {
        Column {
            OutlinedTextField(
                value = selectedText,
                onValueChange = { },
                label = { Text("Tipo de Gado") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .onFocusChanged { focusState ->
                        TipoIconColor.value =
                            if (focusState.isFocused) Color(0xFFAE6627) else Color.Gray
                    }
                    .clickable { expanded = !expanded },
                readOnly = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFFAE6627),
                    unfocusedBorderColor = Color.Gray,
                    focusedLabelColor = Color(0xFFAE6627),
                    unfocusedLabelColor = Color.Gray,
                    cursorColor = Color.Black
                ),
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.tiporaca),
                        contentDescription = "Select",
                        colorFilter = ColorFilter.tint(TipoIconColor.value)
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = if (expanded) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown,
                        contentDescription = "Dropdown Icon",
                        tint = TipoIconColor.value
                    )
                },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = raca,
                onValueChange = { raca = it },
                label = { Text("Raça") },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.raca),
                        contentDescription = "Search",
                        colorFilter = ColorFilter.tint(RacaIconColor.value)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .onFocusChanged { focusState ->
                        RacaIconColor.value =
                            if (focusState.isFocused) Color(0xFFAE6627) else Color.Gray
                    },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFFAE6627),
                    unfocusedBorderColor = Color.Gray,
                    focusedLabelColor = Color(0xFFAE6627),
                    unfocusedLabelColor = Color.Gray,
                    cursorColor = Color.Black
                ),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = sexo,
                onValueChange = { sexo = it },
                label = { Text("Sexo") },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.sexo),
                        contentDescription = "Gender",
                        colorFilter = ColorFilter.tint(SexoIconColor.value)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .onFocusChanged { focusState ->
                        SexoIconColor.value =
                            if (focusState.isFocused) Color(0xFFAE6627) else Color.Gray
                    },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFFAE6627),
                    unfocusedBorderColor = Color.Gray,
                    focusedLabelColor = Color(0xFFAE6627),
                    unfocusedLabelColor = Color.Gray,
                    cursorColor = Color.Black
                ),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = data_nascimento,
                onValueChange = { data_nascimento = it },
                label = { Text("Nasci/Compra") },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.nasci),
                        contentDescription = "Calendar",
                        colorFilter = ColorFilter.tint(NasciIconColor.value)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .onFocusChanged { focusState ->
                        NasciIconColor.value =
                            if (focusState.isFocused) Color(0xFFAE6627) else Color.Gray
                    },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFFAE6627),
                    unfocusedBorderColor = Color.Gray,
                    focusedLabelColor = Color(0xFFAE6627),
                    unfocusedLabelColor = Color.Gray,
                    cursorColor = Color.Black
                ),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = peso,
                onValueChange = { peso = it },
                label = { Text("Peso") },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.peso),
                        contentDescription = "Weight",
                        colorFilter = ColorFilter.tint(PesoIconColor.value)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .onFocusChanged { focusState ->
                        PesoIconColor.value =
                            if (focusState.isFocused) Color(0xFFAE6627) else Color.Gray
                    },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFFAE6627),
                    unfocusedBorderColor = Color.Gray,
                    focusedLabelColor = Color(0xFFAE6627),
                    unfocusedLabelColor = Color.Gray,
                    cursorColor = Color.Black
                ),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = pastagem,
                onValueChange = { pastagem = it },
                label = { Text("Pastagem") },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.pastagem),
                        contentDescription = "Pasture",
                        colorFilter = ColorFilter.tint(PastagemIconColor.value)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .onFocusChanged { focusState ->
                        PastagemIconColor.value =
                            if (focusState.isFocused) Color(0xFFAE6627) else Color.Gray
                    },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFFAE6627),
                    unfocusedBorderColor = Color.Gray,
                    focusedLabelColor = Color(0xFFAE6627),
                    unfocusedLabelColor = Color.Gray,
                    cursorColor = Color.Black
                ),
                singleLine = true
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            )
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//            )

            {
                FloatingActionButton(
                    onClick = {
                        val db = BancoDados()
                        CoroutineScope(Dispatchers.IO).launch {
                            db.Cadastro_Animal(
                                Animal(
                                    id_animal.text,
                                    raca.text,
                                    sexo.text,
                                    data_nascimento.text,
                                    peso.text,
                                    pastagem.text
                                )
                            )
                            val intent = Intent(context, Tela_Principal::class.java)
                            context.startActivity(intent)
                        }
                    },
                    containerColor = Color(0xFF3D7C17),
                    modifier = Modifier
                    //    .align(Alignment.BottomEnd)
                        .padding(16.dp)
                        .width(150.dp)
                        .height(36.dp),
                ) {
                    Text("Confirmar", color = Color.Black)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TopBarPreviewAnimal() {
    MeuApp {
        TelaCentro(intent = Intent())
    }
}
