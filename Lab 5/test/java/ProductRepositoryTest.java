
import com.example.lab55.entity.Product;
import com.example.lab55.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductRepositoryTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private ProductRepository productRepository;

    @BeforeAll
    public void setup() {
        emf = Persistence.createEntityManagerFactory("VRP_DB_Unit");
        em = emf.createEntityManager();
        productRepository = new ProductRepository();
        productRepository.entityManager = em;
    }

    @Test
    public void testCreateProduct() {
        Product product = new Product();
        product.setName("Test Product");
        product.setQuantity(10);
        product.setDescription("A test description");

        em.getTransaction().begin();
        productRepository.create(product);
        em.getTransaction().commit();

        Assertions.assertNotNull(product.getProductId(), "Product ID should be generated after persisting");
    }

    @Test
    public void testReadProduct() {
        Product product = productRepository.read(11);
        Assertions.assertNotNull(product, "Product should be found in the database");
    }

    @Test
    public void testUpdateProduct() {
        em.getTransaction().begin();
        Product product = productRepository.read(11);
        product.setQuantity(20);
        Product updatedProduct = productRepository.update(product);
        em.getTransaction().commit();

        Assertions.assertEquals(20, updatedProduct.getQuantity(), "Product quantity should be updated");
    }

    @Test
    public void testDeleteProduct() {
        em.getTransaction().begin();
        productRepository.delete(11);
        em.getTransaction().commit();

        Product product = productRepository.read(11);
        Assertions.assertNull(product, "Product should be deleted from the database");
    }

    @Test
    public void testFindByName() {
        List<Product> products = productRepository.findByName("Test Product");
        Assertions.assertFalse(products.isEmpty(), "Should find products with the specified name");
    }

    @AfterAll
    public void cleanup() {
        em.close();
        emf.close();
    }
}
