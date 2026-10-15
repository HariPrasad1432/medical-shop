//package com.medicalshop.controller;
//import com.medicalshop.entity.Supplier; import com.medicalshop.repository.SupplierRepository; import lombok.RequiredArgsConstructor; import org.springframework.web.bind.annotation.*; import java.util.*;
//@RestController @RequestMapping("/api/suppliers") @RequiredArgsConstructor public class SupplierController{
// private final SupplierRepository r; @GetMapping public List<Supplier> all(){return r.findAll();} @PostMapping public Supplier add(@RequestBody Supplier s){return r.save(s);}
//}
package com.medicalshop.controller;
import com.medicalshop.entity.Supplier; import com.medicalshop.repository.SupplierRepository; import lombok.RequiredArgsConstructor; import org.springframework.web.bind.annotation.*; import org.springframework.security.access.prepost.PreAuthorize; import java.util.*;
@RestController @RequestMapping("/api/suppliers") @RequiredArgsConstructor public class SupplierController{
 private final SupplierRepository r; @GetMapping @PreAuthorize("hasRole('ADMIN')") public List<Supplier> all(){return r.findAll();} @PostMapping @PreAuthorize("hasRole('ADMIN')") public Supplier add(@RequestBody Supplier s){return r.save(s);}
}
