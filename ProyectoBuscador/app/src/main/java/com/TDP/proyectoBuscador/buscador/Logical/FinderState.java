package com.TDP.proyectoBuscador.buscador.Logical;

public abstract class FinderState {

    protected Finder finder;

    public void setFinder(Finder finder) {
        this.finder = finder;
    }

    public void free(){}

    public void occupy(){}

    public Result find(String toFind){
        return null;
    }

}

