package com.example.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class AdapterNotes extends RecyclerView.Adapter<AdapterNotes.ViewHolder> {

    public static ArrayList<Notes> notesArrayList;

    public AdapterNotes(ArrayList<Notes> listData, MainActivity mainActivity) {
        this.notesArrayList = listData;
    }


    @NonNull
    @Override
    public AdapterNotes.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.custom_list_bars, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNotes.ViewHolder holder, int position) {
        Notes myListData = notesArrayList.get(position);
        holder.textViewName.setText(notesArrayList.get(position).getNoteHeader());
        holder.textViewContent.setText(notesArrayList.get(position).getNoteContent());
        holder.timeDate.setText(notesArrayList.get(position).getTimeStamp());

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(view.getContext(), "Hepp " + myListData.getNoteHeader(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return notesArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public TextView textViewContent;
        public ImageView deleteBin;
        public TextView timeDate;
        public CardView constraintLayout;


        public ViewHolder(View view) {
            super(view);
            this.textViewName = view.findViewById(R.id.text_view_header);
            this.textViewContent = view.findViewById(R.id.text_view_content);
            this.deleteBin = view.findViewById(R.id.deleteBinButton);
            this.timeDate = view.findViewById(R.id.timeDateView);
            this.constraintLayout = view.findViewById(R.id.list_item_constraint_id);

            deleteBin.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    notesArrayList.remove(getAbsoluteAdapterPosition());
                    notifyDataSetChanged();
                    Toast.makeText(view.getContext(), textViewName.getText() +
                            " deleted successfully!", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
        }

    }
}
