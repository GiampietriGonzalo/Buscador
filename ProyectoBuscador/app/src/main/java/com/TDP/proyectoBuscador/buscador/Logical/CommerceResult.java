package com.TDP.proyectoBuscador.buscador.Logical;

import TDALista.*;

public class CommerceResult implements Result {

    private PositionList<Result> results;


    public boolean isSucessful() {
        return !results.isEmpty();
    }

    public PositionList<Result> getResults() {
        return results;
    }
}
