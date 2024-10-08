package com.example.iniciao_cientfica.telas

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iniciao_cientfica.R
import com.example.iniciao_cientfica.banco.BancoDados
import com.example.iniciao_cientfica.classes.Animal
import com.example.iniciao_cientfica.classes.Doenca_Animal
import com.example.iniciao_cientfica.classes.Evento
import com.example.iniciao_cientfica.classes.Vacina
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random


class Tela_Principal : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val healthcareTopics = intent.getStringArrayListExtra("Lista")
            MyApp {
                MainScreen(healthcareTopics)
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
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
fun MainScreen(healthcareTopics: ArrayList<String>?) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavigationPrincipal(drawerState, scope)
        }
    ) {
        Scaffold(
            topBar = {
                TopBar(drawerState, scope)
            },
            content = { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFBBA6A6))
                ) {
                    ConteudoPrincipal(innerPadding,healthcareTopics)
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(drawerState: DrawerState, scope: CoroutineScope) {
    val contexto = LocalContext.current

    TopAppBar(
        title = {
            Text(
                text = "Meus Testes",
                textAlign = TextAlign.Center,
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp)
            )
        },
        navigationIcon = {
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
                    tint = Color.White,
                    modifier = Modifier.size(35.dp)
                )
            }
        },
        actions = {
            IconButton(onClick = { /* Ação 1 */ }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Ação 1",
                    tint = Color.White,
                    modifier = Modifier.size(35.dp)
                )
            }
            IconButton(onClick = {
                contexto.startActivity(Intent(contexto, Tela_Cadastro_Animal::class.java))
            }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Ação 2",
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
            }
            IconButton(onClick = { /* Ação 3 */ }) {
                Image(
                    painter = painterResource(id = R.drawable.ordenar),
                    contentDescription = "Ordenar",
                    modifier = Modifier.size(35.dp) // Adicione um tamanho para o ícone
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color(0xFF0288d1),
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White,
            actionIconContentColor = Color.White
        )
    )
}


