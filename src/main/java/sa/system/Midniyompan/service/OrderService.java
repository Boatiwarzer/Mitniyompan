package sa.system.Midniyompan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sa.system.Midniyompan.entity.OrderItem;
import sa.system.Midniyompan.entity.OrderItemKey;
import sa.system.Midniyompan.entity.Product;
import sa.system.Midniyompan.entity.PurchaseOrder;
import sa.system.Midniyompan.model.AddCartRequest;
import sa.system.Midniyompan.repository.OrderItemRepository;
import sa.system.Midniyompan.repository.ProductRepository;
import sa.system.Midniyompan.repository.PurchaseOrderRepository;
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
        item.setPurchaseOrder(currentOrder);
        item.setProduct(product);
        item.setQuantity(request.getQuantity());
        if (product.getRemain() <= 0) {
            // สินค้าหมดแล้ว จะทำการจองแทนการสั่งซื้อ
            item.setStatus(Status.RESERVE);
        }else{
            item.setStatus(Status.ORDER);
        }
        item.getDecrease();

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

//    public void plus(UUID productId) {
//        PurchaseOrder currentOrder = orderRepository.findById(currentOrderId).get();
//        OrderItem orderItem = itemRepository.findById(new OrderItemKey(currentOrderId, productId)).get();
//
//        // เพิ่มปริมาณสินค้าที่เลือก
//        int newQuantity = orderItem.getQuantity() + 1;
//        orderItem.setQuantity(newQuantity);
//
//        // ตรวจสอบหากสินค้าเหลือพอหรือไม่
//        Product product = productRepository.findById(productId).get();
//        if (product.getRemain() <= 0) {
//            // สินค้าหมดแล้ว จะทำการจองแทนการสั่งซื้อ
//            currentOrder.setStatus(Status.RESERVE);
//        }
//
//        itemRepository.save(orderItem);
//    }
//    public void minus(UUID productId) {
//        PurchaseOrder currentOrder = orderRepository.findById(currentOrderId).get();
//        OrderItem orderItem = itemRepository.findById(new OrderItemKey(currentOrderId, productId)).get();
//
//        // เพิ่มปริมาณสินค้าที่เลือก
//        if (orderItem.getQuantity() > 1){
//            int newQuantity = orderItem.getQuantity();
//            orderItem.setQuantity(newQuantity);
//
//        }
//        else{
//            int newQuantity = orderItem.getQuantity() - 1;
//            orderItem.setQuantity(newQuantity);
//        }
//
//        // ตรวจสอบหากสินค้าเหลือพอหรือไม่
//        Product product = productRepository.findById(productId).get();
//        if (product.getRemain() <= 0) {
//            // สินค้าหมดแล้ว จะทำการจองแทนการสั่งซื้อ
//            currentOrder.setStatus(Status.RESERVE);
//        }
//
//        itemRepository.save(orderItem);
//    }

    public void submitOrder() {
        PurchaseOrder currentOrder =
                orderRepository.findById(currentOrderId).get();
        currentOrder.setTimestamp(LocalDateTime.now());
        if (currentOrder.getStatus() != Status.RESERVE){
            currentOrder.setStatus(Status.CONFIRM);
        }
        orderRepository.save(currentOrder);
        currentOrderId = null;

    }
    public List<PurchaseOrder> getAllOrders() {
        return orderRepository.findAll();
    }
    public PurchaseOrder getById(UUID orderId) {
        return orderRepository.findById(orderId).get();
    }


    public void finishOrder(UUID orderId) {
        PurchaseOrder record = orderRepository.findById(orderId).get();
        record.setStatus(Status.FINISH);
        orderRepository.save(record);
    }



}

