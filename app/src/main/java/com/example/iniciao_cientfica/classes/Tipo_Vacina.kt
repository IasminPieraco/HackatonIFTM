package com.example.iniciao_cientfica.classes

import android.os.Parcel
import android.os.Parcelable

data class Tipo_Vacina (
    var id_tipo_vacina: String,
    var nome: String,
    var data: String

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()

    ) {
    }

    constructor()
            :this("","","")

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id_tipo_vacina)
        parcel.writeString(nome)
        parcel.writeString(data)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Tipo_Vacina> {
        override fun createFromParcel(parcel: Parcel): Tipo_Vacina {
            return Tipo_Vacina(parcel)
        }

        override fun newArray(size: Int): Array<Tipo_Vacina?> {
            return arrayOfNulls(size)
        }
    }
}