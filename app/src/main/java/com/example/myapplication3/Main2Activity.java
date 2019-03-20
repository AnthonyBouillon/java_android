package com.example.myapplication3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    private Button btn_rollBack;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

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
        // A changer contre getApplicationContext()
        SQLite sqlite = new SQLite(this);
        ArrayList<Users> list = new ArrayList();
        list = sqlite.getList();
        textView = findViewById(R.id.textView4);
        for (Users listy : list){
            textView.setText(textView.getText() + "\n\n" + "MAIL :  " + listy.getMail() + "\n" + "PASSWORD : " + listy.getPassword() + "\n" + "ID : " + listy.getId() + "\n\n");
        }



    }
}
