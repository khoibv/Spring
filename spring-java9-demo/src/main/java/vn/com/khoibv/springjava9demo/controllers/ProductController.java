package vn.com.khoibv.springjava9demo.controllers;


import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.khoibv.springjava9demo.model.Product;

@RestController
@RequestMapping("api/product")
public class ProductController {

  private List<Product> products = Arrays.asList(
      new Product(1, "Apple", 10d),
      new Product(1, "Watermelon", 20d)
  );


  @GetMapping("list")
  public List<Product> list() {

    return products;

  }

}
