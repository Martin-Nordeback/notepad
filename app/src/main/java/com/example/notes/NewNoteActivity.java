package com.example.notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class NewNoteActivity extends AppCompatActivity {

    EditText noteHeader;
    EditText noteContent;
    File noteFolder;
    AdapterNotes adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        Button goBackButton = findViewById(R.id.backButton);
        Button saveNoteButton = findViewById(R.id.saveButton);
        FloatingActionButton newNoteButton = findViewById(R.id.floatingActionBackButton);


        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO lägga till en toast/varning om att dina ändringar inte sparats typ?
                finish();
            }
        });

        newNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewNoteActivity.this, NewNoteActivity.class);
                startActivity(intent);
            }
        });

        saveNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savedNotesMethod();
                finish();
            }
        });

    }


    //få varje note att sparas enskilt - klart!
    private void savedNotesMethod() {

        noteHeader = findViewById(R.id.editTextTextPersonName);
        noteContent = findViewById(R.id.editTextTextPersonName2);

        String noteName = noteHeader.getText().toString();
        String noteContentIntent = noteContent.getText().toString();
        System.currentTimeMillis();

        noteHeader.setText(noteName);
        noteContent.setText(noteContentIntent);

        noteFolder = new File(NewNoteActivity.this.getFilesDir(), "Notes");
        if (!noteFolder.exists()) {
            noteFolder.mkdir();
        }

        try {
            File textFile = new File(noteFolder, noteName + ".txt");
            FileWriter writer = new FileWriter(textFile);
            writer.write(noteContent.getText().toString());
            writer.close();
            Toast.makeText(NewNoteActivity.this, "Your note is saved", Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(NewNoteActivity.this, "Failed to save note, try again!", Toast.LENGTH_LONG).show();
        }

        //adapter method to make the change to mainactivity
        adapter.notifyDataSetChanged();


    }


}