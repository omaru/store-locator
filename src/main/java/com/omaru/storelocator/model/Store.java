package com.omaru.storelocator.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.security.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "store")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Store {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name="store_id", updatable = false, nullable = false)
    private UUID uuid;
    @Column(name="sap_store_id",  nullable = false)
    private Long sapStoreID;
    @Column(name="today_open", nullable = false)
    private Timestamp todayOpen;
    @Column(name="today_close", nullable = false)
    private Timestamp todayClose;
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "store")
    private Location location;

}
