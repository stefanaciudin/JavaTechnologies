import com.example.lab55.entity.Product;
import jakarta.persistence.*;
import org.junit.*;

public class ProductTest {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("VRP_DB_Unit");


    @Test
    public void testProductPersistence() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Product product = new Product();
        product.setName("Sample Product");
        product.setQuantity(100);
        product.setDescription("A test for compulsory product");

        em.persist(product);
        em.getTransaction().commit();

        Product foundProduct = em.find(Product.class, product.getProductId());
        Assert.assertNotNull("Product should be saved in the database", foundProduct);
        Assert.assertEquals("Sample Product", foundProduct.getName());

        em.close();
    }
}