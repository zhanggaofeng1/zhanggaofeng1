/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.player.vo;

/**
 *
 * @author Administrator
 */
public class Users {
    private String id;
    private int age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    @Override
    public String toString() {
        return "Users[id=" + id + ",age=" + age + "]";
    }
}
