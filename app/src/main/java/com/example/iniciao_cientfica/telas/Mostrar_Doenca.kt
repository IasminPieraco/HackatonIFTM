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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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

class Mostrar_Doenca : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppMostraDoenca {
                MainScreenDoenca()
            }
        }
    }
}

@Composable
fun MyAppMostraDoenca(content: @Composable () -> Unit) {
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
fun MainScreenDoenca() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavigationMostraDoenca(drawerState, scope)
        }
    ) {
        Scaffold(
            topBar = {
                TopBarMostraDoenca(drawerState, scope)
            },
            content = { innerPadding ->
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {

                    ConteudoMostraDoenca(innerPadding)
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarMostraDoenca(drawerState: DrawerState, scope: CoroutineScope) {
    val contexto = LocalContext.current

    TopAppBar(
        title = {},
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
            Spacer(modifier = Modifier.width(150.dp))
            Text(
                text = "Funcionarios",
                textAlign = TextAlign.Center,
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 25.sp)
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { /* Ação 1 */ }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Ação 1",
                    tint = Color.White,
                    modifier = Modifier.size(35.dp)
                )
            }
            IconButton(onClick = { /* Voltar */ }) {
                Image(
                    painter = painterResource(id = R.drawable.ordenar),
                    contentDescription = "Voltar"
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
fun NavigationMostraDoenca(drawerState: DrawerState, scope: CoroutineScope) {
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
fun ConteudoMostraDoenca(innerPadding: PaddingValues) {
    var allDoencaAnimalInfo by remember { mutableStateOf(emptyList<Doenca_Animal>()) }
    LaunchedEffect(Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val db = BancoDados()
            val doencaAnimal = db.Mostrar_Doenca()
            println(doencaAnimal)
            allDoencaAnimalInfo = doencaAnimal
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        Image(painter = painterResource(id = R.drawable.graficos), contentDescription ="Graficos" )
        LazyColumn {
            item {
                ListaDeFuncionarios()
            }
        }
    }
}
 //mudar essa parte para o código antigo e colocar a função dialogo de volta
@Composable
fun CartaoMostraDoenca( doencaAnimal: Doenca_Animal) {
     val isExpanded = remember { mutableStateOf(false) }
     val showDialog = remember { mutableStateOf(false) }
     val dialogContent = remember { mutableStateOf("") }

     Card(
         modifier = Modifier
             .fillMaxWidth()
             .padding(16.dp),
         colors = CardDefaults.cardColors(containerColor = Color(0xFFEFEFEF))
     ) {
         Column(modifier = Modifier.padding(16.dp)) {
             Row(verticalAlignment = Alignment.CenterVertically) {
                 Image(
                     painter = painterResource(id = R.drawable.logo),
                     contentDescription = "Imagem do Animal",
                     modifier = Modifier.size(if (isExpanded.value) 100.dp else 65.dp)
                 )
                 Spacer(modifier = Modifier.width(8.dp))
                 Column(modifier = Modifier.weight(1f)) {
                     Text(text = "ID: ${doencaAnimal.id_animal}", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                     Text(text = "Tipo da Doença: ${doencaAnimal.tipo_doenca}", color = Color(0xFFB66200))
                 }
                 IconButton(onClick = { isExpanded.value = !isExpanded.value }) {
                     Icon(
                         painter = if (isExpanded.value) painterResource(id = R.drawable.sobe) else painterResource(id = R.drawable.desce),
                         contentDescription = if (isExpanded.value) "Recolher" else "Expandir"
                     )
                 }
             }
             if (isExpanded.value) {
                 Spacer(modifier = Modifier.height(8.dp))
                 Column {
                     Text(text = "Data da Doença: ${doencaAnimal.data_doenca}", color = Color(0xFFD32F2F))
                     Text(text = "Curado: ${doencaAnimal.curado}", color = Color.Black)
                     Text(text = "Doença do Animal: ${doencaAnimal.doenca_animal}", color = Color(0xFF2E7D32))
                     Text(text = "Medicamento: ${doencaAnimal.medicamento}", color = Color.Black)
                     Text(text = "Descrição: ${doencaAnimal.descricao}", color = Color.Black)
                     Spacer(modifier = Modifier.height(8.dp))
                     }
                 }
             }
         }
     }

@Preview(showBackground = true)
@Composable
fun DefaultPreviewMostraDoenca() {
    MyAppMostraDoenca {
        MainScreenDoenca()
    }
}
data class Funcionario(
    val nome: String,
    val idade: Int,
    val cargo: String
)

// Função que gera 5 funcionários aleatórios
fun gerarFuncionariosAleatorios(): List<Funcionario> {
    val nomes = listOf("Ana", "Carlos", "Beatriz", "Fernando", "Julia", "Pedro", "Lucas", "Mariana")
    val cargos = listOf("Enfermeiro","Instrumentador"  , "Medico	", "Cirurgião"	, "Cirurgião geral" , "Neuro cirurgião")

    return List(5) {
        Funcionario(
            nome = nomes.random(),
            idade = Random.nextInt(18, 65),
            cargo = cargos.random()
        )
    }
}

@Composable
fun FuncionarioCard(funcionario: Funcionario) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Nome: ${funcionario.nome}", style = MaterialTheme.typography.titleMedium)
            Text(text = "Idade: ${funcionario.idade}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Cargo: ${funcionario.cargo}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun ListaDeFuncionarios() {
    // Gerando 5 funcionários aleatórios
    val funcionarios = remember { gerarFuncionariosAleatorios() }

    Column(modifier = Modifier.fillMaxSize()) {
        funcionarios.forEach { funcionario ->
            FuncionarioCard(funcionario = funcionario)
        }
    }
}
