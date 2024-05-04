package MatteoOrlando.CapStone.services;

import MatteoOrlando.CapStone.entities.Category;
import MatteoOrlando.CapStone.repositories.CategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    public Category getCategoryById(Long id) {
        return categoryDAO.findById(id).orElse(null);
    }

    public List<Category> getAllCategories() {
        return categoryDAO.findAll();
    }

    public Category createCategory(Category category) {
        return categoryDAO.save(category);
    }

    public Category updateCategory(Category category) {
        return categoryDAO.save(category);
    }

    public void deleteCategory(Long id) {
        categoryDAO.deleteById(id);
    }
}
