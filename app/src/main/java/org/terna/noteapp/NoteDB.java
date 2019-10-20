package org.terna.noteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class NoteDB extends SQLiteOpenHelper {
    public static String NOTE = "NOTE";
    public static String CREATE_NOTE_TABLE = "CREATE TABLE "+NOTE+"(note_id INTEGER , note_title TEXT, note_description TEXT)";
    public static String SELECT_ALL_NOTE ="SELECT * FROM "+ NOTE;
    public NoteDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_NOTE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertIntoNote(String note_title,String note_description){

        int note_id = getAllNotes().size() + 1;
        Log.e("NOTEDB","LAST ID is "+note_id);
        ContentValues contentValues = new ContentValues();
        contentValues.put("note_id",note_id);
        contentValues.put("note_title",note_title);
        contentValues.put("note_description",note_description);


        SQLiteDatabase database = getWritableDatabase();

        long rowId = database.insert(NOTE,null,contentValues);

        if (rowId > 0){
            return true;

        }else {
            return false;
        }


    }

    public boolean UpdateNoteWith(int note_id,String note_title,String note_description){
        ContentValues contentValues = new ContentValues();
        contentValues.put("note_title",note_title);
        contentValues.put("note_description",note_description);

        String whereClause = "note_id = ?";
        String WhereArgs[] = new String[]{String.valueOf(note_id)};

        SQLiteDatabase database = getWritableDatabase();
        long rowId  = database.update(NOTE,contentValues,whereClause,WhereArgs);

        if (rowId >0){
            return true;

        }
        else
        {
            return false;
        }
    }

    public boolean deleteNoteWith(int note_id){

        String whereClause = "note_id = ?";
        String WhereArgs[] = new String[]{String.valueOf(note_id)};

        SQLiteDatabase database = getWritableDatabase();
        long rowId  = database.delete(NOTE,whereClause,WhereArgs);
        if (rowId >0){
            return true;

        }
        else
        {
            return false;
        }
    }

    public ArrayList<NoteModel> getAllNotes(){
        ArrayList<NoteModel> notes = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(SELECT_ALL_NOTE,null);

        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                NoteModel person = new NoteModel();
                person.note_id = cursor.getInt(cursor.getColumnIndex("note_id"));
                person.note_title = cursor.getString(cursor.getColumnIndex("note_title"));
                person.note_description = cursor.getString(cursor.getColumnIndex("note_description"));

                notes.add(person);
                cursor.moveToNext();
            }

            cursor.close();
        }
        database.close();

        return notes;
    }
}
