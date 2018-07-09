package com.fpt.edu.tiengviet123.bean;

import java.io.Serializable;

public class VCharacter implements Serializable{

private int id;
private String face;
private String content;
private int imageSrcId;
private int baseAudioId;
private int extentionAudioID;

    public VCharacter(int id, String face, String content, int imageSrcId, int baseAudioId, int extentionAudioID) {
        this.id = id;
        this.face = face;
        this.content = content;
        this.imageSrcId = imageSrcId;
        this.baseAudioId = baseAudioId;
        this.extentionAudioID = extentionAudioID;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImageSrcId() {
        return imageSrcId;
    }

    public void setImageSrcId(int imageSrcId) {
        this.imageSrcId = imageSrcId;
    }

    public int getBaseAudioId() {
        return baseAudioId;
    }

    public void setBaseAudioId(int baseAudioId) {
        this.baseAudioId = baseAudioId;
    }

    public int getExtentionAudioID() {
        return extentionAudioID;
    }

    public void setExtentionAudioID(int extentionAudioID) {
        this.extentionAudioID = extentionAudioID;
    }
}
