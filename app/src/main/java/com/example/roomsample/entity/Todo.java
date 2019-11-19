package com.example.roomsample.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// 테이블명을 클래스명과 다르게 하고 싶을 경우 사용
@Entity(tableName = "todo_table")   // , primaryKeys = {"title","date"}   <-- Primary Key를 여러개 지정하고 싶을때
public class Todo {

    // Index값 AutoIncrement 설정
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    //변수명과 다르게 실제 컬럼명을 지정하고 싶을경우 사용
    @ColumnInfo(name = "todo_title")
    private String title;
    @ColumnInfo(name = "todo_date")
    private String date;
    @ColumnInfo(name = "todo_contents")
    private String contents;

    public Todo(String title, String date, String contents) {
        this.title = title;
        this.date = date;
        this.contents = contents;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

}
