package com.example.iniciao_cientfica.classes

import android.os.Parcel
import android.os.Parcelable


data class Evento (
    var id_evento: String,
    var id_animal: String,
    var tipo_evento: String,
    var data: String,
    var descricao: String


) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()

    ) {
    }

    constructor()
            :this("","","","","")

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id_evento)
        parcel.writeString(id_animal)
        parcel.writeString(tipo_evento)
        parcel.writeString(data)
        parcel.writeString(descricao)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Evento> {
        override fun createFromParcel(parcel: Parcel): Evento {
            return Evento(parcel)
        }

        override fun newArray(size: Int): Array<Evento?> {
            return arrayOfNulls(size)
        }
    }
}