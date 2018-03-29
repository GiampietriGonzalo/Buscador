package com.TDP.proyectoBuscador.buscador.Logical;

public abstract class Search {

    protected int searchID;
    protected Result result;
    protected Finder finder;

    public Search(int searchID, Finder finder){
        this.searchID=searchID;
        result=new CommerceResult();
        this.finder=finder;
    }

    public int getSearchID() {

        return searchID;
    }

    public Result getResult() {

        return result;
    }

    public void setSearchID(int searchID) {

        this.searchID = searchID;
    }
}
