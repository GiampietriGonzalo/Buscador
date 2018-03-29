package com.TDP.proyectoBuscador.buscador.CommerceAndDesired;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

public abstract class Business {

    protected String name;
    protected int commerceID;
    protected ArrayList<String> location;
    protected String telephone;
    protected Map<String,Double> desiredPrice;
    protected Double reputation;
    protected int votes;
    protected Map<String,Double> desiredQuality;
    protected String type;
    protected String sector;

    public Business(String name, int commerceID, ArrayList<String> location, String telephone, String sector){
        this.name=name;
        this.commerceID=commerceID;
        this.telephone=telephone;
        this.location=location;
        desiredPrice= new HashMap<String,Double>();
        desiredQuality= new HashMap<String, Double>();
        reputation=5d;
        votes=0;
        type="";
        this.sector=sector;
    }

    public int getCommerceID() {
        return commerceID;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getLocation() {
        return location;
    }

    public Map<String,Double> getDesiredPrice() {
        return desiredPrice;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setLocation(ArrayList<String> location){
        this.location=location;
    }

    public void setDesiredPrice(Map<String,Double> desiredPrice) {
        this.desiredPrice = desiredPrice;
    }

    public void addDesiredPrice(String desiredName,Double price){
        this.desiredPrice.put(desiredName,price);
    }

    public Double getReputation() {
        return reputation;
    }

    public void addReputation(Double rep){
        reputation=reputation*votes+rep;
        votes++;
        reputation/=votes;
    }

    public void setReputation(Double reputation) {
        this.reputation = reputation;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public int getVotes() {
        return votes;
    }

    public Map<String,Double> getDesiredQuality() {
        return desiredQuality;
    }

    public String getType() {
        return type;
    }

    public void setDesiredQuality(Map<String,Double> desiredQuality) {
        this.desiredQuality = desiredQuality;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getSector() {
        return sector;
    }

    public void addDesiredQuality(String desired, Double quality){
        desiredQuality.put(desired,quality);
    }
}
