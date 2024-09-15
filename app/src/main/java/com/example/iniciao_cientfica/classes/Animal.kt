package com.example.iniciao_cientfica.classes

import android.os.Parcel
import android.os.Parcelable

data class Animal (
    var id_animal: String,
    var raca: String,
    var sexo: String,
    var data_nascimento: String,
    var peso: String,
    var pastagem: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    constructor()
            :this("","","","","","")

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id_animal)
        parcel.writeString(raca)
        parcel.writeString(sexo)
        parcel.writeString(data_nascimento)
        parcel.writeString(peso)
        parcel.writeString(pastagem)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Animal> {
        override fun createFromParcel(parcel: Parcel): Animal {
            return Animal(parcel)
        }

        override fun newArray(size: Int): Array<Animal?> {
            return arrayOfNulls(size)
        }
    }
}