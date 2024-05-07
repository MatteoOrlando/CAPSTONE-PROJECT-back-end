package MatteoOrlando.CapStone.controllers;

import MatteoOrlando.CapStone.entities.Product;
import MatteoOrlando.CapStone.services.ProductService;
import MatteoOrlando.CapStone.exceptions.NotFoundException;
import MatteoOrlando.CapStone.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            throw new NotFoundException("Product not found with id: " + id);
        }
        return product;
    }

    @GetMapping("/category/{categoryId}")
    public List<Product> getByCategoryId(@PathVariable Long categoryId) {
        List<Product> products = productService.getByCategoryId(categoryId);
        if (products.isEmpty()) {
            throw new NotFoundException("No products found for category ID: " + categoryId);
        }
        return products;
    }

    @GetMapping("/name/{name}")
    public List<Product> getProductsByName(@PathVariable String name) {
        List<Product> products = productService.getProductsByName(name);
        if (products.isEmpty()) {
            throw new NotFoundException("No products found with name: " + name);
        }
        return products;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        if (product == null || product.getName() == null || product.getPrice() == null) {
            throw new BadRequestException("Invalid product data provided.");
        }
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        if (productService.existsById(id)) {
            throw new NotFoundException("Cannot update product. No product found with id: " + id);
        }
        product.setId(id);
        return productService.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        if (productService.existsById(id)) {
            throw new NotFoundException("Cannot delete product. No product found with id: " + id);
        }
        productService.deleteProduct(id);
    }
}
