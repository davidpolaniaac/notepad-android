package com.dpolania.notepad;

import android.app.Application;
import com.raizlabs.android.dbflow.config.FlowManager;

public class NotesApp  extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initDB();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        DBTearDown();
    }

    private void initDB() {
        FlowManager.init(this);
    }

    private void DBTearDown() {
        FlowManager.destroy();
    }

}
