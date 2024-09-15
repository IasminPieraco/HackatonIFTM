package com.example.iniciao_cientfica.classes

import android.os.Parcel
import android.os.Parcelable

data class Doenca_Animal (
    var id_usuario: String,
    var id_animal: String,
    var tipo_doenca: String,
    var data_doenca: String,
    var curado: String,
    var doenca_animal: String,
    var medicamento: String,
    var descricao: String

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    constructor(
        text: String,
        text1: String,
        text2: String,
        text3: String,
        text4: String,
        text5: String,
        text6: String
    )
            :this("","","","","","","","")

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id_usuario)
        parcel.writeString(id_animal)
        parcel.writeString(tipo_doenca)
        parcel.writeString(data_doenca)
        parcel.writeString(curado)
        parcel.writeString(doenca_animal)
        parcel.writeString(medicamento)
        parcel.writeString(descricao)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Doenca_Animal> {
        override fun createFromParcel(parcel: Parcel): Doenca_Animal {
            return Doenca_Animal(parcel)
        }

        override fun newArray(size: Int): Array<Doenca_Animal?> {
            return arrayOfNulls(size)
        }
    }
}