package org.terna.noteapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateNote extends AppCompatActivity implements View.OnClickListener {

    EditText note_title;
    EditText note_description;
    Button updateButton;
    int noteid;
    String notetitle;
    String notedescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);

        note_title = findViewById(R.id.updateNoteTitleEditText);
        note_description = findViewById(R.id.updateNoteDescriptionEditText);
        updateButton = findViewById(R.id.updateNoteButton);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            noteid = (int) bd.get("note_id");
            notetitle = (String) bd.get("note_title");
            notedescription = (String) bd.get("note_description");
        }

        note_title.setText(notetitle);
        note_description.setText(notedescription);



        updateButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        notetitle = note_title.getText().toString();
        notedescription = note_description.getText().toString();

        NoteDB db = new NoteDB(this,"notedb.db",null,1);
        db.UpdateNoteWith(noteid,notetitle,notedescription);
        Toast.makeText(getApplicationContext(),"Updated",Toast.LENGTH_SHORT).show();

    }
}
