package MatteoOrlando.CapStone.services;

import MatteoOrlando.CapStone.dto.ProductDTO;
import MatteoOrlando.CapStone.entities.Product;
import MatteoOrlando.CapStone.entities.Category;
import MatteoOrlando.CapStone.exceptions.NotFoundException;
import MatteoOrlando.CapStone.repositories.ProductDAO;
import MatteoOrlando.CapStone.repositories.CategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private CategoryDAO categoryDAO;

    private ProductDTO convertToDTO(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getCategory().getId());
    }

    private Product convertToEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.id());
        product.setName(productDTO.name());
        product.setDescription(productDTO.description());
        product.setPrice(productDTO.price());

        Category category = categoryDAO.findById(productDTO.categoryId())
                .orElseThrow(() -> new NotFoundException("Category not found with id: " + productDTO.categoryId()));
        product.setCategory(category);

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

    /*public Category findOrCreateCategory(String categoryName) {
        // Cerca
        Optional<Category> existingCategory = categoryDAO.findByName(categoryName);
        if (existingCategory.isPresent()) {
            return existingCategory.get();
        } else {
            // Se non esiste, crea
            Category newCategory = new Category();
            newCategory.setName(categoryName);
            return categoryDAO.save(newCategory);  // Salva
        }
    }*/

    /* public boolean existsById(Long id) {
        return productDAO.existsById(id);
    }*/
}
