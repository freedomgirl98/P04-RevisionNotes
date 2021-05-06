package com.myapplicationdev.android.p04_revisionnotes;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class SecondActivity extends AppCompatActivity {

	ListView lvNotes;
	ArrayList<Note> alNote;
	RevisionNotesArrayAdapter rnaa;
//	DBHelper dbNote;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//TODO implement the Custom ListView

		setContentView(R.layout.activity_second);
		lvNotes = findViewById(R.id.lv);
		DBHelper dbNote = new DBHelper(SecondActivity.this);

		//add database values into arraylist
		alNote = dbNote.getAllNotes();
		rnaa = new RevisionNotesArrayAdapter(this, R.layout.row,alNote);
		lvNotes.setAdapter(rnaa);

	}




}
