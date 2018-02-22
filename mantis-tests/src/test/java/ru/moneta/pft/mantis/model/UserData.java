package ru.moneta.pft.mantis.model;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@XStreamAlias("user")
@Entity
@Table(name = "mantis_user_table")
public class UserData {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "access_level")
    private int access_level;

    @Column(name = "enabled")
    private int enabled;

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public int getAccess_level() {
        return access_level;
    }

    public int getEnabled() {
        return enabled;
    }

    public UserData withId(int id){
        this.id = id;
        return this;
    }

    public UserData withUserName(String userName){
        this.userName = userName;
        return this;
    }
    public UserData withEmail(String email){
        this.email = email;
        return this;
    }
}
