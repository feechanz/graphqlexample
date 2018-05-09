package com.tryme.projectk.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Account")
@Getter
@Setter
public class Account implements Serializable {
    private static final long serialVersionUID = -3009157732242241606L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonProperty("Id")
    private Long id;

    @Column(name = "username")
    @JsonProperty("Username")
    private String username;

    @Column(name = "password")
    @JsonProperty("Password")
    private String password;


    @Column(name = "fullname")
    @JsonProperty("Fullname")
    private String fullname;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "account", cascade = CascadeType.ALL)
    @JsonProperty("Notes")
    private Set<Note> notes;


    @Override
    public String toString() {
        return String.format("Account[id=%d, username='%s', fullname='%s']", id, username, fullname);
    }
}
