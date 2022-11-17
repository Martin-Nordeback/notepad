package com.example.notes;

import android.app.Activity;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {

    Activity activity;
    EditText noteHeader;
    EditText noteContent;
    File noteFolder;

    static ArrayList<Notes> notesArrayList = new ArrayList<>();

    public FileManager(Activity activity) {
        this.activity = activity;
    }

    public void saveToGsonMethod(){

        File file = new File(activity.getFilesDir(), "notes.json");
        Gson gson = new Gson();
        try {
            FileWriter writer = new FileWriter(file);
            String jsonArray = gson.toJson(notesArrayList);

            System.out.println("jsonArray = " + jsonArray);

            writer.write(jsonArray);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromJsonFile(){
        File file = new File(activity.getFilesDir(), "notes.json");
        Gson gson = new Gson();

        try {
            Scanner scanner = new Scanner(file);
            String jsonString = scanner.nextLine();
            System.out.println("jsonString = " + jsonString);

            notesArrayList = gson.fromJson(jsonString, new TypeToken<ArrayList<Notes>>(){}.getType());
            System.out.println("notesarraylist = " + notesArrayList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


}
