package com.dpolania.notepad.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.dpolania.notepad.R;
import com.dpolania.notepad.fragment.FavoriteNotesFragment;
import com.dpolania.notepad.fragment.NotesFragment;
import com.dpolania.notepad.fragment.SearchNotesFragment;

public class MainActivity extends AppCompatActivity {

    private ActionBar toolbar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        Fragment fragment;

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.favorite:
                    toolbar.setTitle("Favorites");
                    fragment = new FavoriteNotesFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.notes_dashboard:
                    toolbar.setTitle("Notes");
                    fragment = new NotesFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.search_note:
                    toolbar.setTitle("Search");
                    fragment = new SearchNotesFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = getSupportActionBar();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        toolbar.setTitle("Notes");
        Fragment fragment = new NotesFragment();
        loadFragment(fragment);
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
