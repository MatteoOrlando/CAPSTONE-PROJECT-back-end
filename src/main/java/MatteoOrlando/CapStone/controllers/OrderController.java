package MatteoOrlando.CapStone.controllers;

import MatteoOrlando.CapStone.entities.Order;
import MatteoOrlando.CapStone.exceptions.BadRequestException;
import MatteoOrlando.CapStone.exceptions.NotFoundException;
import MatteoOrlando.CapStone.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        if (order == null) {
            throw new NotFoundException(id);
        }
        return order;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PutMapping("/{id}")
    public void updateOrder(@PathVariable Long id, @RequestBody Order order) {
        if (orderService.existsById(id)) {
            throw new NotFoundException(id);
        }
        try {
            order.setId(id);
            orderService.updateOrder(order);
        } catch (Exception ex) {
            throw new BadRequestException("Invalid order data for update!");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        if (orderService.existsById(id)) {
            throw new NotFoundException(id);
        }
        orderService.deleteOrder(id);
    }

}
