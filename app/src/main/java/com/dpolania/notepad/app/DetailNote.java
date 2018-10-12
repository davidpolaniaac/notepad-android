package com.dpolania.notepad.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.dpolania.notepad.R;
import com.dpolania.notepad.adapter.DBNotesAdapter;
import com.dpolania.notepad.entities.Note;

public class DetailNote extends AppCompatActivity {

    private AutoCompleteTextView mTitleView;
    private EditText mDescriptionView;
    private Note note;
    private String idNote;

    DBNotesAdapter dbNotesAdapter = new DBNotesAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_note);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            idNote = extras.getString("idNote");
            mTitleView = (AutoCompleteTextView) findViewById(R.id.title);
            mDescriptionView = (EditText) findViewById(R.id.description);
            loadData();

            Button mUpdateNoteButton = (Button) findViewById(R.id.update_note_in_button);
            mUpdateNoteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String title = mTitleView.getText().toString();
                    String decription = mDescriptionView.getText().toString();
                    note.setTitle(title);
                    note.setText(decription);
                    note.setNoteId(Integer.parseInt(idNote));
                    dbNotesAdapter.updateNote(note);
                    finish();
                }
            });

            Button mDeleteNoteButton = (Button) findViewById(R.id.delete_note_in_button);
            mDeleteNoteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dbNotesAdapter.deleteNote(note);
                    finish();
                }
            });

        }

    }

    private void loadData(){
        note =  dbNotesAdapter.getNote(Integer.parseInt(idNote));
        mTitleView.setText(note.getTitle());
        mDescriptionView.setText(note.getText());
    }
}
