package MatteoOrlando.CapStone.services;

import MatteoOrlando.CapStone.entities.Order;
import MatteoOrlando.CapStone.repositories.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderDAO orderDAO;

    public void placeOrder(Order order) {
        orderDAO.saveOrder(order);
    }

    public Order getOrderById(Long id) {
        return orderDAO.getOrderById(id);
    }

    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }


    public void updateOrder(Order order) {
        orderDAO.updateOrder(order);
    }

    public void deleteOrder(Long id) {
        orderDAO.deleteOrder(id);
    }
}
