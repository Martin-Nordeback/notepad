package com.example.notes;

public class Notes {

    String noteHeader;
    String noteContent;
    String timeStamp;

    public Notes(String noteHeader, String noteContent) {
        this.noteHeader = noteHeader;
        this.noteContent = noteContent;
    }

    public Notes(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getNoteHeader() {
        return noteHeader;
    }

    public void setNoteHeader(String noteHeader) {
        this.noteHeader = noteHeader;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "noteHeader='" + noteHeader + '\'' +
                ", noteContent='" + noteContent + '\'' +
                '}';
    }
}
