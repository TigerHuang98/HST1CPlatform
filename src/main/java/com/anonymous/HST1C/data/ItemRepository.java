package com.anonymous.HST1C.data;

import com.anonymous.HST1C.Item;

import java.util.List;

public interface ItemRepository {
    int addItem(Item item);
    Item findItemById(int itemid);
    List<Item> findItemsByUsername(String username);
}
