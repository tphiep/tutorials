package com.hiep.sample.store.service;

import com.hiep.sample.store.model.Item;
import com.hiep.sample.store.repository.ItemRepository;
import com.hiep.sample.store.service.tools.StaticService;
import org.springframework.beans.factory.annotation.Autowired;

public class RateService {

    @Autowired
    private ItemRepository itemRepository;


    public int calculateRate(String itemId, int muliplicator) {

        Item item = itemRepository.findById(itemId);
        int rate = item.getPriceInCents() * StaticService.getMultiplicator();
        return rate;
    }

}
