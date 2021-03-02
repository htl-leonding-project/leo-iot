package at.htl.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Acl {

    @Id
    private int aclId;
    private int allow;
    private String ipAddr;
    private String userName;
    private String clientId;
    private int access;
    private String topic;

    public Acl() {
    }

    public Acl(int aclId, int allow, String ipAddr, String userName, String clientId, int access, String topic) {
        this.aclId = aclId;
        this.allow = allow;
        this.ipAddr = ipAddr;
        this.userName = userName;
        this.clientId = clientId;
        this.access = access;
        this.topic = topic;
    }

    //region Getter and Setter
    public int getAclId() {
        return aclId;
    }

    public void setAclId(int aclId) {
        this.aclId = aclId;
    }

    public int getAllow() {
        return allow;
    }

    public void setAllow(int allow) {
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
}
