package MatteoOrlando.CapStone.controller;


import MatteoOrlando.CapStone.entities.Product;
import MatteoOrlando.CapStone.repositories.ProductDAO;
import MatteoOrlando.CapStone.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Product> getProductsById(@PathVariable Long id) {
        Product product = productService.findProductById(id);
        return  ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> createProducts(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProducts(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.saveProduct(product);
        return ResponseEntity.ok(updatedProduct);
    }


    @DeleteMapping("/id")
    public  ResponseEntity<?> deleteProducts(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Prodotto cancellato con successo!");
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable Long categoryId) {
        List<Product> products = productService.findProductsByCategory(categoryId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/platform/{platformId}")
    public ResponseEntity<List<Product>> getProductsByPlatform(@PathVariable Long platformId) {
        List<Product> products = productService.findProductsByPlatform(platformId);
        return ResponseEntity.ok(products);
    }
}
