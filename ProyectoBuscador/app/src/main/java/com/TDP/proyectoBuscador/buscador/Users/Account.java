package com.TDP.proyectoBuscador.buscador.Users;
import com.TDP.proyectoBuscador.buscador.CommerceAndDesired.Commerce;

public abstract class Account {

    protected String name;
    protected String password;
    protected char type;

    public Account(String name, char type){
        this.name=name;
        this.type=type;
    }

    public String getName() {
        return name;
    }

    public char getType() {
        return type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void addCommerce(Commerce c);

    public abstract void addItem();


}
