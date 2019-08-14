package com.omaru.storelocator.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.geo.Point;

import javax.persistence.*;

@Entity
@Table(name = "store_location")
@Getter
@Setter
@EqualsAndHashCode
public class Location {
    @Id
    @SequenceGenerator(name = "store_location_sequence", sequenceName = "store_location_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "store_location_sequence")
    @Column(name="store_location_id")
    private Long id;
    @Column(name="location")
    private final Point location;
    @Column(name="complex_number")
    private Integer complexNumber;
    @Column(name="warning_message")
    private Boolean warningMessage=false;
    @Column(name="collection_point",nullable =false)
    private Boolean collectionPoint = false;
    @Column(name="location_type")
    private String locationType;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="store_id")
    private Store store;
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "location")
    private Address address;
    public Location(Point location){
        this.location=location;
    }
}
