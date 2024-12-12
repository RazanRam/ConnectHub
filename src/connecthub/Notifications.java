/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

import java.time.Instant;

/**
 *
 * @author malok
 */
public class Notifications {
    private String type;
    private String sender;
    private String description;
    private boolean read;
    private long timestamp;
    
    public Notifications(String type, String description) {
        this.type = type;
        this.sender = sender;
        this.description = description;
        this.timestamp=Instant.now().toEpochMilli();
        this.read = false;
    }

    public String getType() {
        return type;
    }

    public String getSender() {
        return sender;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getDescription() {
        return description;
    }

    public boolean isRead() {
        return read;
    }

    public void Read() {
        this.read = true;
    }
}
