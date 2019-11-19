package com.example.roomsample;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roomsample.dao.TodoDao;
import com.example.roomsample.database.TodoDataBases;
import com.example.roomsample.entity.Todo;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String LOG_TAG = "RoomSample";

    EditText editTitle;
    EditText editDate;
    EditText editContents;

    Button btnSave;
    Button btnList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        startTodoDatabase();
    }

    private void initView() {

        // Input
        editTitle = findViewById(R.id.edit_title);
        editDate = findViewById(R.id.edit_date);
        editContents = findViewById(R.id.edit_contents);

        // Button
        btnSave = findViewById(R.id.btn_todo_save);
        btnSave.setOnClickListener(this);
        btnList = findViewById(R.id.btn_todo_list);
        btnList.setOnClickListener(this);

    }

    private void startTodoDatabase() {
        TodoDataBases db = TodoDataBases.getDatabase(this);
        //LiveData를 사용할 경우 observer를 달아서 db가 업데이트될때마다 자동으로 UI 업데이트

        List<Todo> todoList = db.todoDao().getAll();
        if(todoList != null && todoList.size() > 0) {
            for(Todo todo : todoList) {
                Log.d(LOG_TAG, "Title : " + todo.getTitle() + ", Date : " + todo.getDate() + ", Contents : " + todo.getContents());
            }
        }
    }

    private boolean checkValues() {
        if(TextUtils.isEmpty(editTitle.getText()) ||
                TextUtils.isEmpty(editContents.getText()) ||
                TextUtils.isEmpty(editDate.getText()))
            return false;
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_todo_save:
                if(checkValues()) {

                } else{
                    Toast.makeText(this, "내용을 넣어주세요!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_todo_list:
                break;
        }
    }


    // Room을 통한 DB 접근은 메인쓰레드에서 불가하기 때문에 AsyncTask 사용
    // RxJava나 코틀린으로 작성 한다면 코루틴 사용
    private static class TodoDataBaseAsyncTask extends AsyncTask<Todo, Void, Void> {

        private TodoDao mTodoDao;

        public TodoDataBaseAsyncTask(TodoDao todoDao) {
            this.mTodoDao = todoDao;
        }

        @Override
        protected Void doInBackground(Todo... todos) {

            return null;
        }

    }
}
