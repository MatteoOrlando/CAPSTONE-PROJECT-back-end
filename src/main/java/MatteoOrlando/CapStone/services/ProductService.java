package MatteoOrlando.CapStone.services;

import MatteoOrlando.CapStone.entities.Product;
import MatteoOrlando.CapStone.exceptions.NotFoundException;

import MatteoOrlando.CapStone.repositories.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductDAO productDAO;

    public Product getProductById(Long id) {
        return productDAO.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found with id: " + id));
    }

    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }

    public Product saveProduct(Product product) {
        return productDAO.save(product);
    }

    public Product updateProduct(Product product) {
        if (!productDAO.existsById(product.getId())) {
            throw new NotFoundException("Product not found with id: " + product.getId());
        }
        productDAO.save(product);
        return product;
    }

    public void deleteProduct(Long id) {
        if (!productDAO.existsById(id)) {
            throw new NotFoundException("Product not found with id: " + id);
        }
        productDAO.deleteById(id);
    }
}

