package com.Project.SpringBootProject.Contoller;

import com.Project.SpringBootProject.Model.Product;
import com.Project.SpringBootProject.Services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin // to avoid cors error(different ports of backend and front end)
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductServices service;

    //we should use ResponseEntity to return status code along with data. In this way we give more power to front end how it wants to show data if something goes wrong.
//    @GetMapping("/products")
//    public List <Product> getProducts(){
//
//        return service.getAllProducts();
//    }
// instead of this ,we should use this.

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }

//    @PostMapping("/products")
//    public void addProduct(@RequestBody Product prod){
//        System.out.println(prod);
//        service.addProduct(prod);
//    }
    //instead of this, we should do this
    @CrossOrigin
    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile){
        // ? in ResponseEntity represents that  we can return any type of data or we can return only status code
        // @RequestPart accepts only a part of the object while @RequestBody accepts whole object

        try{
            System.out.println(product);
            Product product1 = service.addProduct(product,imageFile);
            return new ResponseEntity<>(product1,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getProductImageById(@PathVariable int productId){
        Product product = service.getProductById(productId);
        byte [] imageFile = product.getImageDate();
        return ResponseEntity.ok().contentType(MediaType.valueOf(product.getImageType())).body(imageFile);
    }

//    @GetMapping("/product/{id}")
//    public Product getProductById(@PathVariable int id){
//        return service.getProductById(id);
//    }
    // instead of this ,we should use this.
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id){
        Product product = service.getProductById(id);
        if (product!=null)
            return new ResponseEntity<>(product,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND );
    }

    //updating product
    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id,@RequestPart Product product, @RequestPart MultipartFile imageFile){
        Product product1 = null;
        try {
            product1 = service.updateProduct(id,product,imageFile);
        } catch (IOException e) {
            return new ResponseEntity<>("failed to update",HttpStatus.BAD_REQUEST);
        }
        if (product1 != null)
            return new ResponseEntity<>("Updated",HttpStatus.OK);
        else
            return new ResponseEntity<>("failed to update",HttpStatus.BAD_REQUEST);
    }

    //deleting product
    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){

        Product product = service.getProductById(id);
        if (product!= null){
            service.deleteProduct(id);
            return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Product Not Found",HttpStatus.NOT_FOUND);
        }
    }
    //Mapping for search bar
    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword){
        System.out.println("searching with"+keyword);
        List<Product> products=service.searchProducts(keyword);
        return new ResponseEntity<>(products,HttpStatus.OK);
    }


}
