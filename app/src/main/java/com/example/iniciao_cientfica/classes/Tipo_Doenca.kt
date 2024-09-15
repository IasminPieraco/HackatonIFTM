package com.example.iniciao_cientfica.classes

import android.os.Parcel
import android.os.Parcelable

data class Tipo_Doenca (
    var id_tipo_doenca: String,
    var nome_doenca: String,
    var caracteristicas: String,
    var remedio: String


) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()

    ) {
    }

    constructor()
            :this("","","","")

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id_tipo_doenca)
        parcel.writeString(nome_doenca)
        parcel.writeString(caracteristicas)
        parcel.writeString(remedio)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Tipo_Doenca> {
        override fun createFromParcel(parcel: Parcel): Tipo_Doenca {
            return Tipo_Doenca(parcel)
        }

        override fun newArray(size: Int): Array<Tipo_Doenca?> {
            return arrayOfNulls(size)
        }
    }
}