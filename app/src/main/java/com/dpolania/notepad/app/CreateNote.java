package com.dpolania.notepad.app;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.dpolania.notepad.R;
import com.dpolania.notepad.adapter.DBNotesAdapter;


public class CreateNote extends AppCompatActivity {

    private AutoCompleteTextView mTitleView;
    private EditText mDescriptionView;
    private View mProgressView;

    public static final String EXTRA_MESSAGE = "mensaje";

    DBNotesAdapter dbNotesAdapter = new DBNotesAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note_container);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mTitleView = (AutoCompleteTextView) findViewById(R.id.title);
        mDescriptionView = (EditText) findViewById(R.id.description);

        Button mCreateNoteButton = (Button) findViewById(R.id.crete_note_in_button);
        mCreateNoteButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = mTitleView.getText().toString();
                String decription = mDescriptionView.getText().toString();
                dbNotesAdapter.createNote(title,decription,false);
                finish();
            }
        });

        mProgressView = findViewById(R.id.create_progress);
    }

    private boolean isTitleValid(String title) {
        return title.length() > 4;
    }

    private boolean isDescriptionValid(String description) {
        return description.length() > 4;
    }

}

