package MatteoOrlando.CapStone.services;

import MatteoOrlando.CapStone.dto.CategoryDTO;
import MatteoOrlando.CapStone.entities.Category;
import MatteoOrlando.CapStone.exceptions.NotFoundException;
import MatteoOrlando.CapStone.repositories.CategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    private CategoryDTO convertToDTO(Category category) {
        return new CategoryDTO(category.getId(), category.getName());
    }

    private Category convertToEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.id());
        category.setName(categoryDTO.name());
        return category;
    }

    public CategoryDTO getCategoryByName(String name) {
        Category category = categoryDAO.findByName(name)
                .orElseThrow(() -> new NotFoundException("Category not found with name: " + name));
        return convertToDTO(category);
    }

    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryDAO.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found with id: " + id));
        return convertToDTO(category);
    }

    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryDAO.findAll();
        return categories.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = convertToEntity(categoryDTO);
        Category savedCategory = categoryDAO.save(category);
        return convertToDTO(savedCategory);
    }

    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        if (!categoryDAO.existsById(id)) {
            throw new NotFoundException("Cannot update category. No category found with id: " + id);
        }
        Category category = convertToEntity(categoryDTO);
        category.setId(id);
        Category updatedCategory = categoryDAO.save(category);
        return convertToDTO(updatedCategory);
    }

    public void deleteCategory(Long id) {
        if (!categoryDAO.existsById(id)) {
            throw new NotFoundException("Cannot delete category. No category found with id: " + id);
        }
        categoryDAO.deleteById(id);
    }
}

