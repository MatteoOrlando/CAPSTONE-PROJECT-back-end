package MatteoOrlando.CapStone.services;

import MatteoOrlando.CapStone.entities.Category;
import MatteoOrlando.CapStone.exceptions.NotFoundException;

import MatteoOrlando.CapStone.repositories.CategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    public Category getCategoryByName(String name) {
        return categoryDAO.findByName(name)
                .orElseThrow(() -> new NotFoundException("Category not found with name: " + name));
    }

    public Category getCategoryById(Long id) {
        return categoryDAO.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found with id: " + id));
    }

    public List<Category> getAllCategories() {
        return categoryDAO.findAll();
    }

    public Category createCategory(Category category) {
        return categoryDAO.save(category);
    }

    public Category updateCategory(Category category) {
        if (!categoryDAO.existsById(category.getId())) {
            throw new NotFoundException("Cannot update category. No category found with id: " + category.getId());
        }
        return categoryDAO.save(category);
    }

    public void deleteCategory(Long id) {
        if (!categoryDAO.existsById(id)) {
            throw new NotFoundException("Cannot delete category. No category found with id: " + id);
        }
        categoryDAO.deleteById(id);
    }
}
