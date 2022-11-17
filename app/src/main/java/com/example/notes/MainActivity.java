package com.example.notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton newNoteButton;
    ArrayList<Notes> notesArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //View
        //SearchView searchView = findViewById(R.id.searchView);
        recyclerView = findViewById(R.id.recycler_view);
        newNoteButton = findViewById(R.id.floatingActionButton);

        //Display created notes
        readFromFile();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        AdapterNotes adapter = new AdapterNotes (notesArrayList, this);
        recyclerView.setAdapter(adapter);


        newNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewNoteActivity.class);
                startActivity(intent);
            }
        });


        //Search bar
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String noteHeader) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String noteHeader) {
//                return false;
//            }
//        });

    }

    private void readFromFile() {
        File path = MainActivity.this.getFilesDir();
        File fileFolder = new File(path, "Notes");

        File[] files = fileFolder.listFiles();
        for (File f : files) {
            String noteHeader;
            String noteContent = "";

            try {
                System.out.println(f.getName());
                noteHeader = f.getName();
                FileReader fileReader = new FileReader(f);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    noteContent += line;
                    System.out.println(noteContent);
                }
                //here in the printer we replace the .txt in each document
                Notes name = new Notes(noteHeader.replace(".txt", ""), noteContent);
                notesArrayList.add(name);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(notesArrayList);
    }

}

//TODO kunna öppna up antecklingarna som sparats -prio
//TODO kunna editera antecklingarna som sparats -prio
//TODO kod för att radera en anteckning -prio

//TODO sätt gräns så noteheader & notecontent length()? som visas i custom_list_bar sedan ändra tbx wrap_content
//TODO om telefonen är wipead så crashar den för de finns ingen "file"  -prio

//TODO fixa edittext färg custom_list_bar
//TODO fixa margin custom_list_bar
//TODO koppla på delete bucket custom_list_bar
//TODO koppla på timestamp  custom_list_bar
//TODO kod för sökfältet
//TODO få in material design

//TODO kommentera alla kod + xml
