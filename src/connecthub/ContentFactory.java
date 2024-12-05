/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

/**
 *
 * @author hp
 */
public class ContentFactory {
    
    
    public ContentCreation createpoststory(String str)
    {
        
        if(str.equals("Post"))
        { return new Post();}
        if(str.equals("story"))
        {// return new Story();}
            
        }
        return null;
            
        
        
    }
    
}
