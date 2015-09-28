package me.j360.boot.microservice.order.validator;

import me.j360.boot.microservice.order.entity.Express;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created with j360-microservice -> me.j360.boot.microservice.order.validator.
 * User: min_xu
 * Date: 2015/9/28
 * Time: 14:55
 * 说明：
 */

@Component
public class ExpressValidator implements Validator {
    private final InventoryService inventoryService;

    @Autowired
    public ExpressValidator(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }
    @Override
    public boolean supports(Class<?>clazz) {
        return Express.class.isAssignableFrom(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) {
        Express detail = (Express)target;
        if (!inventoryService.isValidInventory(detail.getId())) {
            errors.rejectValue("inventoryId", "inventory.id.invalid", "Inventory ID is invalid");
        }
    }
}
