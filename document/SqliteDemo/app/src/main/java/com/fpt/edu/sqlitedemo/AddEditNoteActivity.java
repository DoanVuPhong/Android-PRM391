package com.fpt.edu.sqlitedemo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fpt.edu.sqlitedemo.bean.Note;

public class AddEditNoteActivity extends AppCompatActivity {
    Note note;
    private static final int MODE_CREATE = 1;
    private static final int MODE_EDIT = 2;

    private int mode;
    private EditText textTitle;
    private EditText textContent;

    private Button btnSave, btnCancel;



    private boolean needRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_note);
        this.textTitle = (EditText) this.findViewById(R.id.txtTitle);
        this.textContent = (EditText) this.findViewById(R.id.txtContent);

        Intent intent = this.getIntent();
        this.note = (Note) intent.getSerializableExtra("note");
        if (note == null) {


            this.mode = MODE_CREATE;
        } else {
            this.mode = MODE_EDIT;
            this.textTitle.setText(note.getTitle());
            this.textContent.setText(note.getContent());
        }

        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper db = new MyDatabaseHelper(AddEditNoteActivity.this);

                String title = textTitle.getText().toString();
                String content = textContent.getText().toString();

                if(title.equals("") || content.equals("")) {
                    Toast.makeText(getApplicationContext(),
                            "Please enter title & content", Toast.LENGTH_LONG).show();
                    return;
                }

                if(mode==MODE_CREATE ) {
                    note= new Note(title,content);
                    db.addNote(note);
                } else  {
                    note.setTitle(title);
                    note.setContent(content);
                    db.update(note);
                }

                needRefresh = true;
                // Trở lại MainActivity.
                onBackPressed();
            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    @Override
    public void finish() {

        // Chuẩn bị dữ liệu Intent.
        Intent data = new Intent();
        // Yêu cầu MainActivity refresh lại ListView hoặc không.
        data.putExtra("needRefresh", needRefresh);

        // Activity đã hoàn thành OK, trả về dữ liệu.
        this.setResult(Activity.RESULT_OK, data);
        super.finish();
    }
}
