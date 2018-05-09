package com.tryme.projectk.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Note")
@Getter
@Setter
public class Note implements Serializable{
    private static final long serialVersionUID = -3009157732242241606L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonProperty("Id")
    private Long id;

    @Column(name = "title")
    @JsonProperty("Title")
    private String title;

    @Column(name = "note")
    @JsonProperty("Note")
    private String note;

    @JoinColumn(name = "accountid")
    @JsonProperty("Accountid")
    private Long accountid;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "accountid", insertable=false, updatable=false)
    @JsonProperty("Account")
    private Account account;

}
