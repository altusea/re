package org.example.playground;

import java.util.List;

import static org.example.playground.CommonTest.printSeparateLine;
import static org.example.playground.ItemPrice.itemPriceFromWeb;

sealed interface ItemPrice {

    static ItemPrice itemPriceFromWeb() {
        return switch ((int) (Math.random() * 3)) { // random number between 0 and 2
            case 0 -> new Sale(100.0);
            case 1 -> new Trade(List.of("Phone", "Laptop"));
            default -> new ContactMe();
        };
    }
}

record Sale(double price) implements ItemPrice {
}

record Trade(List<String> tradeOptions) implements ItemPrice {
}

record ContactMe() implements ItemPrice {
}

class ItemPriceTest {

    public static void main(String[] args) {
        ItemPrice sale1 = new Sale(100.0);
        System.out.println(sale1);
        ItemPrice trade1 = new Trade(List.of("Phone", "Laptop"));
        System.out.println(trade1);
        ItemPrice contactMe1 = new ContactMe();
        System.out.println(contactMe1);

        printSeparateLine();
        switch (itemPriceFromWeb()) {
            case Sale sale -> System.out.println("Sale price: " + sale.price());
            case Trade trade -> System.out.println("Trade options: " + trade.tradeOptions());
            case ContactMe ignored -> System.out.println("Contact me for price");
        }
    }
}
