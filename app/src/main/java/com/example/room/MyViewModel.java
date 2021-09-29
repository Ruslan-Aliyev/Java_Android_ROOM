package com.example.room;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

public class MyViewModel extends AndroidViewModel {

    private String TAG = this.getClass().getSimpleName();
    private EntryDao entryDao;
    private DB db;
    private LiveData<List<Entry>> entries;

    public MyViewModel(Application application) {
        super(application);

        db = DB.getDatabase(application);
        entryDao = db.entryDao();
        entries = entryDao.getAllEntries();
    }

    LiveData<List<Entry>> getAllEntries() {
        return entries;
    }

    public void insert(Entry entry) {
        new InsertAsyncTask(entryDao).execute(entry);
    }

    private class OperationsAsyncTask extends AsyncTask<Entry, Void, Void> {

        EntryDao asyncTaskDao;

        OperationsAsyncTask(EntryDao dao) {
            this.asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Entry... entries) {
            return null;
        }
    }
    private class InsertAsyncTask extends OperationsAsyncTask {

        InsertAsyncTask(EntryDao entryDao) {
            super(entryDao);
        }

        @Override
        protected Void doInBackground(Entry... entries) {
            asyncTaskDao.insert(entries[0]);
            return null;
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG, "ViewModel Destroyed");
    }
}
