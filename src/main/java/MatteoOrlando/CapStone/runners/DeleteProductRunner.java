/*package MatteoOrlando.CapStone.runners;

import MatteoOrlando.CapStone.entities.Category;
import MatteoOrlando.CapStone.entities.Product;
import MatteoOrlando.CapStone.repositories.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Random;

@Component
public class DeleteProductRunner implements ApplicationRunner {
    @Autowired
    private ProductDAO productDAO;

    @Override
    public void run(ApplicationArguments args) {
        if (args.containsOption("deleteAllProducts")) {
            productDAO.deleteAll();
        } else {
            Random random = new Random();
            for (int i = 1; i <= 2; i++) {
                Product product = new Product();
                product.setName("Product " + i);
                product.setDescription("Description for Product " + i);
                product.setPrice(new BigDecimal("39.99").multiply(new BigDecimal(random.nextInt(10) + 1)));

                Category category = new Category();
                category.setId(1L);
                product.setCategory(category);

                productDAO.save(product);
            }
        }
    }
}*/
