package com.omaru.storelocator.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.security.Timestamp;

@Entity
@Table(name = "store",indexes = @Index(columnList = "uuid"))
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Store {
    @Id
    @SequenceGenerator(name = "store_sequence", sequenceName = "store_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "store_sequence")
    @Column(name="store_id", updatable = false, nullable = false)
    private Long id;
    @Column(name="uuid",nullable = false)
    private String uuid;
    @Column(name="sap_store_id",  nullable = false)
    private Long sapStoreID;
    @Column(name="today_open", nullable = false)
    private Timestamp todayOpen;
    @Column(name="today_close", nullable = false)
    private Timestamp todayClose;
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "store")
    private Location location;

}
