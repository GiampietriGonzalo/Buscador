package com.TDP.proyectoBuscador.buscador.Logical;

import TDALista.*;

/*Clase que almacenaria tamporalmente los resultados de una busqueda*/

public class CommerceResult implements Result {

    private PositionList<Result> results;


    public boolean isSucessful() {
        return !results.isEmpty();
    }

    public PositionList<Result> getResults() {
        return results;
    }
}
