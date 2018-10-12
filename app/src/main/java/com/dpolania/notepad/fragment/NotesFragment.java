package com.dpolania.notepad.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.dpolania.notepad.R;
import com.dpolania.notepad.adapter.DBNotesAdapter;
import com.dpolania.notepad.adapter.NotesAdapter;
import com.dpolania.notepad.app.CreateNote;
import com.dpolania.notepad.entities.Note;

import java.util.ArrayList;
import java.util.List;

public class NotesFragment extends Fragment {


    RecyclerView recycler;
    NotesAdapter adapter;
    RecyclerView.LayoutManager lManager;
    LinearLayoutManager mLayoutManager;
    View rootView;
    List<Note> items = new ArrayList<>();
    DBNotesAdapter dbNotesAdapter = new DBNotesAdapter();


    public NotesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_notes, container, false);

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity().getApplicationContext(), CreateNote.class));
            }
        });

        recycler = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recycler.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);


        lManager = new LinearLayoutManager(getActivity());


        recycler.setLayoutManager(mLayoutManager);
        items = dbNotesAdapter.getNotes();
        adapter = new NotesAdapter(items);
        recycler.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(adapter != null ){
            updateNotes();
        }
    }

    public void updateNotes() {
        items = dbNotesAdapter.getNotes();
        adapter.setItems(items);
        adapter.notifyDataSetChanged();
    }
}
