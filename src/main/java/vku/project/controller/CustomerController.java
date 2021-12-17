package vku.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vku.project.dto.DtoCustomer;
import vku.project.entity.Customers;
import vku.project.service.CustomerService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/customer")
@CrossOrigin("http://localhost:4200")
public class CustomerController {
    @Autowired
    private CustomerService customerService;


    @GetMapping("/list")
    public ResponseEntity<Page<Customers>> listCustomer(@PageableDefault(size = 5)Pageable pageable){
        Page<Customers> customersPage = customerService.finAll(pageable);
        if(customersPage == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customersPage, HttpStatus.OK);
    }
    @GetMapping("/getInformation/{id}")
    public ResponseEntity<Customers> getInforCustomer(@PathVariable String id){
        Customers customers = customerService.findById(id);
        if (customers == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Customers> createCustomer(@Valid @RequestBody DtoCustomer customers, BindingResult bindingResult){
        if (!bindingResult.hasFieldErrors()){
            customerService.saveCustomer(customers);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    @PutMapping("/edit")
    public ResponseEntity<Customers> editCustomer(@Valid @RequestBody DtoCustomer customers,BindingResult bindingResult){

        if (!bindingResult.hasFieldErrors()){
            this.customerService.updateCustomer(customers);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Customers> deleteCustomer(@PathVariable String id){
        Customers customers = customerService.findById(id);
        if (customers == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customerService.delete(customers);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/search")
    public ResponseEntity<Page<Customers>> searchCustomer(@RequestParam ("key")String name, @PageableDefault(size = 5) Pageable pageable){
        Page<Customers> customersPage = this.customerService.searchCustomer(name,pageable);
        if (customersPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customersPage,HttpStatus.OK);
    }
}
