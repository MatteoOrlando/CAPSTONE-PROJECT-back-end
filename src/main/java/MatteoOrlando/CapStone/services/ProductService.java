package MatteoOrlando.CapStone.services;

import MatteoOrlando.CapStone.entities.Product;
import MatteoOrlando.CapStone.repositories.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDAO productRepository;


    @Transactional(readOnly = true)
    public Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    @Transactional
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Product> findProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    @Transactional(readOnly = true)
    public List<Product> findProductsByPlatform(Long platformId) {
        return productRepository.findByPlatformId(platformId);
    }

    /* Transactional = Annotazione che gestisce la transazione a livello di metodo .readOnly = true per i metodi di lettura
     migliora la performance, perché non c'è bisogno di gestire le transazioni di scrittura. (Google)

    @Transactional(readOnly = true)
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }*/

   /* @Transactional(readOnly = true)
    public List<Product> findProductsByName(String name) {
        return productRepository.findByName(name);
    }*/
}

