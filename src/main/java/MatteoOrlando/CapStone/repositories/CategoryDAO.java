package MatteoOrlando.CapStone.repositories;

import MatteoOrlando.CapStone.entities.Category;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CategoryDAO {

    @Autowired
    private EntityManager entityManager;

    public Category getCategoryById(Long id) {
        return entityManager.find(Category.class, id);
    }

    public List<Category> getAllCategories() {
        return entityManager.createQuery("SELECT c FROM Category c", Category.class).getResultList();
    }

    public Category saveCategory(Category category) {
        entityManager.persist(category);
        return category;
    }

    public Category updateCategory(Category category) {
        return entityManager.merge(category);
    }

    public void deleteCategory(Long id) {
        Category category = entityManager.find(Category.class, id);
        if (category != null) {
            entityManager.remove(category);
        }
    }
}
