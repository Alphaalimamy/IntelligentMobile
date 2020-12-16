package com.bawa.diary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bawa.diary.data.DatabaseHelper;
import com.bawa.diary.model.Student;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private AlertDialog dialog;
    private AlertDialog.Builder builder;

    private EditText studentName;
    private EditText studentClass;
    private EditText studentAddress;
    private EditText studentContact;
    private Button saveButton;
    private DatabaseHelper databaseHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        databaseHelper = new DatabaseHelper(this);



        fab = findViewById(R.id.fab);

        List<Student>students = databaseHelper.getAllStudents();

        // Todo: Display all students
        for (Student s: students){
          Log.d("Main", "onCreate: " + s.getName());
        }

        fab.setOnClickListener( v -> {

            // Todo: this launches the pop up
            showPopUp();
        } );

        byPassActivity();
    }

    //Todo: Go to second activity direct if there is data in the Database
    public void byPassActivity(){
        if (databaseHelper.getItemCount()>0){
            startActivity(new Intent(MainActivity.this, List_Students.class));
            finish();
        }
    }
    private void saveStudent(View v) {
        Student student = new Student();

        String newStudentName = studentName.getText().toString().trim();
        String newStudentClass = studentClass.getText().toString().trim();
        String newStudentAddress = studentAddress.getText().toString().trim();
        int newStudentContact = Integer.parseInt( studentContact.getText().toString().trim() );

        student.setName(newStudentName);
        student.setContact( (long) newStudentContact );
        student.setStudent_address(newStudentAddress);
        student.setStudent_class(newStudentClass);

        databaseHelper.addStudent(student);
        Snackbar.make(v, "Student save", Snackbar.LENGTH_LONG).show();

        new Handler().postDelayed( () -> {
            dialog.dismiss();
            startActivity(new Intent(MainActivity.this, List_Students.class));
        }, 1200);
    }

    private void showPopUp() {

        builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.pop_up, null);
        builder.setView(view);

        studentName = view.findViewById(R.id.name);
        studentClass = view.findViewById(R.id.class_name);
        studentAddress = view.findViewById(R.id.address);
        studentContact = view.findViewById(R.id.contact);
        saveButton = view.findViewById(R.id.btnSave);

        saveButton.setOnClickListener( v -> {
            if (!studentName.getText().toString().isEmpty()
                    && !studentAddress.getText().toString().isEmpty()
                    && !studentClass.getText().toString().isEmpty()
                    && !studentContact.getText().toString().isEmpty()){

                saveStudent(v);

            } else {
                Snackbar.make(v,"Empty Fields not Allowed",
                        Snackbar.LENGTH_SHORT).show();
            }
        } );
        dialog = builder.create();
        dialog.show();
    }
}