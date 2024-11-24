# Laborator 6

Rewrite the data access layer of the application created in the previous laboratories, implementing the repository classes as Enterprise Java Beans.
Use the support offered by the EJB technology for implementing transactions.

Extend the model of the application such that clients can order products from the warehouse. An order contains a set of products and their quantities.
The warehouse has a predefined quantity (initial stock) of each product. The stocks will be decreased accordingly after each order.
The following enterprise beans must be implemented:

- A stateless session bean that offers methods for determining the current stock of a product.
- A stateful session bean responsible with the creation of the order.
The registration of an order should be atomic, either all the desired products can be delivered, or the transaction will be rolled back.
- A singleton session bean that keeps an in-memory map of the client orders. The map will be instantiated at application startup and updated whenever a new order is created.
Log the invocations and compute the running times of at least one business method, using an EJB interceptor.

S-a modificat structura bazei de date pentru a acomoda modificările necesare. Astfel, a fost creată o nouă clasă entity - OrderItem.
Pentru partea ejb, au fost create clasele InventoryBean, LoggingInterceptor, OrderBean si OrderCacheBean. 