package com.example.mobileapplicationdevelopment_2_;


import android.app.Application;
import androidx.lifecycle.LiveData;

import java.util.List;

class WordRepository {

    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAlphabetizedWords();
    }


    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    void insert(Word word) {
        WordRoomDatabase.databaseWriteExecutor.execute(() -> {
            mWordDao.insert(word);
        });
    }
}

