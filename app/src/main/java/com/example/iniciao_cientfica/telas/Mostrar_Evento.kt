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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iniciao_cientfica.AI.IA
import com.example.iniciao_cientfica.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class Mostrar_Evento : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppMostraEvento {
                MainScreenMostraEvento()
            }
        }
    }
}

@Composable
fun MyAppMostraEvento(content: @Composable () -> Unit) {
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
fun MainScreenMostraEvento() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavigationMostraEvento(drawerState, scope)
        }
    ) {
        Scaffold(
            topBar = {
                TopBarMostraEvento(drawerState, scope)
            },
            content = { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFBBA6A6))
                        .padding(innerPadding)
                ) {
                    // Chamada ao formulário atualizado com Sliders
                    Formulario()
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarMostraEvento(drawerState: DrawerState, scope: CoroutineScope) {
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
                text = "Treinamento",
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
fun NavigationMostraEvento(drawerState: DrawerState, scope: CoroutineScope) {
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
fun Formulario() {
    var selectedCargo by remember { mutableStateOf("") }
    var selectedCargoDesejado by remember { mutableStateOf("") }
    var horas by remember { mutableStateOf("") }
    var cargo by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var cargosDesejado by remember {
        mutableStateOf(TextFieldValue(""))
    }
    val context = LocalContext.current

    val cargos = listOf("Residente"	, "Enfermeiro","Instrumentador"  ,
            "Medico	",
            "Cirurgião"	,
            "Cirurgião geral" ,
            "Neuro cirurgião")
    val cargosDesejados = listOf( "Enfermeiro","Instrumentador"  ,
    "Medico	",
    "Cirurgião"	,
    "Cirurgião geral" ,
    "Neuro cirurgião")
    val habilidades = listOf(
        "Cuidados Intensivos",
        "Gestão e Liderança em Saúde",
        "Terapia Intensiva",
        "Tecnologia e Inovação em Saúde",
        "Qualidade e Segurança do Paciente",
        "Cuidados Paliativos",
        "Tratamentos Avançados",
        "Tratamentos Especializados",
        "Saúde Mental",
        "Saúde Psicológica",
        "Farmácia",
        "Terapia Farmacológica",
        "Nutrição Clínica",
        "Nutrição Hospitalar"
    )

    val sliderValues = remember { mutableStateMapOf<String, Float>().apply {
        habilidades.forEach { this[it] = 0f }
    }}
    val perguntas = listOf(
        "O funcionário já possui experiência prévia na função?",
        " O funcionário entende bem as responsabilidades do seu cargo atual?",
    "O funcionário tem conhecimento básico sobre as ferramentas utilizadas no trabalho?",

   " O funcionário tem dificuldades para utilizar os sistemas internos da empresa?",

    "O funcionário já recebeu treinamentos sobre segurança no trabalho?",

    "O funcionário tem interesse em aprender novas habilidades técnicas?",

    "O funcionário sabe como lidar com situações de conflito no ambiente de trabalho?",

    "O funcionário está familiarizado com as políticas da empresa sobre ética e conduta?",

    "O funcionário sente que seu desempenho poderia melhorar com mais treinamentos?",

    "O funcionário já participou de treinamentos de liderança ou gestão de equipes?",

    "O funcionário tem dificuldades para realizar tarefas relacionadas a tecnologia?",

    "O funcionário se sente confortável ao trabalhar em equipe?",

    "O funcionário tem interesse em aprender sobre metodologias ágeis?",

    "O funcionário já recebeu treinamentos sobre diversidade e inclusão?",

    "O funcionário sabe como identificar e resolver problemas de maneira autônoma?",

    "O funcionário demonstra interesse em desenvolvimento contínuo e aprendizado?",

    "O funcionário conhece as práticas de segurança digital da empresa?",

    "O funcionário já participou de treinamentos de atendimento ao cliente?",

    "O funcionário tem dificuldades para se comunicar de forma clara e eficaz?",

    "O funcionário já foi treinado para trabalhar sob pressão?",
    )
    val answers = remember { mutableStateListOf<Boolean?>(
        null, null, null, null,null,null,null,null,null,null,
        null, null, null, null,null,null,null,null,null,null
        ) }




    LazyColumn {
            item {
                Text("Formulário de Habilidades e Cargo", style = MaterialTheme.typography.titleLarge)
            }

            // Seleção de Cargo
            item {
                StylishSelect(
                    value = cargo,
                    options = cargos,
                    onOptionSelected = {
                        cargo = TextFieldValue(it)
                    },
                    label = "Cargo",
                    placeholder = "Cargo"
                )
            }

            // Seleção de Cargo Desejado
            item {
                StylishSelect(
                    value = cargosDesejado,
                    options = cargosDesejados,
                    onOptionSelected = {
                        cargosDesejado= TextFieldValue(it)
                    },
                    label = "Cargo Desejado",
                    placeholder = "Cargo Desejado"
                )
            }

            // Input de Horas
            item {
                OutlinedTextField(
                    value = horas,
                    onValueChange = { horas = it },
                    label = { Text("Tempo (0-8 horas)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }

            // Habilidades com Slider centralizado
            items(habilidades) { habilidade ->
                SliderField(
                    habilidade = habilidade,
                    value = sliderValues[habilidade] ?: 0f,
                    onValueChange = { sliderValues[habilidade] = it.roundToInt().toFloat() }
                )
            }
        item {
            perguntas.forEachIndexed { index, pergunta ->
                QuestionItem(
                    question = (index +1).toString() +")" +pergunta,
                    answer = answers[index],
                    onAnswerSelected = { answer -> answers[index] = answer }
                )
            }

        }



            // Botão de Submit
            item {
                Row (Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Absolute.Center){
                    Button(
                        onClick = {
                            IA.initIA(48, 50, 10)
                            IA.train()
                            IA.predict()
                            val lista = IA.lista
                            val intent = Intent(context,Tela_Principal::class.java)
                            intent.putStringArrayListExtra("Lista",lista)
                            context.startActivity(intent)


                            // Ações para enviar os dados
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0288d1))
                    ) {
                        Text("Enviar")
                    }

                }

            }

    }


}

@Composable
fun QuestionItem(
    question: String,
    answer: Boolean?,
    onAnswerSelected: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = question,
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            RadioButton(
                selected = answer == true,
                onClick = { onAnswerSelected(true) }
            )
            Text(text = "Sim")
            RadioButton(
                selected = answer == false,
                onClick = { onAnswerSelected(false) }
            )
            Text(text = "Não")
        }
    }
}

@Composable
fun DropdownMenuField(label: String, options: List<String>, selectedOption: String, onOptionSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            label = { Text(label) },
            readOnly = true,
            trailingIcon = {
                Icon(Icons.Filled.ArrowDropDown, contentDescription = null)
            },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true } // Corrigido para abrir ao clicar
        )
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    },
                    text = { Text(option) }
                )
            }
        }
    }
}

