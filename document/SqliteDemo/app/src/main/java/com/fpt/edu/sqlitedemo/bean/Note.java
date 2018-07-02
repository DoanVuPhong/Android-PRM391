package com.fpt.edu.sqlitedemo.bean;

import java.io.Serializable;

public class Note implements Serializable {
    private int noteId;
    private String title;
    private String Content;

    public Note(int noteId, String title, String content) {
        this.noteId = noteId;
        this.title = title;
        Content = content;
    }

    public Note(String title, String content) {
        this.title = title;
        Content = content;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }


    @Override
    public String toString() {
        return title;
    }
}
