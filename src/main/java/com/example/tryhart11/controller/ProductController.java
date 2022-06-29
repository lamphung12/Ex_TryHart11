package com.example.tryhart11.controller;

import com.example.tryhart11.model.Category;
import com.example.tryhart11.model.Post;
import com.example.tryhart11.model.Product;
import com.example.tryhart11.service.category.CategoryService;
import com.example.tryhart11.service.category.ICategoryService;
import com.example.tryhart11.service.product.IProductService;
import com.example.tryhart11.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class ProductController {

    @Autowired
        private ProductService productService;
    @Autowired
        private CategoryService categoryService;

    @GetMapping("/category") /// Hiển thị category
    public ResponseEntity <Iterable<Category>> findAllCategory( ){
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/products") /// Hiển thị
    public ResponseEntity <Iterable<Product>> fillAll( ){
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/products/oder-by-price")////sắp xếp theo giá
    public ResponseEntity <Iterable<Product>> fillAllByOrderByPrice(){
        return new ResponseEntity<>(productService.findAllByOrderByPrice(), HttpStatus.OK);
    }

    @GetMapping("/products/price-between")  //// tìm sản phẩm trong khoảng giá
    public ResponseEntity <Iterable<Product>> fillAllByPriceBetween(@RequestParam("one") int one,@RequestParam("two")int two  ){
        return new ResponseEntity<>(productService.findAllByPriceBetween(one, two), HttpStatus.OK);
    }

    @GetMapping("/products/top4") ////tìm top 4 sản phẩm theo
    public ResponseEntity <Iterable<Product>> fillAllByTop4(){
        return new ResponseEntity<>(productService.findTop4New(), HttpStatus.OK);
    }

    @GetMapping("/products/findByCategory") ////tìm theo danh mục sản phẩm
    public ResponseEntity <Iterable<Product>> fillAllByCategory_Name(@RequestParam("name") String name){
        return new ResponseEntity<>(productService.findAllByCategory_Name(name), HttpStatus.OK);
    }
    @GetMapping("/products/findByNameContaining") ////tìm theo tên gần đúng
    public ResponseEntity <Iterable<Product>> fillAllByNamContaining(@RequestParam("name") String name){
        return new ResponseEntity<>(productService.findAllByNameContaining(name), HttpStatus.OK);
    }

    @PostMapping("/products") /////Create
    public ResponseEntity create(@Valid @RequestBody Product product){
        productService.save(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/products/{id}")         //Tìm theo id
    public ResponseEntity<Product> findProductById(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (!product.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }

    @PutMapping("/products/{id}")           //Chỉnh sửa theo id
    public ResponseEntity updateProduct(@RequestBody Product product, @PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        product.setId(productOptional.get().getId());
        productService.save(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")              //Xóa theo id
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.remote(id);
        return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
    }
    @GetMapping("/products/searchById/{id}")
    public ResponseEntity<Product> search(@PathVariable Long id , Pageable pageable) {
        Page<Product> productIterable = productService.findAllByCategory_Id(id ,pageable);
        return new ResponseEntity(productIterable, HttpStatus.OK);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @PostMapping("/upload")
    public ResponseEntity<Post> fileUpLoad(@RequestParam("file") MultipartFile file , Post post){
        String fileName = file.getOriginalFilename();
        post.setImage(fileName);
        try{
            file.transferTo(new File("E:\\javascrip\\DemoAjaxx" +fileName));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(post);
    }




}






