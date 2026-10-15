//package com.medicalshop.controller;
//import com.medicalshop.entity.*; import com.medicalshop.repository.*; import lombok.RequiredArgsConstructor; import org.springframework.web.bind.annotation.*; import java.time.*; import java.math.*; import java.util.*;
//@RestController @RequestMapping("/api/purchases") @RequiredArgsConstructor public class PurchaseController{
// private final PurchaseRepository p; private final SupplierRepository s;
// @GetMapping public List<Purchase> all(){return p.findAll();}
// @PostMapping public Purchase add(@RequestBody Map<String,Object> x){Purchase q=new Purchase();q.setSupplier(s.findById(Long.valueOf(x.get("supplierId").toString())).orElseThrow());q.setPurchaseDate(LocalDateTime.now());q.setTotalAmount(new BigDecimal(x.get("totalAmount").toString()));return p.save(q);}
//}
package com.medicalshop.controller;
import com.medicalshop.entity.*; import com.medicalshop.repository.*; import lombok.RequiredArgsConstructor; import org.springframework.web.bind.annotation.*; import org.springframework.security.access.prepost.PreAuthorize; import java.time.*; import java.math.*; import java.util.*;
@RestController @RequestMapping("/api/purchases") @RequiredArgsConstructor public class PurchaseController{
 private final PurchaseRepository p; private final SupplierRepository s;
 @GetMapping @PreAuthorize("hasRole('ADMIN')") public List<Purchase> all(){return p.findAll();}
 @PostMapping @PreAuthorize("hasRole('ADMIN')") public Purchase add(@RequestBody Map<String,Object> x){Purchase q=new Purchase();q.setSupplier(s.findById(Long.valueOf(x.get("supplierId").toString())).orElseThrow());q.setPurchaseDate(LocalDateTime.now());q.setTotalAmount(new BigDecimal(x.get("totalAmount").toString()));return p.save(q);}
}
