package com.greenlabs.day.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private String name;
    private String displayName;
    private String birth;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Entry> entryList = new ArrayList<>();

    public void addEntry(Entry entry) {
        entryList.add(entry);
        entry.setUser(this);
    }

    public List<Entry> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<Entry> entryList) {
        this.entryList = entryList;
    }


    public User() {
    }

    public User(String email, String password, String displayName) {
        this.email = email;
        this.password = password;
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email=" + email +
                ", displayName=" + displayName +
                "}";
    }
}
