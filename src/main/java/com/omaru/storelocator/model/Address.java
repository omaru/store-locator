package com.omaru.storelocator.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Address {
    @Id
    @SequenceGenerator(name = "address_sequence", sequenceName = "address_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_sequence")
    @Column(name="address_id")
    private Long id;
    @Column(name="city")
    private String city;
    @Column(name="postal_code")
    private String postalCode;
    @Column(name="street")
    private String street;
    @Column(name="street_2")
    private String street2;
    @Column(name="street_3")
    private String street3;
    @Column(name="address_name")
    private String addressName;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="store_location_id")
    private Location location;
}
