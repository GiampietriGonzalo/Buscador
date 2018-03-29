package com.TDP.proyectoBuscador.buscador.CommerceAndDesired;

import java.io.Serializable;

/**
 *Calles de una ciudad.
 */

public class Street{

    private String name;
    private int number;
    private boolean parkingMeter;

    public Street(String name, int number, boolean parkingMeter){
        this.name=name;
        this.number=number;
        this.parkingMeter=parkingMeter;
    }

    public String getName(){
        return name;
    }

    public int getNumber() {
        return number;
    }

    public boolean getParkingMeter() {
        return parkingMeter;
    }

    public void setParkingMeter(boolean parkingMeter) {
        this.parkingMeter = parkingMeter;
    }
}
