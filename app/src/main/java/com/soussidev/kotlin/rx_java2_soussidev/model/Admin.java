package com.soussidev.kotlin.rx_java2_soussidev.model;

/**
 * Created by Soussi on 27/08/2017.
 */

public class Admin {

    private  String name;
    private Integer id;
    private String post;
    private String email;
    private Integer tel;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTel() {
        return tel;
    }

    public void setTel(Integer tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "Name='" + name + '\'' +
                ", Post=" + post +'\'' +
                ", Email=" + email +'\'' +
                ", Phone=" + tel +'\'' +
                '}';
    }
}
