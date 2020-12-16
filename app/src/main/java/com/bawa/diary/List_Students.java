package com.bawa.diary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bawa.diary.data.DatabaseHelper;
import com.bawa.diary.model.Student;
import com.bawa.diary.ui.StudentAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class List_Students extends AppCompatActivity {
    private static final String TAG = "ListActivity" ;
    private DatabaseHelper databaseHelper;
    private RecyclerView recyclerView;
    private StudentAdapter studentAdapter;
    private List<Student>studentList;


    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
    private FloatingActionButton fab;

    private EditText studentName;
    private EditText studentClass;
    private EditText studentAddress;
    private EditText studentContact;
    private Button saveButton;
    private Button editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__students);

        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.fabAdd);

        databaseHelper = new DatabaseHelper(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        studentList =new ArrayList<>();

        //get Data from the database
        studentList = databaseHelper.getAllStudents();

        for (Student s: studentList){
            Log.d(TAG, "onCreate:" + s.getName());
        }

        studentAdapter = new StudentAdapter(this, studentList);
        recyclerView.setAdapter(studentAdapter);
        studentAdapter.notifyDataSetChanged();

        fab.setOnClickListener( v -> createPopDialog() );
    }

    private void createPopDialog() {
        builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.pop_up, null);

        studentName = view.findViewById(R.id.name);
        studentClass = view.findViewById(R.id.class_name);
        studentAddress =  view.findViewById(R.id.address);
        studentContact = view.findViewById(R.id.contact);
        saveButton = view.findViewById(R.id.btnSave);

        saveButton.setOnClickListener( v -> {
            if (!studentName.getText().toString().isEmpty()
                && !studentClass.getText().toString().isEmpty()
                && !studentAddress.getText().toString().isEmpty()
                && !studentContact.getText().toString().isEmpty()){


                saveItem(v);
            } else {
                Snackbar.make(v, "Please Fill all fields", Snackbar.LENGTH_SHORT)
                        .show();
            }
        } );
        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.show();
    }

    public void saveItem(View view){

        Student student = new Student();
        String newStudentName = studentName.getText().toString().trim();
        String newStudentClass = studentClass.getText().toString().trim();
        String newStudentAdd = studentAddress.getText().toString().trim();
        long newStudentCon = (Long.parseLong(studentContact.getText().toString().trim()));


        student.setName(newStudentName);
        student.setStudent_class(newStudentClass);
        student.setStudent_address(newStudentAdd);
        student.setContact(newStudentCon );

        databaseHelper.addStudent(student);
        Snackbar.make(view, "Student saved", Snackbar.LENGTH_SHORT)
                .show();

        new Handler().postDelayed( () -> {
            alertDialog.dismiss();
            Intent intent = new Intent(List_Students.this, List_Students.class);
            startActivity(intent);
        }, 1200);
    }
}

