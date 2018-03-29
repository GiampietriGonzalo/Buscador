package com.TDP.proyectoBuscador.buscador.Users;
import com.TDP.proyectoBuscador.buscador.CommerceAndDesired.Commerce;

public class Admin extends Account {

    public Admin(String name, char type){
        super(name,type);
    }

    public void addCommerce(Commerce c){
        //El admin puede ingresar comercios directamente a la tabla de commerces
    }
    public void addItem(){
        //El admin puede ingresar items directamente a la tabla de items
    }
}
