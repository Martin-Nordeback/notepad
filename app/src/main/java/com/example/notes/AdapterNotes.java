package com.example.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;


public class AdapterNotes extends ArrayAdapter<Notes> {


    public AdapterNotes (Context context, ArrayList<Notes> itemList) {
        super(context, R.layout.custom_list_bars, itemList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Notes note = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list_bars, parent, false);
        }


        TextView noteHeader = convertView.findViewById(R.id.text_view_header);
        TextView noteContent = convertView.findViewById(R.id.text_view_content);
       // ImageView bin = convertView.findViewById(R.id.deleteBinButton);
        TextView timeDate = convertView.findViewById(R.id.timeDateView);
        noteHeader.setText(note.noteHeader);
        noteContent.setText(note.noteContent);
        timeDate.setText((CharSequence) note.timeDate);
        //bin.setImageDrawable(note.bin);
        //deleteBinButton.setImageDrawable(deleteBinButton);
        //timeData
        return convertView;
    }

}
