package ru.moneta.pft.mantis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;


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

    @Column(name = "access_level", columnDefinition = "SMALLINT")

    private int access_level;

    @Column(name = "enabled", columnDefinition = "TINYINT")
    private int enabled;


    public int getId() {
        return id;
    }

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
    public UserData withAccessLevel(int level){
        this.access_level=level;
        return this;
    }
    public UserData withEnabledStatus(int status){
        this.enabled=status;
        return this;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", access_level=" + access_level +
                ", enabled=" + enabled +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return id == userData.id &&
                access_level == userData.access_level &&
                enabled == userData.enabled &&
                Objects.equals(userName, userData.userName) &&
                Objects.equals(email, userData.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userName, email, access_level, enabled);
    }

}
