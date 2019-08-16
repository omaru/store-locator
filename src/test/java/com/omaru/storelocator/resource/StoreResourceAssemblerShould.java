package com.omaru.storelocator.resource;

import com.omaru.storelocator.domain.model.Store;
import org.junit.jupiter.api.Test;

import static com.omaru.storelocator.util.MockUtil.getStores;
import static org.assertj.core.api.Assertions.assertThat;

public class StoreResourceAssemblerShould {
    private final StoreResourceAssembler storeResourceAssembler = new StoreResourceAssembler();

    @Test
    void retrieveStoreResourceFromStore() {
        Store store = getStores().iterator().next();
        StoreResource storeResource = storeResourceAssembler.toResource(store);
        assertThat(storeResource).isNotNull();
    }
    @Test
    void containNavigationLinkToSelf() {
        Department department = getEmployees().iterator().next().getDepartment();
        DepartmentResource departmentResource = departmentResourceAssembler.toResource(department);
        assertThat(departmentResource.getLink("self").getHref()).contains("/department/" + department.getId());

    }
}