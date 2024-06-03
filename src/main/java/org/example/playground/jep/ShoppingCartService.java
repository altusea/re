package org.example.playground.jep;

import java.util.UUID;

import static org.example.playground.jep.FoldLeft.foldLeft;

public interface ShoppingCartService {

    static ShoppingCart getShoppingCart(EventStore eventStore, UUID shoppingCartId, UUID clientId) {
        var events = eventStore.mockEvents(shoppingCartId, clientId);

        return events.stream()
                .collect(foldLeft(ShoppingCart.Initial::new, ShoppingCart.Event::evolve));
    }
}
