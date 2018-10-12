package com.dpolania.notepad.adapter;

import com.dpolania.notepad.entities.Note;
import com.dpolania.notepad.entities.Note_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class DBNotesAdapter implements IDBNotesAdapter {

    @Override
    public void createNote(String title, String text, boolean favorite) {

        Note note = new Note();
        note.setTitle(title);
        note.setText(text);
        note.setFavorite(favorite);
        note.save();
    }

    @Override
    public void updateNote(Note note) {
        note.update();
    }

    @Override
    public void deleteNote(Note note) {
        note.delete();
    }

    @Override
    public List<Note> getNotes() {
        return SQLite.select().from(Note.class).queryList();
    }

    @Override
    public List<Note> getFavoritesNotes() {
        return SQLite.select().from(Note.class).where(Note_Table.favorite.eq(true)).queryList();
    }

    @Override
    public List<Note> getNotes(String text) {
        return SQLite.select().from(Note.class).where(Note_Table.title.like(text)).or(Note_Table.text.like(text)).queryList();
    }

    @Override
    public Note getNote(int noteId) {

        Note note = SQLite.select().from(Note.class).where(Note_Table.noteId.eq(noteId)).querySingle();
        return note;
    }

    @Override
    public void updateFavorite(int idNote, boolean value) {
        SQLite.update(Note.class)
                .set(Note_Table.favorite.eq(value))
                .where(Note_Table.noteId.is(idNote)).async().execute();
    }
}
