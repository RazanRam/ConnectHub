/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author hp
 */
public class Story extends ContentCreation {

    ReadWriteStory story = new ReadWriteStory();
    ArrayList<Story> st = new ArrayList();
    ArrayList<Story> updatedstories = new ArrayList();

    public void addStory(Story s) {
        st.add(s);
        story.WriteStoryInFile(st);
        
    }

    public void deleteStory(Story s) {        
        for (Story S : st) {
            Duration duration = Duration.between(S.getTimeStamp(), LocalDateTime.now());
            long hrs = duration.toHours();
            if (hrs < 24) {
                updatedstories.add(S);
            }
        } story.WriteStoryInFile(updatedstories);
    }
    
    public ArrayList<Story> getstories() {
        return st;
        
    }
    
}
