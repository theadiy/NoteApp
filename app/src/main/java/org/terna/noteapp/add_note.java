package org.terna.noteapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class add_note extends AppCompatActivity implements View.OnClickListener {

    EditText noteTitle;
    EditText noteDescription;
    Button saveButton;
    String title;
    String description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        noteTitle = findViewById(R.id.titleEditText);
        noteDescription = findViewById(R.id.descriptionEditText);
        saveButton = findViewById(R.id.addnoteButton);
        saveButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        title = noteTitle.getText().toString();
        description = noteDescription.getText().toString();

        NoteDB db = new NoteDB(this,"notedb.db",null,1);
        db.insertIntoNote(title,description);
        Toast.makeText(getApplicationContext(),"Added ",Toast.LENGTH_SHORT).show();
    }
}
