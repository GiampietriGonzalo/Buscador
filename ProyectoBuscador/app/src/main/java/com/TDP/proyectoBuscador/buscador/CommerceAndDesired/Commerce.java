package com.TDP.proyectoBuscador.buscador.CommerceAndDesired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Commerce extends Business{


    /**
     * Initial Compromise 5/10
     * Initial Quality 5/10
     * * */
    public Commerce(String name, int commerceID, ArrayList<String> location, String telephone, String type, String sector){
        super(name,commerceID,location,telephone, sector);
        this.type=type;
    }

}

