package com.example.utsrezzaagustin;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BookDao {

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Book book);

    @Query("DELETE FROM books")
    void deleteAll();

    @Query("SELECT * FROM books ORDER BY title ASC")
    LiveData<List<Book>> getAlphabetizedWords();
}
