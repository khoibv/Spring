package vn.com.khoibv.springjava9demo.controllers;


import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.com.khoibv.springjava9demo.model.Customer;

@RestController
@RequestMapping("api/customer")
public class CustomerController {

  private List<Customer> customers = Arrays.asList(
      new Customer(1, "khoibv", "Hanoi, Vietnam"),
      new Customer(2, "ozaki", "Tokyo, Japan"),
      new Customer(3, "tungnk", "Hanoi, Vietnam"),
      new Customer(4, "hasegawa", "Tokyo, Japan"),
      new Customer(5, "tuannm3", "Hanoi, Vietnam")
  );

  @GetMapping("list")
  public List<Customer> list() {

    return customers;

  }

  @GetMapping("findById")
  public Customer findById(@RequestParam("id") int customerId) {

    return customers.stream()
        .filter(customer -> customer.getId() == customerId)
        .findFirst()
        .orElse(null);
  }

}
