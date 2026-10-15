package com.medicalshop.controller;
//import com.medicalshop.entity.*;
//import com.medicalshop.service.MedicalShopService;
//
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//import java.util.*;
//
//@RestController @RequestMapping("/api") @RequiredArgsConstructor
//@CrossOrigin(origins="http://localhost:65435")
//@Validated
//public class MedicalShopController {
//    private final MedicalShopService service;
//
//    @GetMapping("/medicines") public List<Medicine> medicines(){return service.medicines();}
//    @PostMapping("/medicines") public Medicine addMedicine(@RequestBody @Valid Medicine m){return service.addMedicine(m);}
//
//    @GetMapping("/batches") public List<MedicineBatch> batches(){return service.batches();}
//    @PostMapping("/batches") public MedicineBatch addBatch(@RequestBody MedicineBatch b){return service.addBatch(b);}
//
//    @GetMapping("/inventory/expiring") public List<MedicineBatch> expiring(@RequestParam(defaultValue="30") int days){return service.expiring(days);}
//    @GetMapping("/inventory/low-stock") public List<MedicineBatch> lowStock(){return service.lowStock();}
//
//    @PostMapping("/sales")
//    public Sale sell(@RequestBody Map<String,Object> req){
//        Long batchId=Long.valueOf(req.get("batchId").toString());
//        Integer qty=Integer.valueOf(req.get("quantity").toString());
//        return service.sell(batchId,qty,(String)req.getOrDefault("customerName","Walk-in"));
//    }
//
//    @GetMapping("/dashboard") public Map<String,Object> dashboard(){return service.dashboard();}
//}
import com.medicalshop.entity.*;
import com.medicalshop.service.MedicalShopService;
import jakarta.validation.Valid; import org.springframework.security.access.prepost.PreAuthorize; import org.springframework.security.core.Authentication;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController @RequestMapping("/api") @RequiredArgsConstructor
@CrossOrigin(origins="http://localhost:4200")
public class MedicalShopController {
    private final MedicalShopService service;

    @GetMapping("/medicines") @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST')") public List<Medicine> medicines(){return service.medicines();}
    @PostMapping("/medicines") @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST')") public Medicine addMedicine(@RequestBody @Valid Medicine m){return service.addMedicine(m);}

    @GetMapping("/batches") @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST')") public List<MedicineBatch> batches(){return service.batches();}
    @PostMapping("/batches") @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST')") public MedicineBatch addBatch(@RequestBody MedicineBatch b){return service.addBatch(b);}

    @GetMapping("/inventory/expiring") @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST')") public List<MedicineBatch> expiring(@RequestParam(defaultValue="30") int days){return service.expiring(days);}
    @GetMapping("/inventory/low-stock") @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST')") public List<MedicineBatch> lowStock(){return service.lowStock();}

    @PostMapping("/sales") @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST')")
    public Sale sell(@RequestBody Map<String,Object> req, Authentication auth){
        Long batchId=Long.valueOf(req.get("batchId").toString());
        Integer qty=Integer.valueOf(req.get("quantity").toString());
        return service.sell(batchId,qty,(String)req.getOrDefault("customerName","Walk-in"),auth.getName());
    }

    @GetMapping("/dashboard") @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST')") public Map<String,Object> dashboard(){return service.dashboard();}

    @GetMapping("/sales/my-sales") @PreAuthorize("hasRole('PHARMACIST')")
    public List<Sale> mySales(Authentication auth){return service.mySales(auth.getName());}
}
