package personal.projects.TopShop.controller.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import personal.projects.TopShop.domaine.cart.CreateLineProductRequest;
import personal.projects.TopShop.domaine.cart.LineProductVo;
import personal.projects.TopShop.domaine.cart.UpdateLineProductRequest;
import personal.projects.TopShop.service.LineProductServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@AllArgsConstructor
public class LineProductController {

    private LineProductServiceImpl lineProductService;


    @PreAuthorize("hasAnyRole('CLIENT', 'ADMIN', 'SUPPORT')")
    @GetMapping
    public ResponseEntity<List<LineProductVo>> getAllByUsername(@RequestParam(name = "username") String username){

        return new ResponseEntity<>(lineProductService.getAllByUsername(username), HttpStatus.OK);
    }


    @GetMapping("/lp/{id}")
    public ResponseEntity<LineProductVo> getById(@PathVariable Long id){

        return new ResponseEntity<>(lineProductService.getById(id), HttpStatus.OK);
    }



    @PreAuthorize("hasAnyRole('CLIENT', 'ADMIN', 'SUPPORT')")
    @PostMapping("/add")
    public ResponseEntity<String> save(@RequestBody CreateLineProductRequest request){

        return new ResponseEntity<>(lineProductService.saveLP(request), HttpStatus.CREATED);
    }


    @PreAuthorize("hasAnyRole('CLIENT', 'ADMIN', 'SUPPORT')")
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@RequestBody UpdateLineProductRequest request, @PathVariable("id") Long id){

        return new ResponseEntity<>(lineProductService.updateLP(request, id), HttpStatus.OK);
    }


    @PreAuthorize("hasAnyRole('CLIENT', 'ADMIN', 'SUPPORT')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){

        return new ResponseEntity<>(lineProductService.deleteLP(id), HttpStatus.OK);
    }


}
