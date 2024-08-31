package com.techstockmaster.util;

import com.techstockmaster.model.entities.User;

/**
 * Esta classe armazena o Nome do Usuário que fez o login no sistema.
 */
public class Session {
    private static User userA;

    public static void setUser(User user){
        Session.userA = user;
    }

    public static User getUser(){
        return userA;
    }
}