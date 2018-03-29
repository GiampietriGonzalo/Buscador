package com.TDP.proyectoBuscador.buscador.Logical;

public class FinderAvailable extends FinderState {

    public void occupy(){
        finder.setState(new FinderBusy());
    }

    public Result find(String toFind) {
        //VER BIEN
        occupy();
        Result toReturn=finder.getAlgorithm().search(toFind);
        finder.getState().free();
        return  toReturn;
    }
}
