package com.order.order_bookstore.service;

import com.order.order_bookstore.dto.OrderDto;
import com.order.order_bookstore.exception.BookStoreException;
import com.order.order_bookstore.model.BookModel;
import com.order.order_bookstore.model.EmailModel;
import com.order.order_bookstore.model.OrderModel;
import com.order.order_bookstore.model.UserModel;
import com.order.order_bookstore.repository.OrderRepository;
import com.order.order_bookstore.util.OrderToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService{
    @Autowired(required = true)
    private RestTemplate restTemplate;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    private IEmailService iEmailService;
    @Autowired
    private OrderToken orderToken;

    UserModel user;
    BookModel book;
    @Override
    public OrderModel placeOrder(OrderDto orderDto){
         user = restTemplate.getForEntity("http://localhost:8081/bookstore/getRecordById/"+orderDto.getUserId(), UserModel.class).getBody();
         book = restTemplate.getForEntity("http://localhost:8082/bookstore/getBookById/"+orderDto.getBookId(), BookModel.class).getBody();
        Long totalPrice = orderDto.getPrice()* orderDto.quantity;
        if (user!=null || book!=null) {
            OrderModel order = new OrderModel(orderDto.getUserId(), orderDto.getBookId(), totalPrice, orderDto.getEmail(), orderDto.quantity,orderDto.address,orderDto.cancel);
            orderRepository.save(order);
            String token=orderToken.createToken(order.getOrderId());
            EmailModel emailModel = new EmailModel(orderDto.getEmail(),"Order Placed successfully ","Hello your order token is: "+token+" for product ID:"+book);
            iEmailService.sendEmail(emailModel);
            return order;
        } else {
            throw new BookStoreException("User id or book id did not match! Please check and try again!");
        }
    }
    @Override
    public List<OrderModel> getAllOrders(){
        List<OrderModel> orderModelList = orderRepository.findAll();
        return orderModelList;
    }
    @Override
    public OrderModel getOrderById(long orderId){
        Optional<OrderModel> orderModel = orderRepository.findById(orderId);
        if(orderModel.isPresent())
            return orderModel.get();
        else
            throw new BookStoreException("OrderId: "+orderId+" not found..");
    }
    @Override
    public OrderModel updateRecordById(long orderId, OrderDto orderDto){
         user = restTemplate.getForObject("http://localhost:8081/bookstore/getRecordById/"+orderDto.getUserId(), UserModel.class);
         book =  restTemplate.getForObject("http://localhost:8082/bookstore/getBookById/"+orderDto.getBookId(), BookModel.class);
        Long totalPrice = orderDto.getPrice()* orderDto.quantity;
        if (user!=null || book!=null) {
            OrderModel order = new OrderModel(orderDto.getUserId(), orderDto.getBookId(),totalPrice, orderDto.getEmail(), orderDto.quantity,orderDto.address,orderDto.cancel);
            order.setOrderId(orderId);
            return orderRepository.save(order);
        } else {
            throw new BookStoreException("User id or book id did not match! Please check and try again!");
        }
    }
    @Override
    public OrderModel cancelOrderById(long orderId, OrderDto orderDto) {
        Optional<OrderModel> orderModel = orderRepository.findById(orderId);
        if (orderModel.isPresent()) {
            OrderModel order = new OrderModel();
            order.setCancel(true);
            return orderRepository.save(order);
        } else {
            throw new BookStoreException("User id or book id did not match! Please check and try again!");
        }
    }

}
