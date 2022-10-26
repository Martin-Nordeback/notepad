package com.example.notes;

import android.widget.ImageView;
import android.widget.TextView;

public class Notes {

    String noteHeader;
    String noteContent;
    ImageView bin;
    TextView timeDate;

    public Notes(String noteHeader, String noteContent, ImageView bin, TextView timeDate) {
        this.noteHeader = noteHeader;
        this.noteContent = noteContent;
        this.bin = bin;
        this.timeDate = timeDate;
    }

    //TODO få bin och timedate att funka i denna konstruktorn
    public Notes(String noteHeader, String noteContent) {
        this.noteHeader = noteHeader;
        this.noteContent = noteContent;
    }

}
