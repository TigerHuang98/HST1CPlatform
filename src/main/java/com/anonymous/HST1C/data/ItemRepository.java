package com.anonymous.HST1C.data;

import com.anonymous.HST1C.Item;

public interface ItemRepository {
    Item addItem(Item item);
    Item findItemById(int itemid);
    Item[] findItemByUsername(String username);
}
