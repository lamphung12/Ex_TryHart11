package com.example.tryhart11.controller;

import com.example.tryhart11.model.Category;
import com.example.tryhart11.model.Product;
import com.example.tryhart11.service.category.CategoryService;
import com.example.tryhart11.service.category.ICategoryService;
import com.example.tryhart11.service.product.IProductService;
import com.example.tryhart11.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
   private IProductService productService;
    @Autowired
   private ICategoryService categoryService;

    @ModelAttribute("category")
    public Iterable<Category> category(){
        return categoryService.findAll();
    }

    @GetMapping("/home")
    public ModelAndView showAll(){
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("product",productService.findAll());
        return modelAndView;
    }

    @GetMapping("/create-product")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/create");
        return modelAndView;
    }
    @PostMapping("/create-product")
    public ModelAndView save(@ ModelAttribute("product") Product product  ){
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        return modelAndView;
    }

    @GetMapping("edit-product/{id}")
    public ModelAndView showEditForm(@PathVariable("id") Long id){
        Optional<Product> product = productService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("product",product.get());
        return modelAndView;
    }
    @PostMapping("/edit-product")
    public ModelAndView edit(@ModelAttribute("product") Product product){
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        return modelAndView;
    }

    @GetMapping("/delete-product/{id}")
    public ModelAndView delete(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        productService.remote(id);
        return modelAndView;
    }

    @GetMapping("/findTop4")
    public ModelAndView top4(){
        ModelAndView modelAndView = new ModelAndView("/list");
        Iterable<Product> products = productService.findTop4New();
        modelAndView.addObject("product",products);
        return modelAndView;
    }

    @GetMapping("/search-product")
    public ModelAndView search(@RequestParam("name") String name ){
        ModelAndView modelAndView = new ModelAndView("/list");
        Iterable<Product> products = productService.findAllByNameContaining(name);
        modelAndView.addObject("product",products);
        return modelAndView;
    }

    @GetMapping("/sortPriceAsc")
    public ModelAndView sortPrice(){
        ModelAndView modelAndView = new ModelAndView("/list");
        Iterable<Product> products = productService.findAllByPriceAsc();
        modelAndView.addObject("product",products);
        return modelAndView;
    }

    @GetMapping("/sortPriceDesc")
    public ModelAndView sortPriceDesc(){
        ModelAndView modelAndView = new ModelAndView("/list");
        Iterable<Product> products = productService.findAllByPrice();
        modelAndView.addObject("product",products);
        return modelAndView;
    }

    @GetMapping("/sortByPriceBetween")
    public ModelAndView sortByBetween(){
        ModelAndView modelAndView = new ModelAndView("/sort");
        return modelAndView;
    }
    @PostMapping("/sortByPriceBetween")
    public ModelAndView sortByBetween(@RequestParam("one") int one, @RequestParam("two") int two ){
        ModelAndView modelAndView = new ModelAndView("/sort");
        Iterable<Product> products = productService.findAllByPriceBetween(one, two);
        modelAndView.addObject("product",products);
        return modelAndView;
    }

    @GetMapping("/sortByCategory")
    public ModelAndView sortByCategory(@RequestParam Long id){
        ModelAndView modelAndView = new ModelAndView("/searchByC");
        Iterable<Product> products= productService.findAllByCategory_Id(id);
        modelAndView.addObject("product",products);
        return modelAndView;
    }














}
