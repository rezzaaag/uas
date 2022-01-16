package com.example.utsrezzaagustin;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class BookViewModel extends AndroidViewModel {

    private BookRepository mRepository;

    private final LiveData<List<Book>> allBooks;

    public BookViewModel (Application application) {
        super(application);
        mRepository = new BookRepository(application);
        allBooks = mRepository.getAllBooks();
    }

    LiveData<List<Book>> getAllBooks() { return allBooks; }

    public void insert(Book book) { mRepository.insert(book); }
}
