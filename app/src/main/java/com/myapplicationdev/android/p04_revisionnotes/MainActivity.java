package com.myapplicationdev.android.p04_revisionnotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText editNote;
    RadioButton radioButtonStars;
    RadioGroup radioGroupStars;
    Button btnInsert, btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editNote = findViewById(R.id.editTextNote);;
        btnInsert = findViewById(R.id.buttonInsertNote);
        btnShow = findViewById(R.id.buttonShowList);


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup radioGroupStars = findViewById(R.id.radioGroupStars);
                int selectedButtonId = radioGroupStars.getCheckedRadioButtonId();
                RadioButton radioButtonStars = findViewById(selectedButtonId);
                String addedText = String.valueOf(editNote.getText());
                String num = String.valueOf(radioButtonStars.getText());
                int number = Integer.valueOf(num);
                DBHelper db = new DBHelper(MainActivity.this);
                db.insertNote(addedText,number);
                db.close();
            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    }
