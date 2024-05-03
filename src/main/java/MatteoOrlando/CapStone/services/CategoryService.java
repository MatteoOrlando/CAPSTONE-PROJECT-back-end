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


    public Category getCategoryByName(String name) {
        return categoryDAO.findByName(name);
    }

}