@Composable
fun NavigationPrincipal(drawerState: DrawerState, scope: CoroutineScope) {
    val contexto = LocalContext.current
    ModalDrawerSheet(
        modifier = Modifier
            .fillMaxWidth(0.75f)
            .background(Color(0xFF0288d1))
    ) {
        Column(
            modifier = Modifier
                .background(Color(0xFF0288d1))
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
                    painter = painterResource(R.drawable.eduboot_logo),
                    contentDescription = "Logo",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(250.dp)
                        .scale(0.8f)
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
                    contentDescription = "Meus Testes",
                    tint = Color.Black,
                    modifier = Modifier.size(25.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text("Meus Testes", color = Color.Black, fontSize = 25.sp)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 25.dp)
                    .clickable {
                        contexto.startActivity(
                            Intent(
                                contexto,
                                Mostrar_Doenca::class.java
                            )
                        )
                    }
            ) {
                Icon(
                    imageVector = Icons.Default.Event,
                    contentDescription = "Eventos",
                    tint = Color.Black,
                    modifier = Modifier.size(25.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text("Meus Funcionarios", color = Color.Black, fontSize = 25.sp)
            }
            //Doenças
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 25.dp)
                    .clickable {
                        contexto.startActivity(
                            Intent(
                                contexto,
                                Mostrar_Evento::class.java
                            )
                        )
                    }
            ) {
                Icon(
                    imageVector = Icons.Default.HealthAndSafety,
                    contentDescription = "Treinar",
                    tint = Color.Black,
                    modifier = Modifier.size(25.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text("Treinar", color = Color.Black, fontSize = 25.sp)
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
fun ConteudoPrincipal(innerPadding: PaddingValues, healthcareTopics: ArrayList<String>?) {
    var allAnimalInfo by remember { mutableStateOf(emptyList<Animal>()) }
    val scope = rememberCoroutineScope()

    

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        item{
            if (healthcareTopics != null) {
                healthcareTopics.forEach{
                    if(it.trim().length > 0)
                    CartaoAnimal(it)
                }
            }
            
        }
    }
}


suspend fun <T : Any> loadData(loadDataFunc: suspend () -> T): T {
    return withContext(Dispatchers.IO) {
        loadDataFunc()
    }
}

@Composable
fun ExibirDialogo(opcoes: Int, animal: Animal, onDismiss: () -> Unit) {
    val db = BancoDados()
    val doencaState = remember { mutableStateOf<List<Doenca_Animal>>(emptyList()) }
    val vacinaState = remember { mutableStateOf<List<Vacina>>(emptyList()) }
    val eventoState = remember { mutableStateOf<List<Evento>>(emptyList()) }

    val infoState = remember { mutableStateOf<String>("") }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = opcoes) {
        coroutineScope.launch {
            when (opcoes) {
                1 -> {
                    doencaState.value = loadData { db.Mostrar_DoencaPorId(animal.id_animal) }
                }
                2 -> {
                    vacinaState.value = loadData { db.Mostrar_VacinaPorId(animal.id_animal) }
                }
                3 -> {
                    eventoState.value = loadData { db.Mostrar_EventoPorId(animal.id_animal) }
                }
            }
        }
    }

    when (opcoes) {
        1 -> {
            AlertDialog(
                onDismissRequest = { onDismiss() },
                title = { Text(text = "Doença") },
                text = {
                    if (doencaState.value.isEmpty()) {
                        Text(text = "Carregando...")
                    } else {
                        doencaState.value.forEach { vacina ->
                            Text(text = doencaState.toString())
                        }
                    }
                },
                confirmButton = {
                    TextButton(onClick = { onDismiss() }) {
                        Text("Fechar")
                    }
                }
            )
        }
        2 -> {
            AlertDialog(
                onDismissRequest = { onDismiss() },
                title = { Text(text = "Vacina") },
                text = {
                    if (infoState.value.isEmpty()) {
                        Text(text = "Carregando...")
                    } else {
                        Text(text = vacinaState.toString())
                    }
                },
                confirmButton = {
                    TextButton(onClick = { onDismiss() }) {
                        Text("Fechar")
                    }
                }
            )
        }
        3 -> {
            AlertDialog(
                onDismissRequest = { onDismiss() },
                title = { Text(text = "Evento") },
                text = {
                    if (infoState.value.isEmpty()) {
                        Text(text = "Carregando...")
                    } else {
                        Text(text = eventoState.toString())
                    }
                },
                confirmButton = {
                    TextButton(onClick = { onDismiss() }) {
                        Text("Fechar")
                    }
                }
            )
        }
    }
}

@Composable
fun CartaoAnimal(animal: String) {
    val isExpanded = remember { mutableStateOf(false) }
    val showDialog = remember { mutableStateOf(false) }
    val showDeleteDialog = remember { mutableStateOf(false) }
    val dialogContent = remember { mutableStateOf("") }
    val context = LocalContext.current
    val db = BancoDados()
    val coroutineScope = rememberCoroutineScope()
    var opcoes = remember { mutableIntStateOf(0) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { isExpanded.value = !isExpanded.value }, // Adiciona a funcionalidade de clique no card
        colors = CardDefaults.cardColors(containerColor = Color(0x0FEEE4E4))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.eduboot_logo),
                    contentDescription = "Imagem do Animal",
                    modifier = Modifier.size(if (isExpanded.value) 100.dp else 65.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = animal, color = Color(0xFFB66200))
                }
                Text(text = Random.nextInt(0, 101).toString()+"%")
                IconButton(onClick = { showDeleteDialog.value = true }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Deletar",
                        tint = Color(0xFFDA3838),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            
        }
    }


    if (showDeleteDialog.value) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog.value = false },
            title = { Text(text = "Confirmar Exclusão") },
            text = { Text(text = "Você tem certeza que deseja excluir este animal?") },
            confirmButton = {
                TextButton(onClick = {
                    coroutineScope.launch {
                        showDeleteDialog.value = false
                    }
                }) {
                    Text("Excluir")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog.value = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}



