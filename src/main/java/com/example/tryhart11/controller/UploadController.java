package com.example.tryhart11.controller;

import com.example.tryhart11.model.Post;
import com.example.tryhart11.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
@RestController
@CrossOrigin("*")
public class UploadController {
    @PostMapping("/upload")
    public ResponseEntity<Post> fileUpLoad(@RequestParam("file") MultipartFile file , Post post){
        String fileName = file.getOriginalFilename();
        post.setImage(fileName);
        try{
            file.transferTo(new File("E:\\javascrip\\DemoAjaxx\\image\\" +fileName));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(post);
    }
}
