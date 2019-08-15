package com.omaru.storelocator.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;

@Document
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Store {
    @Id
    private String id;
    private String uuid;
    private Long sapStoreID;
    private LocalTime todayOpen;
    private LocalTime todayClose;
    private Location location;
    public Store(String uuid){
        setUuid(uuid);
    }

}
