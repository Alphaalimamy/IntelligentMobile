package com.bawa.diary.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawa.diary.R;
import com.bawa.diary.data.DatabaseHelper;
import com.bawa.diary.model.Student;
import com.google.android.material.snackbar.Snackbar;


import java.text.MessageFormat;
import java.util.List;

import static com.bawa.diary.R.*;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder>{

    private Context context;
    private List<Student>studentList;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
    private LayoutInflater inflater;

    public StudentAdapter(Context context, List<Student> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public StudentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate( layout.list_row, parent, false);
        return new MyViewHolder(v, context);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.MyViewHolder holder, int position) {
            Student student = studentList.get(position);
            holder.studentName.setText(MessageFormat.format( "Name: {0}", student.getName() ) );
            holder.studentClass.setText( MessageFormat.format( "Course: {0}", student.getStudent_class() ) );
            holder.studentAddress.setText( MessageFormat.format( "Address: {0}", student.getStudent_address() ) );
            holder.studentContact.setText( MessageFormat.format( "Contact: {0}", String.valueOf( student.getContact() ) ) );
            holder.dateAdded.setText( MessageFormat.format( "Date Added: {0}", student.getDateAdded() ) );
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
        public TextView studentName;
        public TextView studentClass;
        public TextView studentAddress;
        public TextView studentContact;
        public TextView dateAdded;
        public int id;
        public Button editButton;
        public Button deleteButton;

        public MyViewHolder(@NonNull View itemView, Context ctx) {
            super( itemView );

            context = ctx;
            studentName = itemView.findViewById(R.id.name_list);
            studentClass = itemView.findViewById(R.id.class_list);
            studentAddress = itemView.findViewById(R.id.add_list);
            studentContact = itemView.findViewById(R.id.contact_list);
            dateAdded = itemView.findViewById(R.id.dateAdded_list);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);

            editButton.setOnClickListener(this);
            deleteButton.setOnClickListener(this);

        }


        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View v) {
            int position =getAdapterPosition();
            Student student = studentList.get(position);

            switch (v.getId()){

                case R.id.editButton:
                    // Todo: edit student info
                    editStudent(student);
                    break;

                case R.id.deleteButton:
                    // Todo: delete student info
                    deleteStudent(student.getId());
                    break;
            }
        }

        // Todo: Implement Delete Method
        private void deleteStudent(int id) {
            builder = new AlertDialog.Builder(context);
            inflater =LayoutInflater.from(context);
            View v  =  inflater.inflate( layout.confirmation, null);

           // View v = inflater.inflate(R.layout.confirmation, null);
            Button yesButton =v.findViewById(R.id.conf_yes_button);
            Button noButton =v.findViewById(R.id.conf_no_button);

            yesButton.setOnClickListener( v1 -> {
            DatabaseHelper db = new DatabaseHelper(context);
            db.deleteStudent(id);
            studentList.remove(getAdapterPosition());


                //notifyDataSetChanged();
                notifyItemRemoved(getAdapterPosition());
                alertDialog.dismiss();
            } );

            noButton.setOnClickListener( v12 -> alertDialog.dismiss() );
            builder.setView(v);
            alertDialog = builder.create();
            alertDialog.show();
            //alertDialog.setCancelable(false);


        }

        // Todo: Implement Edit Method
        private void editStudent(Student newStudent) {
            builder =new AlertDialog.Builder(context);
            inflater =LayoutInflater.from(context);
            View v  =  inflater.inflate( layout.pop_up, null);
            Button saveButton;
            EditText studentName;
            EditText studentClass;
            EditText studentAddress;
            EditText studentContact;
            TextView title;


            studentName = v.findViewById(R.id.name);
            studentClass = v.findViewById(R.id.class_name);
            studentAddress = v.findViewById(R.id.address);
            studentContact = v.findViewById(R.id.contact);
            title = v.findViewById(R.id.title);
            saveButton = v.findViewById(R.id.btnSave);

            title.setText( string.edit_stud);
            studentName.setText(newStudent.getName());
            studentClass.setText(newStudent.getStudent_class());
            studentAddress.setText(newStudent.getStudent_address());
            studentContact.setText(String.valueOf(newStudent.getContact()));
            saveButton.setText( string.update_student);

            builder.setView(v);
            alertDialog = builder.create();
            alertDialog.show();

            saveButton.setOnClickListener( v1 -> {

                DatabaseHelper db = new DatabaseHelper(context);

                //Todo: Update Student
                newStudent.setName(studentName.getText().toString());
                newStudent.setStudent_class(studentClass.getText().toString());
                newStudent.setStudent_address(studentAddress.getText().toString());
                newStudent.setContact(Long.parseLong(studentContact.getText().toString()) );

                if (!studentName.getText().toString().isEmpty()
                    && !studentAddress.getText().toString().isEmpty()
                    && !studentClass.getText().toString().isEmpty()
                    && !studentContact.getText().toString().isEmpty()){
                    notifyItemChanged(getAdapterPosition(), newStudent);
                    db.updateStudent(newStudent);
                    Snackbar.make( v1, "Student Successfull Updated", Snackbar.LENGTH_LONG)
                            .show();
                    Log.d("Update", "onClick: " + newStudent.getName());

                } else {
                    Snackbar.make( v1, "Fields empty", Snackbar.LENGTH_LONG).show();
                }
                alertDialog.dismiss();
            } );

        }
    }
}
