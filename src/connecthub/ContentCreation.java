/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

import java.time.LocalDateTime;

/**
 *
 * @author hp
 */
public abstract class ContentCreation {

    private String authorid;
    private String contentid;
    private String content;
    private LocalDateTime timeStamp;
    private String imagepath;

    /*public ContentCreation(int authorid, int contentid, String content, LocalDateTime timeStamp) {
        this.authorid = authorid;
        this.contentid = contentid;
        this.content = content;
        this.timeStamp = timeStamp;
    }*/
    public ContentCreation (){};

    public String getAuthorid() {
        return authorid;
    }

    public void setAuthorid(String authorid) {
        this.authorid = authorid;
    }

    public String getContentid() {
        return contentid;
    }

    public void setContentid(String contentid) {
        this.contentid = contentid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
    public void setimage(String imagepath){
        this.imagepath=imagepath;
        
    }

    public String getImagepath() {
        return imagepath;
    }

}
