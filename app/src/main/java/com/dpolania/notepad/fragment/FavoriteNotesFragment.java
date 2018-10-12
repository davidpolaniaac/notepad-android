package com.dpolania.notepad.fragment;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dpolania.notepad.R;
import com.dpolania.notepad.adapter.DBNotesAdapter;
import com.dpolania.notepad.adapter.NotesAdapter;
import com.dpolania.notepad.entities.Note;

import java.util.ArrayList;
import java.util.List;


public class FavoriteNotesFragment extends Fragment {


    RecyclerView recycler;
    NotesAdapter adapter;
    RecyclerView.LayoutManager lManager;
    LinearLayoutManager mLayoutManager;
    View rootView;
    List<Note> items = new ArrayList<>();
    DBNotesAdapter dbNotesAdapter = new DBNotesAdapter();

    public FavoriteNotesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_favorite_notes, container, false);

        recycler = (RecyclerView) rootView.findViewById(R.id.recycler_view_favorite);
        recycler.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        lManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(mLayoutManager);
        items = dbNotesAdapter.getFavoritesNotes();
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
        items = dbNotesAdapter.getFavoritesNotes();
        adapter.setItems(items);
        adapter.notifyDataSetChanged();
    }

}
