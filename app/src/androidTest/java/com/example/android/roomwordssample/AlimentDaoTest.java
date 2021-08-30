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

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

/**
 * This is not meant to be a full set of tests. For simplicity, most of your samples do not
 * include tests. However, when building the Room, it is helpful to make sure it works before
 * adding the UI.
 */

@RunWith(AndroidJUnit4.class)
public class AlimentDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private AlimentDao mAlimentDao;
    private AlimentRoomDatabase mDb;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        mDb = Room.inMemoryDatabaseBuilder(context, AlimentRoomDatabase.class)
                // Allowing main thread queries, just for testing.
                .allowMainThreadQueries()
                .build();
        mAlimentDao = mDb.alimentDao();
    }

    @After
    public void closeDb() {
        mDb.close();
    }

    @Test
    public void insertAndGetAliment() throws Exception {
        Aliment aliment = new Aliment("aliment", "LIM");
        mAlimentDao.insert(aliment);
        List<Aliment> allAliments = LiveDataTestUtil.getValue(mAlimentDao.getAlphabetizedWords());
        assertEquals(allAliments.get(0).getAlimentName(), aliment.getAlimentName());
    }

    @Test
    public void getAllAliment() throws Exception {
        Aliment aliment = new Aliment("aaa", "AAA");
        mAlimentDao.insert(aliment);
        Aliment aliment2 = new Aliment("bbb", "BBB");
        mAlimentDao.insert(aliment2);
        List<Aliment> allAliments = LiveDataTestUtil.getValue(mAlimentDao.getAlphabetizedWords());
        assertEquals(allAliments.get(0).getAlimentName(), aliment.getAlimentName());
        assertEquals(allAliments.get(1).getAlimentName(), aliment2.getAlimentName());
    }

    @Test
    public void deleteAll() throws Exception {
        Aliment aliment = new Aliment("word", "WOR");
        mAlimentDao.insert(aliment);
        Aliment aliment2 = new Aliment("word2", "WOR");
        mAlimentDao.insert(aliment2);
        mAlimentDao.deleteAll();
        List<Aliment> allAliments = LiveDataTestUtil.getValue(mAlimentDao.getAlphabetizedWords());
        assertTrue(allAliments.isEmpty());
    }
}
