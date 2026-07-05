package com.example.demo.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;

@Controller
@RequestMapping("/admin/customers")
public class CustomerAdminController {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    
    public CustomerAdminController(
            CustomerRepository customerRepository,
            OrderRepository orderRepository,
            OrderDetailRepository orderDetailRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    // 顧客一覧表示
    @GetMapping
    public String index(Model model) {

        List<Customer> customerList = customerRepository.findAll();
        model.addAttribute("customers", customerList);

        return "admin/customers";
    }

    // 顧客詳細（注文情報）表示
    @GetMapping("/{id}/orders")
    public String show(@PathVariable Integer id, Model model) {

        // 顧客情報
        Customer customer = customerRepository.findById(id).get();
        model.addAttribute("customer", customer);
        // 注文情報（サンプルの場合は１顧客１注文）
        List<Order> orderList = orderRepository.findByCustomerId(id);
        if (orderList != null && orderList.size() > 0) {
            Order order = orderList.get(0);
            model.addAttribute("order", order);
            // 注文明細情報
            List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(order.getId());
            if (orderDetailList != null && orderDetailList.size() > 0) {
                model.addAttribute("orderDetails", orderDetailList);
            }
        }

        return "admin/orders";
    }

}
