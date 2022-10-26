package com.example.notes;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class NoteAdapter extends ArrayAdapter {
    private static final String TAG = "NoteAdapter";
    private final int layoutResource;
    private final LayoutInflater layOutInFlater;
    private List<NoteAdapter> applications;

    public NoteAdapter(@NonNull Context context, int resource, List<NoteAdapter> applications) {
        super(context, resource);
        this.layoutResource = resource;
        this.layOutInFlater = LayoutInflater.from(context);
        this.applications = applications;
    }

    @Override
    public int getCount() {
        return applications.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = layOutInFlater.inflate(layoutResource, parent, false);

        TextView noteHeader = (TextView) view.findViewById(R.id.text_view_header);
        TextView noteContent = (TextView) view.findViewById(R.id.text_view_content);

        NoteAdapter currentApp = applications.get(position);

        noteHeader.setText(currentApp.getNoteHeader());
        noteContent.setText(currentApp.getNoteContent());

        return view;
    }

    private int getNoteHeader() {
        return 0;
    }

    private int getNoteContent() {
        return 0;
    }
}






