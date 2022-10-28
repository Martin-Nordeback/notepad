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
    //public static final String MYNOTEPAD = "com.example.notes";
    ListView noteLists;
    AdapterNotes adapter;
    //EditText noteHeader;
    //EditText noteContent;


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

        //TODO sätt gräns så noteheader & notecontent length()? som visas i custom_list_bar sedan ändra tbx wrap_content
        //TODO om telefonen är wipead så crashar den för de finns ingen "file"  -prio
        //TODO kunna öppna up antecklingarna som sparats -prio
        //TODO kunna editera antecklingarna som sparats -prio
        //TODO kod för att radera en anteckning -prio

        //TODO fixa edittext färg custom_list_bar
        //TODO koppla på delete bucket custom_list_bar
        //TODO koppla på timestamp  custom_list_bar
        //TODO kod för sökfältet

        //TODO kommentera alla kod + xml


        noteLists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = noteLists.getItemAtPosition(i).toString();
                Intent intent = new Intent(MainActivity.this, NewNoteActivity.class);
                Notes note = (Notes) adapterView.getItemAtPosition(i);
                //intent.putExtra("text_content", note.noteContent);
                intent.putExtra("text_content", note.noteContent);
                //intent.putExtra(MYNOTEPAD, item);
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
            public boolean onQueryTextSubmit(String noteHeader) {
                adapter.getFilter().filter(noteHeader);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String noteHeader) {
                adapter.getFilter().filter(noteHeader);
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
