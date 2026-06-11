package org;

import org.entities.Customer;
import org.entities.Order;
import org.entities.Product;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {
        Customer Alexander = new Customer(523523L, "Alexander", 2);
        Customer Martina = new Customer(33423141L, "Martina", 2);
        Customer Paolone = new Customer(346346L, "Paolo", 3);

        List<Customer> customers = List.of(Alexander, Martina, Paolone);

        Product hp1 = new Product(123456L, "Harry Potter and the Philosopher's Stone.", "Books", 59.99);
        Product hp2 = new Product(233455L, "Harry Potter and the Chamber of Secrets.", "Books", 119.99);
        Product hp3 = new Product(252414L, "Harry Potter and the Prisoner of Azkaban.", "Books", 99.99);
        Product bike = new Product(430943L, "Kawasaki Ninja ZX-10R", "Boys", 18.990);
        Product cream = new Product(252352L, "Sol de Janeiro", "Girls", 41.99);
        Product pacifier = new Product(424234L, "Philips Soothie", "Baby", 15.99);
        Product stroller = new Product(665243L, "Stokke YOYO 3", "Baby", 499.99);


        List<Product> products = List.of(hp1, hp2, hp3, bike, cream, pacifier, stroller);


        List<Product> priceOver100 = products.stream().filter(product -> product.getCategory().equals("Books") && product.getPrice() > 100).toList();
        System.out.println("Book price over 100:");
        System.out.println(priceOver100);

        Order order1 = new Order(235235L, "Shipped", LocalDate.of(2021, 2, 15), LocalDate.of(2021, 2, 17), List.of(bike, stroller, hp1), Alexander);
        Order order2 = new Order(325235L, "Pending", LocalDate.now(), LocalDate.now().plusDays(7), List.of(cream, pacifier, hp2), Martina);
        Order order3 = new Order(5235232L, "Out for delivery", LocalDate.now(), LocalDate.now(), List.of(hp3, bike), Paolone);

        List<Order> orders = List.of(order1, order2, order3);

        List<Order> babyCategory = orders.stream().filter(order -> order.getProducts().stream().anyMatch(product -> product.getCategory().equals("Baby"))).toList();
        System.out.println("\nOrders that include 'Baby' category items:");
        System.out.println(babyCategory);

        List<Product> boys10 = products.stream().filter(product -> product.getCategory().equals("Boys")).map(product -> new Product(product.getId(), product.getName(), product.getCategory(), product.getPrice() * 0.9)).toList();
        System.out.println("\n10% off for items in Boys category:");
        System.out.println(boys10);

        List<Product> tier2Products = orders.stream().filter(order -> order.getCustomer().getTier() == 2 && !order.getOrderDate().isBefore(LocalDate.of(2021, 2, 1)) && !order.getOrderDate().isAfter(LocalDate.of(2021, 4, 1))).flatMap(order -> order.getProducts().stream()).toList();

        System.out.println("\nTier 2 Customer Products:");
        System.out.println(tier2Products);

        Map<Customer, List<Order>> ordersByCustomer = orders.stream().collect(Collectors.groupingBy(Order::getCustomer));
        System.out.println("\nOrders grouped by customer:");
        ordersByCustomer.forEach((customer, customerOrder) -> {
            System.out.println(customer);
            System.out.println(customerOrder);
        });
    }
}
