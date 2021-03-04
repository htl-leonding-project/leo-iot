package at.htl.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MqttUser {
    @Id
    private int userId;
    private String username;
    private String password;
    private String salt;
    private boolean superUser;

    public MqttUser() {
    }

    public MqttUser(int userId, String username, String password, String salt, boolean superUser) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.superUser = superUser;
    }

    //region Getter and Setter
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public boolean isSuperUser() {
        return superUser;
    }

    public void setSuperUser(boolean superUser) {
        this.superUser = superUser;
    }

    //endregion

}
