package com.bawa.diary.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.bawa.diary.model.Student;
import com.bawa.diary.util.Constants;

import org.w3c.dom.ls.LSInput;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    public DatabaseHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION );
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENT_TABLE = "CREATE TABLE " + Constants.TABLE_NAME + "("
                + Constants.KEY_ID + " INTEGER PRIMARY KEY, "
                + Constants.KEY_NAME + " TEXT, "
                + Constants.KEY_CLASS + " TEXT, "
                + Constants.KEY_ADDRESS + " TEXT, "
                + Constants.KEY_CONTACT + " INTEGER, "
                + Constants.KEY_DATE_ADDED + " LONG );";

        db.execSQL(CREATE_STUDENT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Todo: Drop the old table
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);

        //Todo: create a new database
        onCreate(db);
    }
//Log.d("DBHandler", "student added: " + student.getName() + student.getContact() + student.getDateAdded());

    public void addStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Constants.KEY_NAME, student.getName());
        values.put(Constants.KEY_CLASS, student.getStudent_class());
        values.put(Constants.KEY_ADDRESS, student.getStudent_address());
        values.put(Constants.KEY_CONTACT, student.getContact());
        values.put(Constants.KEY_DATE_ADDED, java.lang.System.currentTimeMillis());

        db.insert(Constants.TABLE_NAME, null, values);
    }

    // Query one student
    public Student getOneStudent(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Student student;
        try (Cursor cursor = db.query( Constants.TABLE_NAME,
                new String[]{
                        Constants.KEY_ID,
                        Constants.KEY_NAME,
                        Constants.KEY_CLASS,
                        Constants.KEY_ADDRESS,
                        Constants.KEY_CONTACT,
                        Constants.KEY_DATE_ADDED},
                Constants.KEY_ID + "=?",
                new String[]{String.valueOf( id )},
                null, null, null, null )) {

            if (cursor != null)
                cursor.moveToFirst();
            student = new Student();
            if (cursor != null) {
                student.setId( Integer.parseInt( cursor.getString( cursor.getColumnIndex( Constants.KEY_ID ) ) ) );
                student.setName( cursor.getString( cursor.getColumnIndex( Constants.KEY_NAME ) ) );
                student.setStudent_address( cursor.getString( cursor.getColumnIndex( Constants.KEY_ADDRESS ) ) );
                student.setStudent_class( cursor.getString( cursor.getColumnIndex( Constants.KEY_CLASS ) ) );
                student.setContact( (long) cursor.getInt( cursor.getColumnIndex( Constants.KEY_CONTACT ) ) );

                DateFormat dateFormat = DateFormat.getDateInstance();
                String formattedDate = dateFormat.format( new Date( cursor.getLong( cursor.getColumnIndex( Constants.KEY_DATE_ADDED ) ) )
                        .getTime() );

                student.setDateAdded( formattedDate );
            }
        }

        return student;
    }

    public List<Student>getAllStudents(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Student>studentList = new ArrayList<>();

        try (Cursor cursor = db.query( Constants.TABLE_NAME,
                new String[]{
                        Constants.KEY_ID,
                        Constants.KEY_NAME,
                        Constants.KEY_CLASS,
                        Constants.KEY_ADDRESS,
                        Constants.KEY_CONTACT,
                        Constants.KEY_DATE_ADDED},
                null, null, null, null,
                Constants.KEY_DATE_ADDED + " DESC " )) {
            if (cursor.moveToFirst()) {
                do {
                    Student student = new Student();
                    student.setId( Integer.parseInt( cursor.getString( cursor.getColumnIndex( Constants.KEY_ID ) ) ) );
                    student.setName( cursor.getString( cursor.getColumnIndex( Constants.KEY_NAME ) ) );
                    student.setStudent_class( cursor.getString( cursor.getColumnIndex( Constants.KEY_CLASS ) ) );
                    student.setStudent_address( cursor.getString( cursor.getColumnIndex( Constants.KEY_ADDRESS ) ) );
                    student.setContact( cursor.getLong( cursor.getColumnIndex( Constants.KEY_CONTACT ) ) );

                    DateFormat dateFormat = DateFormat.getInstance();
                    String formattedDate = dateFormat.format( new Date( cursor.getLong( cursor.getColumnIndex( Constants.KEY_DATE_ADDED ) ) )
                            .getTime() );
                    student.setDateAdded( formattedDate );

                    studentList.add( student );
                } while (cursor.moveToNext());
            }
        }
        return studentList;
    }

    public int updateStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Constants.KEY_NAME, student.getName());
        values.put(Constants.KEY_CLASS, student.getStudent_class());
        values.put(Constants.KEY_ADDRESS, student.getStudent_address());
        values.put(Constants.KEY_CONTACT, student.getContact());
        values.put(Constants.KEY_DATE_ADDED, java.lang.System.currentTimeMillis());

        return db.update(Constants.TABLE_NAME, values,
                Constants.KEY_ID + "=?",
                new String[]{String.valueOf(student.getId())});
    }

    // Todo: Delete Student
    public void deleteStudent(int id){
    SQLiteDatabase db = this.getWritableDatabase();
    db.delete(Constants.TABLE_NAME, Constants.KEY_ID + "=?",
            new String[]{String.valueOf(id)});

    // close db
        db.close();
    }

    // Todo:getItemCount
    public int getItemCount(){
        String countQuery ="SELECT * FROM " + Constants.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        try (Cursor cursor = db.rawQuery( countQuery, null )) {
            return cursor.getCount();
        }

    }
}
