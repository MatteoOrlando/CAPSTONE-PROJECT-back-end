package MatteoOrlando.CapStone.services;

import MatteoOrlando.CapStone.entities.Order;;
import MatteoOrlando.CapStone.repositories.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderDAO orderDAO;

    public void placeOrder(Order order) {
        orderDAO.save(order);
    }

    public Order getOrderById(Long id) {
        return orderDAO.findById(id).orElse(null);
    }

    public List<Order> getAllOrders() {
        return orderDAO.findAll();
    }

    public boolean existsById(Long id) {
        return orderDAO.existsById(id);
    }
    public void updateOrder(Order order) {
        orderDAO.save(order);
    }

    public void deleteOrder(Long id) {
        orderDAO.deleteById(id);
    }
}
