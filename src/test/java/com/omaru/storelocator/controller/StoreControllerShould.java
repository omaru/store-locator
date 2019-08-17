package com.omaru.storelocator.controller;

import com.omaru.storelocator.domain.model.Store;
import com.omaru.storelocator.domain.service.StoreService;
import com.omaru.storelocator.resource.StoreResource;
import com.omaru.storelocator.resource.StoreResourceAssembler;
import com.omaru.storelocator.util.cmd.CommandLineDataIngester;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

import static com.omaru.storelocator.util.MockUtil.getStoreResources;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(SpringExtension.class)
@WebMvcTest(StoreController.class)
class StoreControllerShould {
    @Inject
    private MockMvc mockMvc;
    @MockBean
    private StoreService storeService;
    @MockBean
    private StoreResourceAssembler storeResourceAssembler;
    @MockBean
    private CommandLineDataIngester commandLineDataIngester;
    @Test
    void beAbleToRetrieveStoreById() throws Exception {
        StoreResource storeResource = getStoreResources().get(0);
        given(storeService.get(anyString())).willReturn(Optional.of(new Store()));
        given(storeResourceAssembler.toResource(any())).willReturn(storeResource);
        mockMvc.perform(get("/store/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("uuid",
                is(storeResource.getUuid())));
    }
    @Test
    void return404IfNoStoreIsFound() throws Exception {
        mockMvc.perform(get("/store/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    @Test
    void beAbleToRetrieveAvailableStores() throws Exception {
        List<StoreResource> storeResources = getStoreResources();
        given(storeResourceAssembler.toResources(any())).willReturn(storeResources);
        mockMvc.perform(get("/store/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$[0].uuid",
                is(storeResources.get(0).getUuid())));
    }
    @Test
    void beAbleToRetrieveAvailableStoresByGivenPoint() throws Exception {
        List<StoreResource> storeResources = getStoreResources();
        given(storeResourceAssembler.toResources(any())).willReturn(storeResources);
        mockMvc.perform(get("/store/")
                .param("latitude","3").param("longitude","2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$[0].uuid",
                is(storeResources.get(0).getUuid())));
    }
}
