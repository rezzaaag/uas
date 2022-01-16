package com.example.utsrezzaagustin;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

class BookRepository {

    private BookDao bookDao;
    private LiveData<List<Book>> allBooks;

    BookRepository(Application application) {
        BookRoomDatabase db = BookRoomDatabase.getDatabase(application);
        bookDao = db.bookDao();
        allBooks = bookDao.getAlphabetizedWords();
    }

    LiveData<List<Book>> getAllBooks() {
        return allBooks;
    }

    void insert(Book book) {
        BookRoomDatabase.databaseWriteExecutor.execute(() -> {
            bookDao.insert(book);
        });
    }
}
