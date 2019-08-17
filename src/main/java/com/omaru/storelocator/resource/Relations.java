package com.omaru.storelocator.resource;

public enum Relations {
    LOCATION("near-location");
    private final String relation;
    Relations(String relation){
        this.relation = relation;
    }
    public String getRelation(){
        return relation;
    }
}
