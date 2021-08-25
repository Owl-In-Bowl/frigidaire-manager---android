package com.example.android.roomwordssample;

/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

//TODO : add button to erase database

//TODO : add a way to erase a specifique aliment (inside recycle view holder ??)
//TODO : separate l'affichage de l'ajout
//TODO : ajouter un toast pour signaler le résumé de l'ajout
//TODO : ajouter les attributs trigramme
//TODO : ajouter les attributs dates
//TODO : ajouter le systeme d'affichage du temps restant
//TODO : signalétique de péremption
//TODO : ajouter du style à l'application


//TODO : ajouter une liste de course

//TODO : transformer le tout en storage manager
    // vaec un coin frigo
    // un coin jeu
    // un coin autre truc nanana
    // etc


import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    private AlimentViewModel mAlimentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final AlimentListAdapter adapter = new AlimentListAdapter(new AlimentListAdapter.AlimentDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get a new or existing ViewModel from the ViewModelProvider.
        mAlimentViewModel = new ViewModelProvider(this).get(AlimentViewModel.class);

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mAlimentViewModel.getAllAliment().observe(this, aliment -> {
            // Update the cached copy of the words in the adapter.
            adapter.submitList(aliment);
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, NewAlimentActivity.class);
            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Aliment aliment = new Aliment(data.getStringExtra(NewAlimentActivity.EXTRA_REPLY));
            mAlimentViewModel.insert(aliment);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}
