package com.omaru.storelocator.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Address {
    private String city;
    private String postalCode;
    private String street;
    private String street2;
    private String street3;
    private String addressName;
}
