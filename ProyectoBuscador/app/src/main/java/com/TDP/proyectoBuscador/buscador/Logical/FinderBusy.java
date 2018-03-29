package com.TDP.proyectoBuscador.buscador.Logical;

public class FinderBusy extends FinderState {


    public void free() {
        finder.setState(new FinderAvailable());
    }


    public Result find(String toFind) {
        //No se puede buscar, el buscador está ocupado
        return null;
    }
}
