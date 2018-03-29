package com.TDP.proyectoBuscador.buscador.Users;
import com.TDP.proyectoBuscador.buscador.CommerceAndDesired.Commerce;

public class User extends Account {

    public User(String name, char type){
        super(name,type);
    }


    public void addItem(){

        //El usuario agrega un producto/servicio(Item) a la tabla auxiliar de productos/servicios. Luego de x votos, el Item pasa a la tabla de Items.

    }

    public void addCommerce(Commerce c) {
        //El usuario agrega un comercio a la tabla auxiliar de comercios. Luego de x votos, el comercio pasa a la tabla de comercios.

    }
}
