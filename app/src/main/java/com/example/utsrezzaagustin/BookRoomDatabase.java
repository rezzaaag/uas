package com.example.utsrezzaagustin;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Book.class}, version = 1, exportSchema = false)
public abstract class BookRoomDatabase extends RoomDatabase {

    public abstract BookDao bookDao();

    private static volatile BookRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static BookRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (BookRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            BookRoomDatabase.class, "book_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}