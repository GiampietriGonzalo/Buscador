package com.TDP.proyectoBuscador.buscador.Logical;


public class CommerceFinder extends Finder {

    private CommerceFinder(SearchAlgorithm algorithm) {
        super(algorithm);
    }

    public static Finder getFinder(){
        if(finder==null)
            finder= new CommerceFinder(algorithm.getSearchAlgorithm());

        return finder;
    }
}
