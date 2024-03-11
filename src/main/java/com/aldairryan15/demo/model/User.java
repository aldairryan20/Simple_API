package com.aldairryan15.demo.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = User.TABLE_NAME)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Integer Id;

    public static final String TABLE_NAME = "user";
    @Column(name = "username", length = 30, nullable = false, updatable = false)
    private String username;


    @JsonProperty(access = Access.WRITE_ONLY)
    @Column(name = "password", length = 6, nullable = false, updatable = true)
    private String password;
    
    @OneToMany(mappedBy = "user")
    private List<Task> tasks = new ArrayList<Task>();

    public List<Task> getTasks() {
        return this.tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }


    public User(Integer id, String username, String password) {
        this.Id = id;
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public Integer getId() {
        return Id;
    }

    public static String getTableName() {
        return TABLE_NAME;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }
    
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if (!(obj instanceof User)) return false;
        
        User user = (User) obj;
        if(Id == null || user.Id == null){
            try{
                if(Id == null && user.Id != null) return false;
                if(Id != null && user.Id == null) return false;
            }catch(Exception e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        if(Id == null && user.Id != null) return false;
        return Objects.equals(Id, user.Id);
    }
}
