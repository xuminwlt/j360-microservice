package me.j360.boot.microservice.order.validator;

import org.springframework.stereotype.Component;

/**
 * Created with j360-microservice -> me.j360.boot.microservice.order.validator.
 * User: min_xu
 * Date: 2015/9/28
 * Time: 14:57
 * 说明：
 */

@Component
public class InventoryService {

    public boolean isValidInventory(Long l){
        return true;
    }

}
