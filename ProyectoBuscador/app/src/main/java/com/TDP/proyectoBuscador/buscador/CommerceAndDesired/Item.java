package com.TDP.proyectoBuscador.buscador.CommerceAndDesired;


import java.util.LinkedList;
import java.util.List;

/*Clase abstracta que generaliza a los productos y servicios*/

public abstract class Item {

    protected String name;
    protected int searches;
    protected List<String> business;
    protected List<String> tags;
    protected int ID;

    public Item(String name){
        this.name=name;
        searches=0;
        business= new LinkedList<String>();
        tags= new LinkedList<String>();
        ID=0;
    }

    public String getName() {
        return name;
    }

    public int getSearches() {
        return searches;
    }

    public List<String> getBusiness() {
        return business;
    }

    public List<String> getTags() {
        return tags;
    }

    public void addTag(String newTag){
        tags.add(newTag);
    }

    public void setCommerces(List<String> business) {
        this.business = business;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setSearches(int searches) {
        this.searches = searches + 1;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

}

