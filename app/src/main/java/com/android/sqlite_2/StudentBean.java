package com.android.sqlite_2;

public class StudentBean {

    // field
    // custom_layout.xml에 있는 textView와 imageView 선언! (다 쓸거니까!)
    private int studentid;
    private String studentname;
    private String studentmajor;
    private String studenttelno;


    // constructor

    public StudentBean(int studentid, String studentname, String studentmajor, String studenttelno) {
        this.studentid = studentid;
        this.studentname = studentname;
        this.studentmajor = studentmajor;
        this.studenttelno = studenttelno;
    }


    // getter setter


    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getStudentmajor() {
        return studentmajor;
    }

    public void setStudentmajor(String studentmajor) {
        this.studentmajor = studentmajor;
    }

    public String getStudenttelno() {
        return studenttelno;
    }

    public void setStudenttelno(String studenttelno) {
        this.studenttelno = studenttelno;
    }
}// end
