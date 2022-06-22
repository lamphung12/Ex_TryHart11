package com.example.tryhart11.controller;

import com.example.tryhart11.model.Category;
import com.example.tryhart11.model.Product;
import com.example.tryhart11.service.category.CategoryService;
import com.example.tryhart11.service.category.ICategoryService;
import com.example.tryhart11.service.product.IProductService;
import com.example.tryhart11.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@CrossOrigin("*")
public class ProductController {

    @Autowired
   private IProductService productService;
    @Autowired
   private ICategoryService categoryService;

    @ModelAttribute("category")
    public Iterable<Category> category(){
        return categoryService.findAll();
    }

//    @GetMapping("/home")
//    public ModelAndView showAll(){
//        ModelAndView modelAndView = new ModelAndView("list");
//        modelAndView.addObject("product",productService.findAll());
//        return modelAndView;
//    }
    @GetMapping("/products")
    public ResponseEntity <Iterable<Product>> fillAll(){
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

//    @GetMapping("/create-product")
//    public ModelAndView showCreateForm(){
//        ModelAndView modelAndView = new ModelAndView("/create");
//        return modelAndView;
//    }
//    @PostMapping("/create-product")
//    public ModelAndView save(@ ModelAttribute("product") Product product  ){
//        productService.save(product);
//        ModelAndView modelAndView = new ModelAndView("redirect:/home");
//        return modelAndView;
//    }
    @GetMapping("/products/oder-by-price")
    public ResponseEntity <Iterable<Product>> fillAllByOrderByPrice(){
        return new ResponseEntity<>(productService.findAllByOrderByPrice(), HttpStatus.OK);
    }

    @GetMapping("/products/price-between")
    public ResponseEntity <Iterable<Product>> fillAllByPriceBetween(@RequestParam("one") int one,@RequestParam("two")int two  ){
        return new ResponseEntity<>(productService.findAllByPriceBetween(one, two), HttpStatus.OK);
    }

    @GetMapping("/products/top4")
    public ResponseEntity <Iterable<Product>> fillAllByTop4(){
        return new ResponseEntity<>(productService.findTop4New(), HttpStatus.OK);
    }

    @GetMapping("/products/findByCategory")
    public ResponseEntity <Iterable<Product>> fillAllByCategory_Name(@RequestParam("name") String name){
        return new ResponseEntity<>(productService.findAllByCategory_Name(name), HttpStatus.OK);
    }



//    @PostMapping("/products")
//    public ResponseEntity create(@RequestBody Product product){
//        productService.save(product);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @GetMapping("edit-product/{id}")
//    public ModelAndView showEditForm(@PathVariable("id") Long id){
//        Optional<Product> product = productService.findById(id);
//        ModelAndView modelAndView = new ModelAndView("/edit");
//        modelAndView.addObject("product",product.get());
//        return modelAndView;
//    }
//    @PostMapping("/edit-product")
//    public ModelAndView edit(@ModelAttribute("product") Product product){
//        productService.save(product);
//        ModelAndView modelAndView = new ModelAndView("redirect:/home");
//        return modelAndView;
//    }
//
//    @GetMapping("/delete-product/{id}")
//    public ModelAndView delete(@PathVariable("id") Long id){
//        ModelAndView modelAndView = new ModelAndView("redirect:/home");
//        productService.remote(id);
//        return modelAndView;
//    }
//
//    @GetMapping("/findTop4")
//    public ModelAndView top4(){
//        ModelAndView modelAndView = new ModelAndView("/list");
//        Iterable<Product> products = productService.findTop4New();
//        modelAndView.addObject("product",products);
//        return modelAndView;
//    }
//
//    @GetMapping("/search-product")
//    public ModelAndView search(@RequestParam("name") String name ){
//        ModelAndView modelAndView = new ModelAndView("/list");
//        Iterable<Product> products = productService.findAllByNameContaining(name);
//        modelAndView.addObject("product",products);
//        return modelAndView;
//    }
//
//    @GetMapping("/sortPriceAsc")
//    public ModelAndView sortPrice(){
//        ModelAndView modelAndView = new ModelAndView("/list");
//        Iterable<Product> products = productService.findAllByPriceAsc();
//        modelAndView.addObject("product",products);
//        return modelAndView;
//    }
//
//    @GetMapping("/sortPriceDesc")
//    public ModelAndView sortPriceDesc(){
//        ModelAndView modelAndView = new ModelAndView("/list");
//        Iterable<Product> products = productService.findAllByPrice();
//        modelAndView.addObject("product",products);
//        return modelAndView;
//    }
//
//    @GetMapping("/sortByPriceBetween")
//    public ModelAndView sortByBetween(){
//        ModelAndView modelAndView = new ModelAndView("/sort");
//        return modelAndView;
//    }
//    @PostMapping("/sortByPriceBetween")
//    public ModelAndView sortByBetween(@RequestParam("one") int one, @RequestParam("two") int two ){
//        ModelAndView modelAndView = new ModelAndView("/sort");
//        Iterable<Product> products = productService.findAllByPriceBetween(one, two);
//        modelAndView.addObject("product",products);
//        return modelAndView;
//    }
//
//    @GetMapping("/sortByCategory")
//    public ModelAndView sortByCategory(@RequestParam String name){
//        ModelAndView modelAndView = new ModelAndView("/searchByC");
//        Iterable<Product> products= productService.findAllByCategory_Name(name);
//        modelAndView.addObject("product",products);
//        return modelAndView;
//    }














}
