package com.hiep.sample.store.service;

import com.hiep.sample.store.model.Item;
import com.hiep.sample.store.repository.ItemRepository;
import com.hiep.sample.store.service.tools.StaticService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

/**
 * Created by hiep on 8/7/2016.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(StaticService.class)
public class ItemServiceTest {
    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetItemNameUpperCase(){
        // Prepare
        Item item = new Item("1", "Item name 1", "Some description", 12, true);
        when(itemRepository.findById("1")).thenReturn(item);

        // Action
        String result = itemService.getItemNameUpperCase("1");

        // Assertion
        verify(itemRepository, times(1)).findById("1");
        assertThat(result, is(item.getName().toUpperCase()));
    }

    @Test
    public void calculationOfAveragePriceForAllItems(){
        List<Item> mockedItemList = new ArrayList<Item>();
        mockedItemList.add(new Item("it1", "Item 1", "This is item 1", 2000, true));
        when(itemRepository.readAllItems()).thenReturn(mockedItemList);
        mockStatic(StaticService.class);
        when(StaticService.getMultiplicator()).thenReturn(5);

        int averagePriceForAllItems = itemService.getAveragePriceForAllItems();

        verify(itemRepository, times(1)).readAllItems();
        verifyStatic(times(1));
        StaticService.getMultiplicator();
        assertThat(averagePriceForAllItems, is(2000*5));

    }




}