package com.company.ecommerce.eventlisteners;

import com.company.ecommerce.entity.Cart;
import com.company.ecommerce.entity.User;
import io.jmix.core.DataManager;
import io.jmix.core.Id;
import io.jmix.core.event.EntityChangedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class UserDeleteListener {
    @Autowired
    private DataManager dataManager;

    @EventListener
    public void onUserBeforeDelete(EntityChangedEvent<User> event) {
        if(event.getType() == EntityChangedEvent.Type.DELETED) {
            Id<User> userId = event.getEntityId();
            UUID id = (UUID) userId.getValue();
            List<Cart> carts = dataManager.load(Cart.class)
                    .query("SELECT c FROM Cart c WHERE c.customer.id = :user")
                    .parameter("user", id)
                    .list();

            carts.forEach(cart -> {
                cart.setCustomerName(dataManager.load(User.class)
                        .query("select u from User u where u.id = :id")
                        .parameter("id", id).one()
                        .getUsername() + " (Deleted)");
                cart.setCustomer(null);
            });

            dataManager.saveAll(carts);
        }
    }
}