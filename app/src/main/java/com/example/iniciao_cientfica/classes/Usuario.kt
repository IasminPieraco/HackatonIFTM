package com.example.iniciao_cientfica.classes

import android.os.Parcel
import android.os.Parcelable

data class Usuario (
    var id_usuario: String,
    var nome: String,
    var senha: String,
    var email: String,
    var telefone: String

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
        parcel.writeString(id_usuario)
        parcel.writeString(nome)
        parcel.writeString(senha)
        parcel.writeString(email)
        parcel.writeString(telefone)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Usuario> {
        override fun createFromParcel(parcel: Parcel): Usuario {
            return Usuario(parcel)
        }

        override fun newArray(size: Int): Array<Usuario?> {
            return arrayOfNulls(size)
        }
    }
}