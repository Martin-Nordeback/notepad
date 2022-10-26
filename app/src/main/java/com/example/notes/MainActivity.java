package com.example.notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<Notes> notesArrayList = new ArrayList<>();
    ListView noteLists;
    AdapterNotes adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SearchView searchView = findViewById(R.id.searchView);
        noteLists = findViewById(R.id.listViewId);


        FloatingActionButton newNoteButton = findViewById(R.id.floatingActionButton);
        readFromFile();

        adapter = new AdapterNotes(getApplicationContext(), notesArrayList);
        noteLists.setAdapter(adapter);

        //TODO varje gång en anteckning sparas ska adaptern uppdateras
        //TODO onResume när man går fram och tillbaka



        noteLists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(MainActivity.this, NewNoteActivity.class);

                Notes note = (Notes) adapterView.getItemAtPosition(i);
                intent.putExtra("text_content", note.noteContent);
                startActivity(intent);
            }
        });

        newNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewNoteActivity.class);
                startActivity(intent);
            }
        });

        //Search bar code
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });



    }



    private void readFromFile() {
        File path = MainActivity.this.getFilesDir();
        File fileFolder = new File(path, "Notes");

        File[] files = fileFolder.listFiles();
        for (File f : files) {
            String noteHeader;
            String noteContent = "";

            try {
                //System.out.println(f.getName());
                noteHeader = f.getName();
                //TODO få bort .txt på alla inlästa filer
                noteHeader.replace(".txt", "");
                //System.out.println(noteHeader);
                FileReader fileReader = new FileReader(f);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                // Här kan du läsa innehållet hur du vill, med en StringBuilder t.ex.
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    noteContent += line;
                    //System.out.println(noteContent);
                }
                Notes namn = new Notes(noteHeader, noteContent);
                notesArrayList.add(namn);



            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(notesArrayList);
    }

}
