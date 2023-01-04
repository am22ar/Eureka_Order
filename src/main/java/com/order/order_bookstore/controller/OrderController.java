package com.order.order_bookstore.controller;

import com.order.order_bookstore.dto.OrderDto;
import com.order.order_bookstore.dto.ResponseDto;
import com.order.order_bookstore.model.OrderModel;
import com.order.order_bookstore.service.IOrderService;
import com.order.order_bookstore.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookstore")
public class OrderController {
    @Autowired
    OrderService orderService;
    @PostMapping("/placeOrder")
    public ResponseEntity<ResponseDto> placeOrder(@Valid @RequestBody OrderDto orderDTO) {
        OrderModel order=orderService.placeOrder(orderDTO);
        ResponseDto responseDto=new ResponseDto("Order details are submitted!",order,null);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
    @GetMapping("/getAllOrders")
    public ResponseEntity<ResponseDto> getAllOrders(){
        List<OrderModel> orderModelList = orderService.getAllOrders();
        ResponseDto responseDto=new ResponseDto("Get All orders!",orderModelList,null);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
    @GetMapping("/getOrderById/{orderId}")
    public ResponseEntity<ResponseDto> getOrderById(@PathVariable(value = "orderId") long orderId){
        OrderModel order = orderService.getOrderById(orderId);
        ResponseDto responseDto=new ResponseDto("Order Details of Id:"+orderId,order,null);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
    @PutMapping("/updateOrderById/{orderId}")
    public ResponseEntity<ResponseDto> updateOrderById(@PathVariable(value = "orderId") long orderId, @RequestBody OrderDto orderDto){
        OrderModel orderModel = orderService.updateRecordById(orderId,orderDto);
        ResponseDto responseDto=new ResponseDto("Updated Order Details of ID:"+orderId,"Data Updated Successfully...",null);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
    @PutMapping("/cancelOrderById/{orderId}")
    public ResponseEntity<ResponseDto> cancelOrderById(@PathVariable("orderId") long orderId,@RequestBody OrderDto orderDto){
        ResponseDto responseDto=new ResponseDto("Order Cancel","Order cancelled successfully for Id:"+orderId,null);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
}
