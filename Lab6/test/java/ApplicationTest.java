import com.example.lab6.ejb.InventoryBean;
import com.example.lab6.ejb.OrderBean;

import jakarta.ejb.EJB;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApplicationTest {

    @EJB
    private InventoryBean inventoryBean;

    @EJB
    private OrderBean orderBean;

    @Test
    public void testOrderPlacement() {
        int productId = 3;
        System.out.println("Stock for product " + productId + ": " + inventoryBean.getStockLevel(productId));

        orderBean.addItemToOrder(3, 10);
        boolean orderPlaced = orderBean.placeOrder(1);

        assertTrue(orderPlaced, "Order should be placed successfully if stock is sufficient.");
    }
}

