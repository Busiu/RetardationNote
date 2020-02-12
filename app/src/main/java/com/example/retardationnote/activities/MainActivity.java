package com.example.retardationnote.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.retardationnote.R;
import com.example.retardationnote.utils.Person;

import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    private Button addPersonButton;
    private EditText addPersonEditText;

    private HashSet<Person> people = new HashSet<>();

    //private static final String user1 = "Bułor";
    //private static final String user2 = "Busiu";

    //private Person retardationList1;
    //private Person retardationList2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addPersonButton = findViewById(R.id.addPersonButton);
        addPersonEditText = findViewById(R.id.addPersonEditText);

        addPersonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nickname = addPersonEditText.getText().toString();
                System.out.println("lol");
                if(!nickname.equals("")) {
                    people.add(new Person(nickname));
                    Toast.makeText(getApplicationContext(), Integer.toString(people.size()), Toast.LENGTH_SHORT).show();
                }
            }
        });
        //TODO: Sprobowac z GSON

        //retardationList1 = new Person(getApplicationContext(), user1);
        //retardationList1.load();

        //retardationList2 = new Person(getApplicationContext(), user2);
        //retardationList2.load();
    }

    protected void onDestroy() {
        super.onDestroy();

        //retardationList1.save();
        //retardationList2.save();
    }
}
