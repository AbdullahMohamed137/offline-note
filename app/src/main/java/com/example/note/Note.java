package com.example.note;

public class Note {
    private String Title ;
    private String Note;
    private String Date ;
    private int id;

    public Note() {
    }
    public Note( String title) {
        Title = title;

    }
    public Note(int id, String title, String note) {
        this.id = id;
        Title = title;
        Note = note;
    }
    public Note( String title, String note, String date) {
        Title = title;
        Note = note;
        Date = date;
    }

    public Note(int id, String title, String note, String date) {
        this.id = id;
        Title = title;
        Note = note;
        Date = date;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
