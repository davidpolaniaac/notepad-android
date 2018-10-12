package com.dpolania.notepad.db;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = NotesDatabase.NAME, version = NotesDatabase.VERSION)
public class NotesDatabase {
    public static final int VERSION = 1;
    public static final String NAME = "Notes";
}