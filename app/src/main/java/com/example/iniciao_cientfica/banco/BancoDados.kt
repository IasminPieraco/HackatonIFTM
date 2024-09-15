package com.example.iniciao_cientfica.banco

import com.example.iniciao_cientfica.classes.Animal
import com.example.iniciao_cientfica.classes.Doenca_Animal
import com.example.iniciao_cientfica.classes.Evento
import com.example.iniciao_cientfica.classes.Vacina
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class BancoDados {

    private val db = Firebase.firestore
    suspend fun Cadastro_Animal(animal: Animal) {
        try {
            println(animal)
            db.collection("Animal").document(animal.id_animal).set(animal).await()
        } catch (e: Exception) {
            println("erro: " + e)
        }
    }

    suspend fun Mostrar_Animal(): List<Animal> {
        return try {
            val animal = db.collection("Animal").get().await()
            animal.toObjects(Animal::class.java)
        } catch (e: Exception) {
            println(e)
            emptyList<Animal>()
        }
    }

    suspend fun Cadastro_Doenca(doencaAnimal: Doenca_Animal) {
        try {
            println(doencaAnimal)
            db.collection("Tipo Doença").document(doencaAnimal.id_animal).set(doencaAnimal).await()
        } catch (e: Exception) {
            println("erro: " + e)
        }
    }

    suspend fun Mostrar_Doenca(): List<Doenca_Animal> {
        return try {
            val doenca = db.collection("Tipo Doença").get().await()
            doenca.toObjects(Doenca_Animal::class.java)
        } catch (e: Exception) {
            println(e)
            emptyList<Doenca_Animal>()
        }
    }

    suspend fun  Mostrar_DoencaPorId(id_animal: String):List<Doenca_Animal>{
        return try {
            val doenca = db.collection("Tipo Doença")
                .whereEqualTo("id_animal",id_animal)
                .get().await()
            doenca.toObjects(Doenca_Animal::class.java)
        }catch (e:Exception){
            println(e)
            emptyList<Doenca_Animal>()
        }
    }

    suspend fun Cadastro_Vacina(vacina: Vacina) {
        try {
            println(vacina)
            db.collection("Tipo Vacina").document(vacina.id_animal).set(vacina).await()
        } catch (e: Exception) {
            println("erro: " + e)
        }
    }


    suspend fun Mostrar_Vacina(): List<Vacina> {
        return try {
            val vacina = db.collection("Tipo Doença").get().await()
            vacina.toObjects(Vacina::class.java)
        } catch (e: Exception) {
            println(e)
            emptyList<Vacina>()
        }
    }

    suspend fun Mostrar_VacinaPorId(id_animal: String): List<Vacina> {
        return try {
            val vacina = db.collection("Tipo Vacina")
                .whereEqualTo("id_animal", id_animal)
                .get().await()
            vacina.toObjects(Vacina::class.java)
        } catch (e: Exception) {
            println(e)
            emptyList<Vacina>()
        }
    }

    suspend fun Cadastro_Evento(evento: Evento) {
        try {
            println(evento)
            db.collection("Tipo Evento").document(evento.id_animal).set(evento).await()
        } catch (e: Exception) {
            println("erro: " + e)
        }
    }


    suspend fun Mostrar_Evento(): List<Evento> {
        return try {
            val evento = db.collection("Tipo Evento").get().await()
            evento.toObjects(Evento::class.java)
        } catch (e: Exception) {
            println(e)
            emptyList<Evento>()
        }
    }

    suspend fun Mostrar_EventoPorId(id_animal: String): List<Evento> {
        return try {
            val evento = db.collection("Tipo Evento")
                .whereEqualTo("id_animal", id_animal)
                .get().await()
            evento.toObjects(Evento::class.java)
        } catch (e: Exception) {
            println(e)
            emptyList<Evento>()
        }
    }

    fun Excluir_Animal(animal: Animal){
        db.collection("Animal").document(animal.id_animal).delete().addOnCompleteListener {
        }.addOnFailureListener{
        }
    }
}