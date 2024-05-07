package MatteoOrlando.CapStone.services;

import MatteoOrlando.CapStone.entities.Order;
import MatteoOrlando.CapStone.exceptions.NotFoundException;
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
        return orderDAO.findById(id).orElseThrow(() -> new NotFoundException("Order not found with id: " + id));
    }

    public List<Order> getAllOrders() {
        return orderDAO.findAll();
    }

    public void updateOrder(Order order) {
        if (!orderDAO.existsById(order.getId())) {
            throw new NotFoundException("Cannot update order. Order not found with id: " + order.getId());
        }
        orderDAO.save(order);
    }

    public void deleteOrder(Long id) {
        if (!orderDAO.existsById(id)) {
            throw new NotFoundException("Cannot delete order. Order not found with id: " + id);
        }
        orderDAO.deleteById(id);
    }
}

