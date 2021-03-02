package at.htl.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    private int userid;
    private boolean issuperuser;
    private String username;
    private String password;
    private String salt;

    public User() {
    }

    public User(int userid, boolean issuperuser, String username, String password, String salt) {
        this.userid = userid;
        this.issuperuser = issuperuser;
        this.username = username;
        this.password = password;
        this.salt = salt;
    }

    //region Getter and Setter
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public boolean isSuperuser() {
        return issuperuser;
    }

    public void setSuperuser(boolean issuperuser) {
        this.issuperuser = issuperuser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    //endregion

}
