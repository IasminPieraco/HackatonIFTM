package com.example.iniciao_cientfica.classes

import android.os.Parcel
import android.os.Parcelable

data class Vacina (
    var id_tipoVacina: String,
    var id_tipo_vacina: String,
    var id_animal: String,
    var nome_vacina: String,
    var data_aplicacao: String,
    var data_vencimento: String,
    var descricao: String

) : Parcelable {
    constructor(parcel: Parcel) : this(
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
        text5: String
    )
            :this("","","","","","","")

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id_tipoVacina)
        parcel.writeString(id_tipo_vacina)
        parcel.writeString(id_animal)
        parcel.writeString(nome_vacina)
        parcel.writeString(data_aplicacao)
        parcel.writeString(data_vencimento)
        parcel.writeString(descricao)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Vacina> {
        override fun createFromParcel(parcel: Parcel): Vacina {
            return Vacina(parcel)
        }

        override fun newArray(size: Int): Array<Vacina?> {
            return arrayOfNulls(size)
        }
    }
}