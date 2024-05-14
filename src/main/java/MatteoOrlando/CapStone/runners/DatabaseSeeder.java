package MatteoOrlando.CapStone.runners;

import MatteoOrlando.CapStone.repositories.CategoryDAO;
import MatteoOrlando.CapStone.repositories.PlatformDAO;
import MatteoOrlando.CapStone.repositories.ProductDAO;
import MatteoOrlando.CapStone.repositories.ReviewDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import MatteoOrlando.CapStone.entities.Category;
import MatteoOrlando.CapStone.entities.Platform;
import MatteoOrlando.CapStone.entities.Product;
import MatteoOrlando.CapStone.entities.Review;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private PlatformDAO platformDAO;
    @Autowired
    private ReviewDAO reviewDAO;



    private void seedProducts() {
        if (productDAO.count() == 100) {  // Verifica se il database è già popolato
            List<Product> products = List.of();

            productDAO.saveAll(products);
        }
    }

    private Product createProduct(String name, String description, BigDecimal price, String categoryName) {
        Category category = findOrCreateCategory(categoryName);
        Set<Platform> platforms = findOrCreatePlatforms(Arrays.asList("PC", "PlayStation 5"));
        Set<Review> reviews = createReviews();

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategory(category);
        product.setPlatforms(platforms);
        product.setReviews(reviews);

        return product;
    }

    private Category findOrCreateCategory(String categoryName) {
        return categoryDAO.findByName(categoryName)
                .orElseGet(() -> {
                    Category newCategory = new Category();
                    newCategory.setName(categoryName);
                    return categoryDAO.save(newCategory);
                });
    }

    private Set<Platform> findOrCreatePlatforms(List<String> platformNames) {
        Set<Platform> platforms = new HashSet<>();
        for (String name : platformNames) {
            Platform platform = platformDAO.findByName(name)
                    .orElseGet(() -> {
                        Platform newPlatform = new Platform();
                        newPlatform.setName(name);
                        return platformDAO.save(newPlatform);
                    });
            platforms.add(platform);
        }
        return platforms;
    }

    private Set<Review> createReviews() {
        Set<Review> reviews = new HashSet<>();
        // Aggiungi recensioni di esempio o implementa una logica simile a quella delle piattaforme per la creazione dinamica
        Review review = new Review();
        review.setContent("Great game!");
        // Imposta altri attributi necessari e salva nel DAO se richiesto
        reviews.add(review);
        return reviews;
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
