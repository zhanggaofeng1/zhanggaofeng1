/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsc.tbm.enums;

/**
 *
 * @author Administrator
 */
public enum TypeEnums {

    SUPPROT_INT("int"),
    SUPPROT_UP_STRING("String"),
    SUPPROT_STRING("string"),
    SUPPROT_UP_FLOAT("Float"),
    SUPPROT_FLOAT("float"),
    SUPPROT_UP_DOUBLE("Double"),
    SUPPROT_DOUBLE("double"),
    ;
    private String type;
    TypeEnums(String type) {
        this.type = type;
    }
    
    public String type() {
        return type;
    }
}
