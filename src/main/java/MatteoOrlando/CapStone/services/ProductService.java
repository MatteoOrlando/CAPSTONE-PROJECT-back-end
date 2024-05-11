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
        return productDAO.findById(id).orElseThrow(() -> new NotFoundException("Product not found with id: " + id));
    }

    public List<Product> getProductsByName(String name) {
        List<Product> products = productDAO.findByNameContainingIgnoreCase(name);
        if (products.isEmpty()) {
            throw new NotFoundException("No products found with name: " + name);
        }
        return products;
    }

    public List<Product> getByCategoryId(Long categoryId) {
        return productDAO.findByCategoryId(categoryId);
    }

    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }

    public Product createProduct(Product product) {
        return productDAO.save(product);
    }

    public Product updateProduct(Product product) {
        if (!productDAO.existsById(product.getId())) {
            throw new NotFoundException("Cannot update product. No product found with id: " + product.getId());
        }
        return productDAO.save(product);
    }

    public void deleteProduct(Long id) {
        if (!productDAO.existsById(id)) {
            throw new NotFoundException("Cannot delete product. No product found with id: " + id);
        }
        productDAO.deleteById(id);
    }

    public boolean existsById(Long id) {
        return !productDAO.existsById(id);
    }
}

