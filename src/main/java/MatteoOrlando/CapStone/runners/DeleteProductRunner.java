/*package MatteoOrlando.CapStone.runners;

import MatteoOrlando.CapStone.entities.Category;
import MatteoOrlando.CapStone.entities.Product;
import MatteoOrlando.CapStone.repositories.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Random;

@Component
public class DeleteProductRunner implements ApplicationRunner {
    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args) {
        addImageUrlColumnIfNotExists();

        long count = productDAO.count();
        if (count >= 50) {
            productDAO.deleteAll();
        } else {
            Random random = new Random();
            for (int i = 1; i <= 10; i++) {
                Product product = new Product();
                product.setName("Product " + i);
                product.setDescription("Description for Product " + i);
                product.setPrice(new BigDecimal("39.99").multiply(new BigDecimal(random.nextInt(10) + 1)));

                Category category = new Category();
                category.setId(1L);
                product.setCategory(category);

                productDAO.delete(product);
            }
        }
    }

    private void addImageUrlColumnIfNotExists() {
        String checkColumnExistsQuery = "SELECT column_name FROM information_schema.columns WHERE table_name='products' AND column_name='image_url'";
        Boolean columnExists = jdbcTemplate.query(checkColumnExistsQuery, (ResultSetExtractor<Boolean>) rs -> rs.next());

        if (Boolean.FALSE.equals(columnExists)) {
            String addColumnQuery = "ALTER TABLE products ADD COLUMN image_url VARCHAR(255) DEFAULT 'default_image_url.jpg'";
            jdbcTemplate.execute(addColumnQuery);
        }
    }
}*/
