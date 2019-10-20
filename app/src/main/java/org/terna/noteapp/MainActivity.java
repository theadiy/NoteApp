package org.terna.noteapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView noteRecyclerView;
    ArrayList<NoteModel> notes;
    Button addButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = findViewById(R.id.addNoteButton);
        addButton.setOnClickListener(this);

        RefreshNote();


    }

    public void RefreshNote(){

        NoteDB db = new NoteDB(this,"notedb.db",null,1);
        //db.insertIntoNote("first note","This is note 1");
        //db.insertIntoNote(2,"second note","This is note 2");
        //db.deleteNoteWith(1);




        notes = db.getAllNotes();

        noteRecyclerView = findViewById(R.id.noteRecyclerView);
        noteRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        if(notes !=null){
            noteRecyclerView.setAdapter(new NoteAdapter());
        }




    }
    @Override
    public void onClick(View v) {
        Intent newActivity = new Intent(this,add_note.class);
        startActivity(newActivity);
    }

    class NoteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {





        class NoteItem extends RecyclerView.ViewHolder{

            TextView noteTitleTextView;
            Button deleteNoteButton;
            public NoteItem(View itemView) {
                super(itemView);
                noteTitleTextView = itemView.findViewById(R.id.noteTitleTextView);
                deleteNoteButton = itemView.findViewById(R.id.deleteNoteButton);
            }
        }
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_note,parent,false);
            return new NoteItem(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

            final NoteModel note =notes.get(position);

            ((NoteItem)holder).noteTitleTextView.setText(note.note_title);
            ((NoteItem)holder).noteTitleTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent newActivity = new Intent(MainActivity.this,UpdateNote.class);
                    newActivity.putExtra("note_id",note.note_id);
                    newActivity.putExtra("note_title",note.note_title);
                    newActivity.putExtra("note_description",note.note_description);
                    startActivity(newActivity);
                }
            });

            ((NoteItem)holder).deleteNoteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("OnBindVieHolder","this will delete "+note.note_id);
                    NoteDB db = new NoteDB(MainActivity.this,"notedb.db",null,1);
                    db.deleteNoteWith(note.note_id);
                    RefreshNote();
                    Toast.makeText(getApplicationContext(),"Deleted ",Toast.LENGTH_SHORT).show();
                }
            });

        }


        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        @Override
        public int getItemCount() {
            return notes.size();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        RefreshNote();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }


}
