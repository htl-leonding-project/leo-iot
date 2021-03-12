package at.htl.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Acl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long allow;
    private String ipAddr;
    private String userName;
    private String clientId;
    private int access;
    private String topic;

    public Acl() {
    }

    public Acl(Long allow, String ipAddr, String userName, String clientId, int access, String topic) {
        this.allow = allow;
        this.ipAddr = ipAddr;
        this.userName = userName;
        this.clientId = clientId;
        this.access = access;
        this.topic = topic;
    }

    //region Getter and Setter

    public Long getId() {
        return id;
    }

    public void setId(Long aclId) {
        this.id = aclId;
    }

    public Long getAllow() {
        return allow;
    }

    public void setAllow(Long allow) {
        this.allow = allow;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    //endregion

    @Override
    public String toString() {
        return "Acl{" +
                "id=" + id +
                ", allow=" + allow +
                ", ipAddr='" + ipAddr + '\'' +
                ", userName='" + userName + '\'' +
                ", clientId='" + clientId + '\'' +
                ", access=" + access +
                ", topic='" + topic + '\'' +
                '}';
    }
}
