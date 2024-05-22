package MatteoOrlando.CapStone.services;

import MatteoOrlando.CapStone.dto.ProductDTO;
import MatteoOrlando.CapStone.dto.ReviewDTO;
import MatteoOrlando.CapStone.entities.Platform;
import MatteoOrlando.CapStone.entities.Product;
import MatteoOrlando.CapStone.entities.Review;
import MatteoOrlando.CapStone.entities.User;
import MatteoOrlando.CapStone.entities.Category;
import MatteoOrlando.CapStone.exceptions.NotFoundException;
import MatteoOrlando.CapStone.repositories.ProductDAO;
import MatteoOrlando.CapStone.repositories.CategoryDAO;
import MatteoOrlando.CapStone.repositories.PlatformDAO;
import MatteoOrlando.CapStone.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private PlatformDAO platformDAO;

    @Autowired
    private UserDAO userDAO;

    private ProductDTO convertToDTO(Product product) {
        Set<ReviewDTO> reviews = product.getReviews().stream()
                .map(review -> new ReviewDTO(review.getId(), review.getUser().getId(), review.getProduct().getId(), review.getRating(), review.getContent()))
                .collect(Collectors.toSet());

        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getPlatforms(),
                product.getImageUrl(),
                reviews
        );
    }

    private Product convertToEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.id());
        product.setName(productDTO.name());
        product.setDescription(productDTO.description());
        product.setPrice(productDTO.price());
        product.setImageUrl(productDTO.imageUrl());

        Category category = categoryDAO.findById(productDTO.categoryId())
                .orElseThrow(() -> new NotFoundException("Category not found with id: " + productDTO.categoryId()));
        product.setCategory(category);

        Set<Platform> platforms = productDTO.platforms().stream()
                .map(platformDTO -> platformDAO.findById(platformDTO.getId())
                        .orElseThrow(() -> new NotFoundException("Platform not found with id: " + platformDTO.getId())))
                .collect(Collectors.toSet());
        product.setPlatforms(platforms);

        Set<Review> reviews = productDTO.reviews().stream()
                .map(reviewDTO -> {
                    Review review = new Review();
                    review.setId(reviewDTO.id());
                    review.setContent(reviewDTO.comment());
                    review.setRating(reviewDTO.rating());

                    User user = userDAO.findById(reviewDTO.userId())
                            .orElseThrow(() -> new NotFoundException("User not found with id: " + reviewDTO.userId()));
                    review.setUser(user);

                    review.setProduct(product);

                    return review;
                })
                .collect(Collectors.toSet());
        product.setReviews(reviews);

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
}
