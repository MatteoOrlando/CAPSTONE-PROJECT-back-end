package MatteoOrlando.CapStone.services;

import MatteoOrlando.CapStone.dto.OrderDTO;
import MatteoOrlando.CapStone.entities.Order;
import MatteoOrlando.CapStone.entities.Product;
import MatteoOrlando.CapStone.entities.User;
import MatteoOrlando.CapStone.exceptions.NotFoundException;
import MatteoOrlando.CapStone.repositories.OrderDAO;
import MatteoOrlando.CapStone.repositories.ProductDAO;
import MatteoOrlando.CapStone.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ProductDAO productDAO;

    private OrderDTO convertToDTO(Order order) {
        List<Long> productIds = order.getProducts().stream().map(Product::getId).collect(Collectors.toList());
        return new OrderDTO(order.getId(), order.getUser().getId(), productIds, order.getTotalPrice(), order.getStatus());
    }

    private Order convertToEntity(OrderDTO orderDTO) {
        Order order = new Order();
        User user = userDAO.findById(orderDTO.userId()).orElseThrow(() -> new NotFoundException("User not found with id: " + orderDTO.userId()));
        List<Product> products = orderDTO.productIds().stream()
                .map(productId -> productDAO.findById(productId)
                        .orElseThrow(() -> new NotFoundException("Product not found with id: " + productId)))
                .collect(Collectors.toList());

        order.setId(orderDTO.id());
        order.setUser(user);
        order.setProducts(products);
        order.setTotalPrice(orderDTO.totalPrice());
        order.setStatus(orderDTO.status());

        return order;
    }

    public OrderDTO getOrderById(Long id) {
        Order order = orderDAO.findById(id).orElseThrow(() -> new NotFoundException("Order not found with id: " + id));
        return convertToDTO(order);
    }

    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderDAO.findAll();
        return orders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = convertToEntity(orderDTO);
        Order savedOrder = orderDAO.save(order);
        return convertToDTO(savedOrder);
    }

    public OrderDTO updateOrder(Long id, OrderDTO orderDTO) {
        if (!orderDAO.existsById(id)) {
            throw new NotFoundException("Cannot update order. No order found with id: " + id);
        }
        Order order = convertToEntity(orderDTO);
        order.setId(id);
        Order updatedOrder = orderDAO.save(order);
        return convertToDTO(updatedOrder);
    }

    public void deleteOrder(Long id) {
        if (!orderDAO.existsById(id)) {
            throw new NotFoundException("Cannot delete order. No order found with id: " + id);
        }
        orderDAO.deleteById(id);
    }
}
