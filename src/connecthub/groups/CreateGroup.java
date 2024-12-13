/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub.groups;

import java.util.UUID;

/**
 *
 * @author hp
 */
public class CreateGroup {

    private String groupname;
    private String groupdescription;
    private String groupimage;
    private String groupprimaryadmin;
    private String groupid;

    public void setgroupname(String name) {
        this.groupname = name;
    }

    public String getgroupname() {
        return groupname;

    }

    public void setgroupdescription(String description) {
        this.groupdescription = description;
    }

    public String getgroupdescription() {
        return groupdescription;
    }

    public void setgroupimage(String imagepath) {
        this.groupimage = imagepath;
    }

    public String getgroupimage() {
        return groupimage;
    }

    public String getGroupprimaryadmin() {
        return groupprimaryadmin;
    }

    public void setGroupprimaryadmin(String groupprimaryadmin) {
        this.groupprimaryadmin = groupprimaryadmin;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid() {
        this.groupid = UUID.randomUUID().toString();
    }

}
