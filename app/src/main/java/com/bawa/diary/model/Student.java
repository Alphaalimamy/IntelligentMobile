package com.bawa.diary.model;

public class Student {

    private int id;
    private String name;
    private String student_class;
    private String student_address;
    private Long contact;
    private String dateAdded;

    public Student() {
    }

    public Student(String name, String student_class, String student_address, Long contact) {
        this.name = name;
        this.student_class = student_class;
        this.student_address = student_address;
        this.contact = contact;
    }

    public Student(int id, String name, String student_class, String student_address, Long contact) {
        this.id = id;
        this.name = name;
        this.student_class = student_class;
        this.student_address = student_address;
        this.contact = contact;
    }

    public Student(int id, String name, String student_class, String student_address, Long contact, String dateAdded) {
        this.id = id;
        this.name = name;
        this.student_class = student_class;
        this.student_address = student_address;
        this.contact = contact;
        this.dateAdded = dateAdded;
    }

    public Student(String name, String student_class, String student_address, Long contact, String dateAdded) {
        this.name = name;
        this.student_class = student_class;
        this.student_address = student_address;
        this.contact = contact;
        this.dateAdded = dateAdded;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudent_class() {
        return student_class;
    }

    public void setStudent_class(String student_class) {
        this.student_class = student_class;
    }

    public String getStudent_address() {
        return student_address;
    }

    public void setStudent_address(String student_address) {
        this.student_address = student_address;
    }

    public Long getContact() {
        return contact;
    }

    public void setContact(Long contact) {
        this.contact = contact;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }
}
