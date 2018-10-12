package com.dpolania.notepad.entities;

import com.dpolania.notepad.db.NotesDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ConflictAction;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.annotation.Unique;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = NotesDatabase.class)
public class Note extends BaseModel {

    @PrimaryKey(autoincrement = true) private int noteId;

    @Column private String title;

    @Column private String text;

    @Column private boolean favorite;

    public boolean isFavorite() {
        return favorite;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public boolean equals(Object obj) {
        boolean equal = false;

        if (obj instanceof Note){
            Note note = (Note)obj;
            equal = this.noteId==(note.getNoteId());
        }

        return equal;
    }
}

