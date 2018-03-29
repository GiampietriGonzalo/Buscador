package com.TDP.proyectoBuscador.buscador.Logical;

public abstract class Finder {

    protected static SearchAlgorithm algorithm;
    protected FinderState state;
    protected static Finder finder;

    protected Finder(SearchAlgorithm algorithm) {
        this.algorithm=algorithm;
        state=new FinderAvailable();
    }

    public FinderState getState() {
        return state;
    }

    public void setState(FinderState state) {
        this.state = state;
        state.setFinder(this);
    }

    public Result find(String toFind){
        return state.find(toFind);
    }

    public static Finder getFinder(){

        return null;
    }

    public  SearchAlgorithm getAlgorithm() {
        return algorithm;
    }

}
