package com.dpolania.notepad.adapter;

import android.content.ClipData;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dpolania.notepad.R;
import com.dpolania.notepad.app.DetailNote;
import com.dpolania.notepad.entities.Note;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private List<Note> items;

    public List<Note> getItems() {
        return items;
    }

    DBNotesAdapter dbNotesAdapter = new DBNotesAdapter();

    public void setItems(List<Note> items) {
        this.items = items;
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView description;
        private CardView noteItem;
        private ImageButton start_on;
        private ImageButton start_off;

        public NoteViewHolder(View v) {
            super(v);
            title=(TextView) v.findViewById(R.id.title);
            description=(TextView) v.findViewById(R.id.description);
            noteItem=(CardView) v.findViewById(R.id.noteItem);
            start_on=(ImageButton) v.findViewById(R.id.start_on);
            start_off=(ImageButton) v.findViewById(R.id.start_off);
        }
    }

    public NotesAdapter(List<Note> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.note_item_row, viewGroup, false);
        return new NoteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final NoteViewHolder viewHolder, int i) {


        final Note note = new Note();

        note.setNoteId(items.get(i).getNoteId());
        note.setFavorite(items.get(i).getFavorite());
        note.setTitle(items.get(i).getTitle());
        note.setText(items.get(i).getText());

        viewHolder.title.setText(note.getTitle());
        viewHolder.description.setText(note.getText());

        if(note.getFavorite()){
            viewHolder.start_on.setVisibility(View.VISIBLE);
            viewHolder.start_off.setVisibility(View.GONE);
        }

        viewHolder.noteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), DetailNote.class);
                intent.putExtra("idNote", note.getNoteId()+"");
                v.getContext().startActivity(intent);
            }
        });

        viewHolder.start_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.start_off.setVisibility(View.VISIBLE);
                viewHolder.start_on.setVisibility(View.GONE);
                dbNotesAdapter.updateFavorite(note.getNoteId(),false);
            }
        });

        viewHolder.start_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.start_off.setVisibility(View.GONE);
                viewHolder.start_on.setVisibility(View.VISIBLE);
                dbNotesAdapter.updateFavorite(note.getNoteId(),true);
            }
        });

    }

    public void limpiar()
    {
        items.clear();
        notifyDataSetChanged();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    public void insert(int position, Note data) {
        items.add(position, data);
        notifyItemInserted(position);
    }

    public void remove(Note data) {
        int position = items.indexOf(data);
        items.remove(position);
        notifyItemRemoved(position);
    }


}