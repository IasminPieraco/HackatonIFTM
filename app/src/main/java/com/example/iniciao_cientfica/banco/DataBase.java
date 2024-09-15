package com.example.iniciao_cientfica.banco;

import com.example.iniciao_cientfica.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DataBase {
    public static FirebaseDatabase database;
    public static DatabaseReference mDatabase;
    public String dados;

    public DataBase(){
        database = FirebaseDatabase.getInstance("https://hackatoniftm-a7c07-default-rtdb.firebaseio.com/");
        mDatabase = FirebaseDatabase.getInstance("https://hackatoniftm-a7c07-default-rtdb.firebaseio.com/").getReference();
    }

    public static void initBD(){
        database = FirebaseDatabase.getInstance("https://hackatoniftm-a7c07-default-rtdb.firebaseio.com/");
        mDatabase = FirebaseDatabase.getInstance("https://hackatoniftm-a7c07-default-rtdb.firebaseio.com/").getReference();
    }

    public static void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email);

        mDatabase.child("users").child(userId).setValue(user);
    }
}
