package com.dpolania.notepad.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.dpolania.notepad.R;
import com.dpolania.notepad.adapter.DBNotesAdapter;
import com.dpolania.notepad.adapter.NotesAdapter;
import com.dpolania.notepad.entities.Note;

import java.util.ArrayList;
import java.util.List;


public class SearchNotesFragment extends Fragment {


    RecyclerView recycler;
    NotesAdapter adapter;
    RecyclerView.LayoutManager lManager;
    LinearLayoutManager mLayoutManager;
    View rootView;
    List<Note> items = new ArrayList<>();
    DBNotesAdapter dbNotesAdapter = new DBNotesAdapter();
    private AutoCompleteTextView mTextView;
    private String text;

    public SearchNotesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_search_notes, container, false);
        recycler = (RecyclerView) rootView.findViewById(R.id.recycler_view_search);
        recycler.setHasFixedSize(true);
        mTextView = (AutoCompleteTextView) rootView.findViewById(R.id.search_text);
        text = mTextView.getText().toString();
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        lManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(mLayoutManager);
        items = dbNotesAdapter.getNotes(text);
        adapter = new NotesAdapter(items);
        recycler.setAdapter(adapter);

        Button mSearchNoteButton = (Button) rootView.findViewById(R.id.search_note_in_button);
        mSearchNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mTextView.getWindowToken(), 0);

                text = mTextView.getText().toString();
                items = dbNotesAdapter.getNotes(text);
                adapter = new NotesAdapter(items);
                adapter.notifyDataSetChanged();
                recycler.setAdapter(adapter);
            }
        });
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
        items = dbNotesAdapter.getNotes(text);
        adapter.setItems(items);
        adapter.notifyDataSetChanged();
    }

}
