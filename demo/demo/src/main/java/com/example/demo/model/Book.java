package com.example.demo.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="book")
public class Book {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;

     @Column(name="name")
    private String name;
    @Column(name="imageurl")
    private String imageUrl;

    public Book(){
        
    }
    public Book(int id, String name , String imageUrl){
        this.id=id;
        this.name=name;
        this.imageUrl=imageUrl;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }

    public String getImageUrl(){
        return imageUrl;
    }
    public void setId(int id){
        this.id=id;
    }
    public void setName(String name){
        this.name =name;
    }
    public void setImageUrl(String imageUrl){
        this.imageUrl =imageUrl;
    }
}