package com.example.myapplication3;

import android.content.Context;
import android.content.Intent;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    private Button btn_rollBack;
    private TextView textView;
    private ListView listView;
    private SQLite sqlite = new SQLite(this);
    // QUAND JE MET CE TABLEAU DANS ADAPTER CA MARCHE mais pas ma liste d'utilisateur.
    private String[] prenoms = new String[]{
            "Antoine", "Benoit", "Cyril", "David", "Eloise", "Florent",
            "Gerard", "Hugo", "Ingrid", "Jonathan", "Kevin", "Logan",
            "Mathieu", "Noemie", "Olivia", "Philippe", "Quentin", "Romain",
            "Sophie", "Tristan", "Ulric", "Vincent", "Willy", "Xavier",
            "Yann", "Zoé"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        if(intent != null){
            String mail = intent.getStringExtra("mail");
            String password = intent.getStringExtra("password");
            if(mail != null && password != null && !mail.equals("") && !password.equals("")){
                new AlertDialog.Builder(this).setTitle("Bienvenue " + mail).setMessage("Voici la liste des utilisateurs").show();
            }
        }

        btn_rollBack = findViewById(R.id.button4);
        btn_rollBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intention: communication between two classes
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                // Displays view
                startActivity(intent);
            }
        });

      /*  textView = findViewById(R.id.textView4);
        for (Users listy : list){
            textView.setText(textView.getText() + "\n\n" + "MAIL :  " + listy.getMail() + "\n" + "PASSWORD : " + listy.getPassword() + "\n" + "ID : " + listy.getId() + "\n\n");

        }*/
        listView = findViewById(R.id.listView);
        ArrayList<Users> list = sqlite.getList();
        ArrayAdapter<Users> itemAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,  list);
        listView.setAdapter(itemAdapter);
    }

}
