package com.example.tryhart11.controller;
import com.example.tryhart11.model.House;
import com.example.tryhart11.service.IHouseService;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.JsonPath;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/house")
public class HouseController {
    @Autowired
    private IHouseService iHouseService;

    @GetMapping("") /// Hiển thị
    public ResponseEntity<Page<House>> fillAll(@PageableDefault(value = 3)Pageable pageable){
        return new ResponseEntity<>(iHouseService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping("") /////Create
    public ResponseEntity<House> create ( @RequestParam("file") MultipartFile file ,@Valid House house){
        String fileName = file.getOriginalFilename();
        house.setImage(fileName);
        try{
            file.transferTo(new File("E:\\javascrip\\DemoAjaxx\\image\\" +fileName));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        iHouseService.save(house);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")         //Tìm theo id
    public ResponseEntity<House> findProductById(@PathVariable Long id) {
        Optional<House> house = iHouseService.findById(id);
        if (!house.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(house.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")           //Chỉnh sửa theo id
    public ResponseEntity  updateProduct(@RequestParam("file") MultipartFile file , House house, @PathVariable Long id) {
        String fileName = file.getOriginalFilename();
        if (fileName.equals("")){
            house.setImage(iHouseService.findById(id).get().getImage());
            try{
                file.transferTo(new File("E:\\javascrip\\DemoAjaxx\\image\\" + house.getImage()));
            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        else {
            house.setImage(fileName);
            try {
                file.transferTo(new File("E:\\javascrip\\DemoAjaxx\\image\\" + fileName));
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        house.setId(id);
        return new ResponseEntity<>(iHouseService.save(house), HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<House> fileUpLoad(@RequestParam("file") MultipartFile file , House house){
        String fileName = file.getOriginalFilename();
        house.setImage(fileName);
        try{
            file.transferTo(new File("E:\\javascrip\\DemoAjaxx\\image\\" +fileName));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(house);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<House> delete(@PathVariable("id") Long id){
        iHouseService.remote(id);
        return new ResponseEntity<>(HttpStatus.OK);
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

}
