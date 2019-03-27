package com.example.myapplication3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    // Button
    private Button btn_validate;
    private Button btn_cancel;
    private Button btn_view;
    private boolean add = false;
    // Field
    private EditText input_editMail;
    private EditText input_editPass;
    // Class
    private SQLite sqlite;
    private UsersDB usersDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Required
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assign the id of the elements
        this.input_editMail = findViewById(R.id.editMail);
        this.input_editPass = findViewById(R.id.editPass);
        this.btn_cancel = findViewById(R.id.button);
        this.btn_validate = findViewById(R.id.button2);
        this.btn_view = findViewById(R.id.button3);

        // When I click on the "Annuler" button
        btn_cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // I clear the fields
                input_editMail.setText("");
                input_editPass.setText("");
            }
        });

        // When I click on the "Valider" button
        btn_validate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // Insert a user => Refer to the add() method
                add();
            }
        });

        // When I click on the "Changer de vue" button
        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // I change my view
               Intent intent = new Intent(MainActivity.this, Main2Activity.class);
               // Passage des identifiants à l'autre activity
                if(add){
                    intent.putExtra("mail", input_editMail.getText().toString());
                    intent.putExtra("password", input_editPass.getText().toString());
                }
               startActivity(intent);
            }
        });

    }

    /**
     * Insert a user in the database if the fields are filled (remplis),
     * and then displays a message
     */
    public void add (){

        if(!input_editMail.getText().toString().equals("") && !input_editPass.getText().toString().equals("")){
            Users user = new Users(input_editMail.getText().toString(), input_editPass.getText().toString());
            // Instance my class
            usersDB = new UsersDB(getApplicationContext());
            usersDB.create(user);
            add = true;
            sqlite.close();
            Toast.makeText(getApplicationContext(), "Vous êtes dorénavent inscrit " + user.getMail(), Toast.LENGTH_LONG).show();
        }else{
            input_editMail.setText(null);
            input_editPass.setText(null);
            Toast.makeText(getApplicationContext(), "Veuillez remplir tous les identifiants", Toast.LENGTH_LONG).show();
        }
    }

}
