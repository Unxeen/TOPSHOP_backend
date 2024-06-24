package personal.projects.TopShop.controller.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import personal.projects.TopShop.domaine.category.CategoryVo;
import personal.projects.TopShop.service.ICategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@AllArgsConstructor
public class CategoryController {

    private ICategoryService categoryService;


    @GetMapping("/all")
    public ResponseEntity<List<CategoryVo>> getAllCategories(){

        return new ResponseEntity<>(categoryService.getAll(), HttpStatus.OK);
    }


    @GetMapping("/{name}")
    public ResponseEntity<CategoryVo> getCategoryByName(@PathVariable("name") String name){

        return new ResponseEntity<>(categoryService.getByName(name), HttpStatus.OK);
    }


    @PostMapping("/save/{name}")
    public ResponseEntity<String> saveCategory(@PathVariable("name") String name){

        return new ResponseEntity<>(categoryService.saveCategory(name), HttpStatus.CREATED);
    }

}
