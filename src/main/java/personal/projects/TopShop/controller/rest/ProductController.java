package personal.projects.TopShop.controller.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import personal.projects.TopShop.domaine.product.CreateProductRequest;
import personal.projects.TopShop.domaine.product.ProductVo;
import personal.projects.TopShop.domaine.product.SearchProductsRequest;
import personal.projects.TopShop.domaine.product.UpdateProductRequest;
import personal.projects.TopShop.service.IImageService;
import personal.projects.TopShop.service.IProductService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {

    private IProductService productService;
    private IImageService imageService;





    @PostMapping("/search")
    public List<ProductVo> searchProducts(@RequestBody SearchProductsRequest request){

        return productService.searchProducts(request);

    }




    @GetMapping("/all/{username}")
    public ResponseEntity<List<ProductVo>> getAllByUsername(@PathVariable String username){

        return new ResponseEntity<>(
                productService.getAllByUsername(username),
                HttpStatus.OK
        );
    }




    @GetMapping("/{label}")
    public ResponseEntity<ProductVo> getProductByLabel(@PathVariable("label") String label){

        return new ResponseEntity<>(
                productService.findByLabel(label),
                HttpStatus.OK
        );
    }




    @GetMapping("/byId/{id}")
    public ResponseEntity<ProductVo> getProductById(@PathVariable("id") Long id){

        return new ResponseEntity<>(
                productService.findById(id),
                HttpStatus.OK
        );
    }
 




    @GetMapping("/addView/{id}")
    public ResponseEntity<String> addView(@PathVariable("id") Long id){

        return new ResponseEntity<>(
                productService.addView(id),
                HttpStatus.OK
        );
    }






    @GetMapping("/image/{name}")
    public ResponseEntity<byte[]> getImageByName(@PathVariable("name") String name){

        return new ResponseEntity<>(
                imageService.getImageByName(name),
                HttpStatus.OK
        );
    }






    @PreAuthorize("hasAnyRole('CLIENT', 'ADMIN')")
    @PostMapping(value = "/save",
            consumes = {
                MediaType.MULTIPART_FORM_DATA_VALUE,
                MediaType.APPLICATION_JSON_VALUE},
            produces = {
                MediaType.MULTIPART_FORM_DATA_VALUE,
                MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<String> saveProduct(@ModelAttribute CreateProductRequest request) throws IOException {

//        System.out.println(request);
        return new ResponseEntity<>(
                productService.save(request),
                HttpStatus.CREATED
        );
    }






    @PreAuthorize("hasAnyRole('CLIENT', 'ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id){

        return new ResponseEntity<>(
                productService.delete(id),
                HttpStatus.OK
        );
    }






    @PreAuthorize("hasAnyRole('CLIENT', 'ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<ProductVo> updateProduct(@PathVariable("id") Long id, @RequestBody UpdateProductRequest request){

        return new ResponseEntity<>(
                productService.update(id, request),
                HttpStatus.OK
        );
    }



}
