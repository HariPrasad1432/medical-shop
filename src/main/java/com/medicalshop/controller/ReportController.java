//package com.medicalshop.controller;
//import com.medicalshop.repository.SaleRepository; import lombok.RequiredArgsConstructor; import org.springframework.web.bind.annotation.*; import java.math.*; import java.time.*; import java.util.*;
//@RestController @RequestMapping("/api/reports") @RequiredArgsConstructor public class ReportController{
// private final SaleRepository sales;
// @GetMapping("/monthly") public Map<String,Object> monthly(@RequestParam int year,@RequestParam int month){
//  LocalDate a=LocalDate.of(year,month,1),b=a.plusMonths(1);var list=sales.findBySaleDateBetween(a.atStartOfDay(),b.atStartOfDay());
//  BigDecimal revenue=list.stream().map(x->x.getTotalAmount()).filter(Objects::nonNull).reduce(BigDecimal.ZERO,BigDecimal::add);
//  int items=list.stream().flatMap(x->x.getItems().stream()).mapToInt(i->i.getQuantity()).sum();
//  return Map.of("year",year,"month",month,"invoiceCount",list.size(),"itemsSold",items,"revenue",revenue);
// }
//}
package com.medicalshop.controller;
import com.medicalshop.repository.SaleRepository; import lombok.RequiredArgsConstructor; import org.springframework.web.bind.annotation.*; import org.springframework.security.access.prepost.PreAuthorize; import java.math.*; import java.time.*; import java.util.*;
@RestController @RequestMapping("/api/reports") @RequiredArgsConstructor public class ReportController{
 private final SaleRepository sales;
 @GetMapping("/monthly") @PreAuthorize("hasRole('ADMIN')") public Map<String,Object> monthly(@RequestParam int year,@RequestParam int month){
  LocalDate a=LocalDate.of(year,month,1),b=a.plusMonths(1);var list=sales.findBySaleDateBetween(a.atStartOfDay(),b.atStartOfDay());
  BigDecimal revenue=list.stream().map(x->x.getTotalAmount()).filter(Objects::nonNull).reduce(BigDecimal.ZERO,BigDecimal::add);
  int items=list.stream().flatMap(x->x.getItems().stream()).mapToInt(i->i.getQuantity()).sum();
  return Map.of("year",year,"month",month,"invoiceCount",list.size(),"itemsSold",items,"revenue",revenue);
 }
}
