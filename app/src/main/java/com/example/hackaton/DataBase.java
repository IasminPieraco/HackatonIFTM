package com.example.hackaton;

import com.example.hackaton.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DataBase {

    public static FirebaseDatabase database;
    public static DatabaseReference mDatabase;
    public String dados;

    public DataBase(){
        database = FirebaseDatabase.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email);

        mDatabase.child("users").child(userId).setValue(user);
    }


}
