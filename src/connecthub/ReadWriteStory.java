/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author hp
 */
public class ReadWriteStory {
    
    private ArrayList<Story> Story= new ArrayList();
    public void WriteStoryInFile(ArrayList<Story> stories){
         JSONArray arrstories = new JSONArray();
         for (Story st : stories) {
            JSONObject jp = new JSONObject();
            jp.put("contentid",st.getContentid());
            jp.put("authorID", st.getAuthorid());
            jp.put("content", st.getContent());
            jp.put("timeStamp", st.getTimeStamp());
            jp.put("imagePath", st.getImagepath());
            arrstories.put(jp);
        }
        FileWriter file;
        try {
            file = new FileWriter("stories.json");
            file.write(arrstories.toString(4));
            file.close();
        } catch (IOException ex) {
            Logger.getLogger(ReadWrite.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
}
    public ArrayList<Story>loadStories() {
        String S, content = new String();
        FileReader fr;
        try {
            fr = new FileReader("stories.json");
            BufferedReader br = new BufferedReader(fr);
            while ((S = br.readLine()) != null) {
                content += S;
            }
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadWrite.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReadWrite.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSONArray arr = new JSONArray(content);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        for (int i = 0; i < arr.length(); i++) {
            JSONObject St = arr.getJSONObject(i);
            String contentid = St.getString("contentid");
            String name = St.getString("name");
            int id = St.getInt("id");
            String txtpost = St.getString("storycontent");
            String timeStamp = St.getString("timeStamp");
            String imagePath = St.getString("imagePath");
            ContentFactory F = new ContentFactory();
            Story s = (Story) F.createpoststory("story");
            s.setContentid(contentid);
            s.setAuthorid(String.valueOf(id));
            s.setContent(txtpost);
            LocalDateTime.parse(timeStamp, formatter);

            Story.add(s);

        }
        return Story;

    }
    
}