@Composable
fun SliderField(habilidade: String, value: Float, onValueChange: (Float) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally // Centraliza o Slider
    ) {
        Text(habilidade, style = MaterialTheme.typography.labelLarge)
        Text(text = value.toString(), style = MaterialTheme.typography.bodySmall)
        // Slider com centro em 0 e cores diferentes para positivo/negativo
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = -10f..10f, // Valor entre -10 e 10
            steps = 20, // Incremento de 1
            modifier = Modifier.fillMaxWidth(),
//            colors = SliderDefaults.colors(
//                thumbColor = if (value >= 0) Color.Green else Color.Red, // Muda cor dependendo do valor
 //              activeTrackColor = if (value >= 0) Color.Green else Color.Red, // Barra ativa
//            )
            colors = SliderDefaults.colors(thumbColor = Color(0xFF0288d1),activeTrackColor = Color(0xFF0288d1),
            inactiveTrackColor = Color.Gray)

        )



        Spacer(modifier = Modifier.height(3.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StylishSelect(
    value: TextFieldValue,
    options: List<String>,
    onOptionSelected: (String) -> Unit,
    label: String,
    placeholder: String,
    enabled: Boolean = true
) {
    var expanded by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    val buttonGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF199CAC),
            Color(0xFF27a0a8),
            Color(0xFF0288d1),
        )
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = {},
            readOnly = true,
            enabled = enabled,
            label = { Text(label) },
            placeholder = { Text(placeholder) },
            trailingIcon = {
                if (expanded) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Dropdown Arrow"
                    )
                }
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { expanded = it.isFocused },
            shape = RoundedCornerShape(corner = CornerSize(16.dp)),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                focusedLabelColor = MaterialTheme.colorScheme.secondary,
                cursorColor = MaterialTheme.colorScheme.primary
            ),
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
                focusManager.clearFocus()

            },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .background(buttonGradient)
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = option,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 16.dp),
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                        focusManager.clearFocus()
                    },
                    contentPadding = PaddingValues(vertical = 16.dp)
                )
            }
        }
    }
}
// Modelo de dados para uma pergunta com respostas Sim ou Não
data class Pergunta(
    val texto: String,
    var value: Boolean
)

@Composable
fun createQuestionStates(keys: List<String>): List<Pergunta> {
    return remember {
        keys.map { key ->
            Pergunta(key, false)
        }.toList()
    }
}

