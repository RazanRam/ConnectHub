/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

import java.io.BufferedReader;
import java.io.File;
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
        ArrayList<Story>oldstories=loadStories();
        oldstories.addAll(stories);
        
         JSONArray arrstories = new JSONArray();
         for (Story st : oldstories) {
            JSONObject jp = new JSONObject();
            jp.put("contentid",st.getContentid());
            jp.put("authorID", st.getAuthorid());
            jp.put("content", st.getContent());
            jp.put("timeStamp", st.getTimeStamp());
            String imagePath=st.getImagepath();
            if(imagePath!=null){ jp.put("imagePath",imagePath);
            }else{jp.put("imagePath", "null");}
            arrstories.put(jp);
        }
        FileWriter writer;
        try {File file=new File("stories.json");
        if(!file.exists()){
        file.createNewFile();}
        
            writer = new FileWriter(file);
            writer.write(arrstories.toString(4));
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(ReadWrite.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
}
    public ArrayList<Story>loadStories() {
        String content = "";
        File file = new File("stories.json");
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                content += line;
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadWrite.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReadWrite.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSONArray arr = new JSONArray(content);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        for (int i = 0; i < arr.length(); i++) {
            JSONObject St = arr.getJSONObject(i);
            String contentid = St.getString("contentid");
           // String authorID = St.getString("authorID");
            String txtpost = St.getString("content");              
            String timeStamp = St.getString("timeStamp");
            String imagePath = St.getString("imagePath");
            ContentFactory F = new ContentFactory();
            Story s = (Story) F.createpoststory("story");
            s.setContentid(contentid);
            //s.setAuthorid(authorID);
            s.setContent(txtpost);
            s.setTimeStamp(LocalDateTime.parse(timeStamp, formatter));
            s.setimage(imagePath);
            

            Story.add(s);

        }
        return Story;

    }
    
}
