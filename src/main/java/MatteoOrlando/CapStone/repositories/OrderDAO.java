package MatteoOrlando.CapStone.repositories;

import MatteoOrlando.CapStone.entities.Order;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

public class OrderDAO {

    @Autowired
    private EntityManager entityManager;

    public void saveOrder(Order order) {
        entityManager.persist(order);
    }

    public Order getOrderById(Long id) {
        return entityManager.find(Order.class, id);
    }

    public List<Order> getAllOrders() {
        return entityManager.createQuery("SELECT o FROM Order o", Order.class).getResultList();
    }

    public void updateOrder(Order order) {
        entityManager.merge(order);
    }

    public void deleteOrder(Long id) {
        Order order = entityManager.find(Order.class, id);
        if (order != null) {
            entityManager.remove(order);
        }
    }
}
