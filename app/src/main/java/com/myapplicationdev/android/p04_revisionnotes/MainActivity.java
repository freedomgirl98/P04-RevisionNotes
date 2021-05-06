package com.myapplicationdev.android.p04_revisionnotes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editNote;
    RadioButton radioButtonStars;
    RadioGroup radioGroupStars;
    Button btnInsert, btnShow;
    ArrayList<Note> alNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editNote = findViewById(R.id.editTextNote);;
        btnInsert = findViewById(R.id.buttonInsertNote);
        btnShow = findViewById(R.id.buttonShowList);
        final DBHelper dbNote = new DBHelper(MainActivity.this);
        alNote = dbNote.getAllNotes();


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    DBHelper db = new DBHelper(MainActivity.this);
                if(String.valueOf(editNote.getText()).trim().isEmpty()){
                    Toast.makeText(MainActivity.this,"Please enter something",Toast.LENGTH_SHORT).show();
                }
                else {
                    RadioGroup radioGroupStars = findViewById(R.id.radioGroupStars);
                    int selectedButtonId = radioGroupStars.getCheckedRadioButtonId();
                    RadioButton radioButtonStars = findViewById(selectedButtonId);
                    String addedText = String.valueOf(editNote.getText());
                    String num = String.valueOf(radioButtonStars.getText());
                    int number = Integer.valueOf(num);
                    if (alNote.isEmpty()) {
                        db.insertNote(addedText, number);
                        db.close();
                    } else {
                        for (int i = 0; i < alNote.size(); i++) {
                            if (addedText.equals(alNote.get(i).getNoteContent())) {
                                Toast.makeText(MainActivity.this, addedText + " is already inserted", Toast.LENGTH_SHORT).show();
                                break;
                            } else {
                                db.insertNote(addedText, number);
                                db.close();
                                break;
                            }
                        }
                    }
                }
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editNote.getWindowToken(), 0);



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
