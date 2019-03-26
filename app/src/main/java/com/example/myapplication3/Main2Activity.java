package com.example.myapplication3;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;


public class Main2Activity extends AppCompatActivity {

    private Button btn_rollBack;
    private ListView listView;
    private ArrayList<Users> list;
    private SQLite sqlite = new SQLite(this);
    private String id_item;
    private  UsersDB usersDB = new UsersDB(Main2Activity.this);

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

        getList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                // dans la ligne "5" récupérer l'id
                AlertDialog.Builder adb = new AlertDialog.Builder(Main2Activity.this);
                adb.setTitle("Suppression d'utilisateur");
                adb.setMessage("Etes vous sur de vouloir le supprimer ?");
                adb.setNegativeButton("Non", null);
                adb.setPositiveButton("Oui", new AlertDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        id_item = list.get(position).getId().toString();
                        usersDB.deleteUser(Integer.parseInt(id_item));
                        Toast.makeText(Main2Activity.this, "Element supprimé avec succès !", Toast.LENGTH_SHORT).show();
                        getList();

                    }
                });
                adb.show();
            }
        });

    }

    public void getList() {
        Button button = new Button(this);
        button.setText("Ajouter un utilisateur");

        listView = findViewById(R.id.listView);
        list = usersDB.getList();

        ArrayAdapter<Users> itemAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(itemAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);

            }
        });


    }
}
