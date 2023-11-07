package sa.system.Midniyompan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sa.system.Midniyompan.entity.*;
import sa.system.Midniyompan.model.AddCartRequest;
import sa.system.Midniyompan.model.OrderRequest;
import sa.system.Midniyompan.repository.*;
import sa.system.Midniyompan.common.Status;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Service
public class OrderService {


    @Autowired
    private PurchaseOrderRepository orderRepository;


    @Autowired
    private OrderItemRepository itemRepository;


    @Autowired
    private ProductRepository productRepository;


    private UUID currentOrderId;
    private UUID postId;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private FormPORepository formPORepository;

    public void createNewOrder() {
        PurchaseOrder newOrder = new PurchaseOrder();
        newOrder.setStatus(Status.ORDER);
        PurchaseOrder record = orderRepository.save(newOrder);
        currentOrderId = record.getId();
    }


    public void order(UUID productId, AddCartRequest request) {
        if (currentOrderId == null)
            createNewOrder();


        PurchaseOrder currentOrder =
                orderRepository.findById(currentOrderId).get();
        Product product = productRepository.findById(productId).get();

        OrderItem item = new OrderItem();
        item.setId(new OrderItemKey(currentOrderId, productId));
        if (product.getInventory() <= 0) {
            currentOrder.setStatus(Status.RESERVE);
        }else{
            currentOrder.setStatus(Status.ORDER);
        }
        item.setPurchaseOrder(currentOrder);
        item.setProduct(product);
        item.setQuantity(request.getQuantity());

        product.setInventory(item.getDecrease());

        itemRepository.save(item);
    }
    public void deleteOrder(UUID itemId) {
        if (currentOrderId == null)
            return; // ไม่มีออร์เดอร์ที่จะลบ

        PurchaseOrder currentOrder = orderRepository.findById(currentOrderId).get();
        Product product = productRepository.findById(itemId).get();

        OrderItemKey orderItemKey = new OrderItemKey(currentOrderId, itemId);
        OrderItem item = itemRepository.findById(orderItemKey).orElse(null);

        if (item != null) {
            itemRepository.delete(item);

        }
    }

    public PurchaseOrder getCurrentOrder() {
        if (currentOrderId == null)
            createNewOrder();
        return orderRepository.findById(currentOrderId).get();
    }
    public PurchaseOrder getOrder() {

        return orderRepository.findById(postId).get();
    }



    public void submitOrder(OrderRequest request) {
        PurchaseOrder currentOrder =
                orderRepository.findById(currentOrderId).get();
        currentOrder.setTimestamp(LocalDateTime.now());
        if (currentOrder.getStatus() != Status.RESERVE){
            currentOrder.setStatus(Status.InProcess);
        }
        Customer customer =
                customerRepository.findById(request.getCustomerId()).get();
        currentOrder.setCustomer(customer);
        orderRepository.save(currentOrder);
        postId = (currentOrder.getId());
        currentOrderId = null;

    }

    public PurchaseOrder getById(UUID orderId) {
        return orderRepository.findById(orderId).get();
    }
    public List<PurchaseOrder> getAllOrders() {
        return orderRepository.findAll();
    }


    public void finishOrder(UUID orderId) {
        PurchaseOrder record = orderRepository.findById(orderId).get();
        record.setStatus(Status.FINISH);
        orderRepository.save(record);
    }




}

