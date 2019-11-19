package com.example.roomsample.dao;

import com.example.roomsample.entity.Todo;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)    // 충돌 전략 설정 가능
    void insert(Todo todo);
    @Update(onConflict = OnConflictStrategy.ABORT)   // Default
    void update(Todo todo);
    @Delete
    void delete(Todo tod);

    @Query("SELECT * FROM todo_table")
    List<Todo> getAll();

    @Query("DELETE FROM todo_table")
    void deleteAll();

    // 메서드랑 쿼리를 매칭 시킬 수 있음
    @Query("SELECT * FROM todo_table WHERE todo_title = :date")
    Todo getTodoByDate(String date);

}
