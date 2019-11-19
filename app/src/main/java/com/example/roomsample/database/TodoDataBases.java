package com.example.roomsample.database;

import android.content.Context;

import com.example.roomsample.dao.TodoDao;
import com.example.roomsample.entity.Todo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// 엔티티 여러개 추가 가능
@Database(entities = {Todo.class}, version = 1, exportSchema = false)
public abstract class TodoDataBases extends RoomDatabase {

    //
    public abstract TodoDao todoDao();
    // 리소스때문에 싱글톤 권장
    private static TodoDataBases INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExcutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static TodoDataBases getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (TodoDataBases.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TodoDataBases.class, "todo_database")
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}
