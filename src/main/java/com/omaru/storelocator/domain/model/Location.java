package com.omaru.storelocator.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
@Getter
@Setter
@EqualsAndHashCode
public class Location {
    @GeoSpatialIndexed
    private final Point location;
    private Integer complexNumber;
    private Boolean warningMessage=false;
    private Boolean collectionPoint = false;
    private String locationType;
    private Address address;
    public Location(Point location){
        this.location=location;
    }
}
