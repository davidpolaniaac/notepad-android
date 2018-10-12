package com.dpolania.notepad.adapter;

import com.dpolania.notepad.entities.Note;

import java.util.List;

public interface IDBNotesAdapter {

    void createNote(String title, String text, boolean favorite);
    void updateNote(Note note);
    void deleteNote(Note note);
    List<Note> getNotes();
    List<Note> getFavoritesNotes();
    List<Note> getNotes(String text);
    Note getNote(int noteId);
    void updateFavorite(int idNote, boolean value);


}
