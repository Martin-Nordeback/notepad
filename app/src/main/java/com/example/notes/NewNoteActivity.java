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
   // ArrayList<Notes> notesArrayList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        Button goBackButton = findViewById(R.id.backButton);
        Button saveNoteButton = findViewById(R.id.saveButton);
        FloatingActionButton newNoteButton = findViewById(R.id.floatingActionBackButton);

//        noteHeader = findViewById(R.id.editTextTextPersonName);
//        Intent intent = getIntent();
//        final String message = intent.getStringExtra(MainActivity.MYNOTEPAD);
//        noteHeader.setText(message);

//        noteHeader = findViewById(R.id.editTextTextPersonName);
//        noteContent = findViewById(R.id.editTextTextPersonName2);
//
//        String noteName = noteHeader.getText().toString();
//        String noteContentIntent = noteContent.getText().toString();
//
//        noteHeader.setText(noteName);
//        noteContent.setText(noteContentIntent);

//        //för att hämta sparade intent
//        //setTitle(getString(R.string.edit_item_title));
//        Intent data = getIntent();
//        final String value = data.getStringExtra("ItemValue");
//        final int position = data.getIntExtra("ItemPosition", -1);
//        final Button saveNoteButton = findViewById(R.id.saveButton);
//        final Button goBackButton = findViewById(R.id.backButton);
//        final EditText noteHeader = findViewById(R.id.editTextTextPersonName);
//        final EditText noteContent = findViewById(R.id.editTextTextPersonName2);
//        if (value != null){
//            noteHeader.setText(value);
//            noteContent.setText(value);
//        }
//        saveNoteButton.setEnabled(false);
//        saveNoteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Pass data back to main activity.
//                Intent data = new Intent();
//                data.putExtra("ItemPosition", position);
//                data.putExtra("ItemValue", noteContent.getText().toString());
//                // Notify calling activity the user accepted changes.
//                setResult(RESULT_OK, data);
//                savedNotesMethod();
//
//                // End execution.
//                finish();
//            }
//        });
//
//        goBackButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Notify calling activity the user discarded changes.
//                setResult(RESULT_CANCELED);
//                // End execution.
//                finish();
//            }
//        });

        //ner hit


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
        //long createtime = System.currentTimeMillis();


        String noteName = noteHeader.getText().toString();
        String noteContentIntent = noteContent.getText().toString();
        //System.currentTimeMillis();

        noteHeader.setText(noteName);
        noteContent.setText(noteContentIntent);
        //getter and setter in class for this to work
        //createtime.setCreatedTime(createtime);

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
//    private void readFromFile() {
//        File path = NewNoteActivity.this.getFilesDir();
//        File fileFolder = new File(path, "Notes");
//
//        File[] files = fileFolder.listFiles();
//        for (File f : files) {
//            String noteHeader;
//            String noteContent = "";
//
//            try {
//                System.out.println(f.getName());
//                noteHeader = f.getName();
//                FileReader fileReader = new FileReader(f);
//                BufferedReader bufferedReader = new BufferedReader(fileReader);
//
//                String line;
//                while ((line = bufferedReader.readLine()) != null) {
//                    noteContent += line;
//                    System.out.println(noteContent);
//                }
//                //here in the printer we replace the .txt in each document
//                Notes name = new Notes(noteHeader.replace(".txt", ""), noteContent);
//                notesArrayList.add(name);
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println(notesArrayList);
//    }

}