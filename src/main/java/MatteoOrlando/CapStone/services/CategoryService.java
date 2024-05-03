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
        return categoryDAO.getCategoryById(id);
    }

    public List<Category> getAllCategories() {
        return categoryDAO.getAllCategories();
    }

    public Category createCategory(Category category) {
        return categoryDAO.saveCategory(category);
    }

    public Category updateCategory(Category category) {
        return categoryDAO.updateCategory(category);
    }

    public void deleteCategory(Long id) {
        categoryDAO.deleteCategory(id);
    }
}
