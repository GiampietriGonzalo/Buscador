package com.TDP.proyectoBuscador.buscador.Logical;


public class CommerceAlgorithm extends SearchAlgorithm {


    private CommerceAlgorithm(){}

    public Result search(String toFind){

        //Implementar lo BD

        return null;
    }

    public static SearchAlgorithm getSearchAlgorithm(){

        if(searchAlgorithm==null)
            searchAlgorithm=new CommerceAlgorithm();

        return searchAlgorithm;

    }


}
