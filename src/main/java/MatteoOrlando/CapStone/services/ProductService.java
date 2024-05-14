package MatteoOrlando.CapStone.services;

import MatteoOrlando.CapStone.dto.ProductDTO;
import MatteoOrlando.CapStone.entities.Product;
import MatteoOrlando.CapStone.entities.Category;
import MatteoOrlando.CapStone.entities.Review;
import MatteoOrlando.CapStone.entities.Platform;
import MatteoOrlando.CapStone.exceptions.NotFoundException;
import MatteoOrlando.CapStone.repositories.ProductDAO;
import MatteoOrlando.CapStone.repositories.CategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private CategoryDAO categoryDAO;

    private ProductDTO convertToDTO(Product product) {
        Set<Long> categoryIds = product.getCategories().stream()
                .map(Category::getId)
                .collect(Collectors.toSet());

        Set<String> reviews = product.getReviews().stream()
                .map(Review::getContent)
                .collect(Collectors.toSet());

        Set<String> platforms = product.getPlatforms().stream()
                .map(Platform::getName)
                .collect(Collectors.toSet());

        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                categoryIds,
                reviews,
                platforms
        );
    }

    private Product convertToEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.id());
        product.setName(productDTO.name());
        product.setDescription(productDTO.description());
        product.setPrice(productDTO.price());

        // Convert categoryIds to Category entities
        Set<Category> categories = productDTO.categoryIds().stream()
                .map(id -> categoryDAO.findById(id)
                        .orElseThrow(() -> new NotFoundException("Category not found with id: " + id)))
                .collect(Collectors.toSet());
        product.setCategories(categories);

        // Convert reviews to Review entities
        Set<Review> reviews = productDTO.reviews().stream()
                .map(reviewText -> {
                    Review review = new Review();
                    review.setContent(reviewText);
                    review.setProduct(product);  // Set the product reference
                    return review;
                }).collect(Collectors.toSet());
        product.setReviews(reviews);

        // Convert platforms to Platform entities
        Set<Platform> platforms = productDTO.platforms().stream()
                .map(platformName -> {
                    Platform platform = new Platform();
                    platform.setName(platformName);
                    platform.getProducts().add(product);  // Add the product reference
                    return platform;
                }).collect(Collectors.toSet());
        product.setPlatforms(platforms);

        return product;
    }

    public ProductDTO getProductById(Long id) {
        Product product = productDAO.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found with id: " + id));
        return convertToDTO(product);
    }

    public List<ProductDTO> getProductsByName(String name) {
        List<Product> products = productDAO.findByNameContainingIgnoreCase(name);
        if (products.isEmpty()) {
            throw new NotFoundException("No products found with name: " + name);
        }
        return products.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<ProductDTO> getByCategoryId(Long categoryId) {
        List<Product> products = productDAO.findByCategoryId(categoryId);
        return products.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productDAO.findAll();
        return products.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        Product savedProduct = productDAO.save(product);
        return convertToDTO(savedProduct);
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        if (!productDAO.existsById(id)) {
            throw new NotFoundException("Cannot update product. No product found with id: " + id);
        }
        Product product = convertToEntity(productDTO);
        product.setId(id);
        Product updatedProduct = productDAO.save(product);
        return convertToDTO(updatedProduct);
    }

    public void deleteProduct(Long id) {
        if (!productDAO.existsById(id)) {
            throw new NotFoundException("Cannot delete product. No product found with id: " + id);
        }
        productDAO.deleteById(id);
    }

    // Metodo per creare un prodotto con categorie, recensioni e piattaforme
    private Product createProduct(String name, String description, BigDecimal price, Set<Long> categoryIds, List<String> reviews, List<String> platforms) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);

        // Convert categoryIds to Category entities
        Set<Category> categories = categoryIds.stream()
                .map(id -> categoryDAO.findById(id)
                        .orElseThrow(() -> new NotFoundException("Category not found with id: " + id)))
                .collect(Collectors.toSet());
        product.setCategories(categories);

        // Convert reviews to Review entities
        Set<Review> reviewEntities = reviews.stream()
                .map(reviewText -> {
                    Review review = new Review();
                    review.setContent(reviewText);
                    review.setProduct(product);
                    return review;
                }).collect(Collectors.toSet());
        product.setReviews(reviewEntities);

        // Convert platforms to Platform entities
        Set<Platform> platformEntities = platforms.stream()
                .map(platformName -> {
                    Platform platform = new Platform();
                    platform.setName(platformName);
                    platform.getProducts().add(product);
                    return platform;
                }).collect(Collectors.toSet());
        product.setPlatforms(platformEntities);

        return product;
    }

    // Metodo per popolare il database con i prodotti di esempio
    public void seedProducts() {
        if (productDAO.count() == 0) {  // Verifica se il database ha meno di 100 prodotti
            List<Product> products = Arrays.asList(
                    createProduct("The Witcher 3: Wild Hunt", "Un gioco di ruolo open-world ambientato in un universo fantasy", new BigDecimal("39.99"), Set.of(1L), List.of("Un capolavoro del genere RPG.", "Un mondo vasto e immersivo."), List.of("PC", "PS4", "Xbox One")),
                    createProduct("Call of Duty: Future Warfare", "Uno sparatutto in prima persona ambientato in un futuro distopico", new BigDecimal("69.99"), Set.of(3L), List.of("Azione frenetica e adrenalinica.", "Grafica mozzafiato."), List.of("PC", "PS5", "Xbox Series X"))
            );

            productDAO.saveAll(products);
        }
    }
}

