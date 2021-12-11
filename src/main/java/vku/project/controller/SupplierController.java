package vku.project.controller;

import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vku.project.entity.Suppliers;
import vku.project.service.SupplierService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/suppliers")
@CrossOrigin("http://localhost:4200")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;
    @GetMapping("/list")
    public ResponseEntity<List<Suppliers>> listSupplier(){
        List<Suppliers> suppliers = this.supplierService.finAll();
        if (suppliers == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(suppliers,HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Suppliers> createSuppliers(@Valid @RequestBody Suppliers suppliers, BindingResult bindingResult){
        if (!bindingResult.hasFieldErrors()){
            this.supplierService.saveSuppliers(suppliers);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

    }
    @PutMapping("/edit")
    public ResponseEntity<Suppliers> editSupplier(@Valid @RequestBody Suppliers suppliers,
                                                  BindingResult bindingResult,@PathVariable int id){

        Suppliers suppliers1 = this.supplierService.findById(id);
        if (!bindingResult.hasFieldErrors()){
        if (suppliers1==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        suppliers1.setSuppliersName(suppliers.getSuppliersName());
        suppliers1.setPhone(suppliers.getPhone());
        suppliers1.setAddress(suppliers.getAddress());
        supplierService.saveSuppliers(suppliers1);
        return new ResponseEntity<>(suppliers1,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Suppliers> deleteSupplier(@PathVariable int id){
        Suppliers suppliers = supplierService.findById(id);
        if (suppliers == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        supplierService.delete(suppliers);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
