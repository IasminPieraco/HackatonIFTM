package com.example.iniciao_cientfica.telas

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
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
import com.example.iniciao_cientfica.classes.Doenca_Animal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

class Tela_Doença : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeuAppDoenca {
                TelaCentroDoenca(intent)
            }
        }
    }
}

@Composable
fun MeuAppDoenca(content: @Composable () -> Unit) {
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
fun TelaCentroDoenca(intent: Intent) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val chave = remember { mutableStateOf(gerarChaveAleatoriaDoenca()) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavigationDoenca(drawerState, scope)
        }
    ) {
        Scaffold(
            topBar = {
                ToppBarDoenca(drawerState, scope)
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
                        Spacer(modifier = Modifier.height(55.dp))
                        IconBoxDoenca()
                        Spacer(modifier = Modifier.height(55.dp))
                        ChaveAleatoriaDoenca(chave)
                        ConteudoDoenca(inner = innerPadding, intent, chave)
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
                        ) {
                        }
                    }
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToppBarDoenca(drawerState: DrawerState, scope: CoroutineScope) {
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
                    text = "Doença",
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
fun NavigationDoenca(drawerState: DrawerState, scope: CoroutineScope) {
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
                    .clickable {  contexto.startActivity(Intent(contexto, Tela_Principal::class.java)) }
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
                    .clickable {  contexto.startActivity(Intent(contexto, Mostrar_Vacina::class.java)) }
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
                    .clickable {  contexto.startActivity(Intent(contexto, Mostrar_Evento::class.java)) }
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
                    .clickable {  contexto.startActivity(Intent(contexto, Mostrar_Doenca::class.java)) }
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
                    .clickable {  contexto.startActivity(Intent(contexto, Tela_Conta::class.java)) }
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


fun gerarChaveAleatoriaDoenca(): String {
    return List(9) { Random.nextInt(0, 10) }.joinToString("")
}

@Composable
fun ChaveAleatoriaDoenca(chave: MutableState<String>) {
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
fun IconBoxDoenca() {
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
fun ConteudoDoenca(inner: PaddingValues, intent: Intent, chave: MutableState<String>) {
    val NomeIconColor = remember { mutableStateOf(Color.Gray) }
    val AplicacaoIconColor = remember { mutableStateOf(Color.Gray) }
    val VencimentoIconColor = remember { mutableStateOf(Color.Gray) }
    val NomeDIconColor = remember { mutableStateOf(Color.Gray) }
    val MedicamentoIconColor = remember { mutableStateOf(Color.Gray) }
    val DescricaoIconColor = remember { mutableStateOf(Color.Gray) }

    val doencaAnimal: Doenca_Animal? = intent.getParcelableExtra("doenca_animal")
    val context = LocalContext.current
 //   var id_usuario by remember { mutableStateOf(TextFieldValue(chave.value)) }
    var id_animal by remember {mutableStateOf(TextFieldValue(chave.value)) }
    var tipo_doenca by remember { mutableStateOf(TextFieldValue(doencaAnimal?.tipo_doenca ?: "")) }
    var data_doenca by remember { mutableStateOf(TextFieldValue(doencaAnimal?.data_doenca ?: "")) }
    var curado by remember { mutableStateOf(TextFieldValue(doencaAnimal?.curado ?: "")) }
    var doenca_animal by remember { mutableStateOf(TextFieldValue(doencaAnimal?.doenca_animal ?: "")) }
    var medicamento by remember { mutableStateOf(TextFieldValue(doencaAnimal?.medicamento ?: "")) }
    var descricao by remember { mutableStateOf(TextFieldValue(doencaAnimal?.descricao ?: "")) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp)
    ) {
        Column {
            Spacer(modifier = Modifier.height(30.dp))
            OutlinedTextField(
                value = tipo_doenca,
                onValueChange = {tipo_doenca = it},
                label = { Text("Tipo da Doença") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = NomeIconColor.value,
                        modifier = Modifier.size(30.dp)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .onFocusChanged { focusState ->
                        NomeIconColor.value =
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
            Spacer(modifier = Modifier.height(30.dp))
            OutlinedTextField(
                value = data_doenca,
                onValueChange = {data_doenca = it},
                label = { Text("Data") },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.calendario),
                        contentDescription = "Calendar",
                        colorFilter = ColorFilter.tint(AplicacaoIconColor.value),
                        modifier = Modifier.size(30.dp)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .onFocusChanged { focusState ->
                        AplicacaoIconColor.value =
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
            Spacer(modifier = Modifier.height(30.dp))
            OutlinedTextField(
                value = curado,
                onValueChange = {curado = it},
                label = { Text("Curado") },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.calendario),
                        contentDescription = "Calendar",
                        colorFilter = ColorFilter.tint(VencimentoIconColor.value),
                        modifier = Modifier.size(30.dp)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .onFocusChanged { focusState ->
                        VencimentoIconColor.value =
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
            Spacer(modifier = Modifier.height(30.dp))
            OutlinedTextField(
                value = doenca_animal,
                onValueChange = {doenca_animal = it},
                label = { Text("Nome da Doença") },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.nome_doenca),
                        contentDescription = "Calendar",
                        colorFilter = ColorFilter.tint(NomeDIconColor.value),
                        modifier = Modifier.size(30.dp)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .onFocusChanged { focusState ->
                        NomeDIconColor.value =
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
            Spacer(modifier = Modifier.height(30.dp))
            OutlinedTextField(
                value = medicamento,
                onValueChange = {medicamento = it},
                label = { Text("Medicamento") },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.medicamento),
                        contentDescription = "Calendar",
                        colorFilter = ColorFilter.tint(MedicamentoIconColor.value),
                        modifier = Modifier.size(30.dp)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .onFocusChanged { focusState ->
                        MedicamentoIconColor.value =
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
            Spacer(modifier = Modifier.height(30.dp))
            OutlinedTextField(
                value = descricao,
                onValueChange = {descricao = it},
                label = { Text("Descrição") },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.tiporaca),
                        contentDescription = "Description",
                        colorFilter = ColorFilter.tint(DescricaoIconColor.value),
                        modifier = Modifier.size(24.dp)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .onFocusChanged { focusState ->
                        DescricaoIconColor.value =
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
            Spacer(modifier = Modifier.height(50.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                FloatingActionButton(
                    onClick = {
                        val db = BancoDados()
                        CoroutineScope(Dispatchers.IO).launch {
                            db.Cadastro_Doenca(
                                Doenca_Animal(
                                  //     id_usuario.text,
                                       id_animal.text,
                                       tipo_doenca.text,
                                       data_doenca.text,
                                       curado.text,
                                       doenca_animal.text,
                                       medicamento.text,
                                       descricao.text

                                )
                            )
                            val intent = Intent(context, Tela_Cadastro_Animal::class.java)
                            context.startActivity(intent)
                        }
                    },
                    containerColor = Color(0xFF3D7C17),
                    modifier = Modifier
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
fun TopBarPreviewDoenca() {
    MeuAppDoenca {
        TelaCentroDoenca(intent = Intent())
    }
}
