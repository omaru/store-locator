package com.omaru.storelocator.resource;

import com.omaru.storelocator.domain.model.Store;
import org.junit.jupiter.api.Test;

import static com.omaru.storelocator.util.MockUtil.getStores;
import static org.assertj.core.api.Assertions.assertThat;

class StoreResourceAssemblerShould {
    private final StoreResourceAssembler storeResourceAssembler = new StoreResourceAssembler();

    @Test
    void retrieveStoreResourceFromStore() {
        Store store = getStores().iterator().next();
        StoreResource storeResource = storeResourceAssembler.toResource(store);
        assertThat(storeResource).isNotNull();
    }
    @Test
    void containNavigationLinkToSelf() {
        Store store = getStores().iterator().next();
        store.setId("123");
        StoreResource storeResource = storeResourceAssembler.toResource(store);
        assertThat(storeResource.getLink("self").getHref()).contains("/store/" + store.getId());
    }
    @Test
    void containNavigationLinkToLocation() {
        Store store = getStores().iterator().next();
        store.setId("123");
        StoreResource storeResource = storeResourceAssembler.toResource(store);
        assertThat(storeResource.getLink(Relations.LOCATION.getRelation())).isNotNull();
    }
}