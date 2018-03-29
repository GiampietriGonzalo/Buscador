package com.TDP.proyectoBuscador.buscador.Logical;


/*Clase pensada para implementar el algoritmo de búsqueda de los comercios a trabvés de un producto*/

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
