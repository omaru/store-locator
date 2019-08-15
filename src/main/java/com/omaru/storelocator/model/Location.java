package com.omaru.storelocator.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@EqualsAndHashCode
public class Location {
    @Id
    private String id;
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
