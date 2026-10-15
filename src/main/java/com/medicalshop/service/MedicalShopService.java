//package com.medicalshop.service;
//import com.medicalshop.entity.*;
//import com.medicalshop.repository.*;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import java.math.BigDecimal;
//import java.time.*;
//import java.util.*;
//
//@Service @RequiredArgsConstructor
//public class MedicalShopService {
//    private final MedicineRepository medicineRepo;
//    private final BatchRepository batchRepo;
//    private final SaleRepository saleRepo;
//
//    public List<Medicine> medicines(){ return medicineRepo.findAll(); }
//    public Medicine addMedicine(Medicine m){ return medicineRepo.save(m); }
//    public List<MedicineBatch> batches(){ return batchRepo.findAll(); }
//    public MedicineBatch addBatch(MedicineBatch b){
//        if(b.getMedicine()==null || b.getMedicine().getId()==null) throw new IllegalArgumentException("medicine.id is required");
//        b.setMedicine(medicineRepo.findById(b.getMedicine().getId()).orElseThrow());
//        return batchRepo.save(b);
//    }
//    public List<MedicineBatch> expiring(int days){
//        return batchRepo.expiring(LocalDate.now().plusDays(days));
//    }
//    public List<MedicineBatch> lowStock(){ return batchRepo.lowStock(); }
//
//    @Transactional
//    public Sale sell(Long batchId, Integer qty, String customer){
//        if(qty==null || qty<=0) throw new IllegalArgumentException("Quantity must be greater than zero");
//        MedicineBatch b=batchRepo.findById(batchId).orElseThrow();
//        if(b.getExpiryDate().isBefore(LocalDate.now())) throw new IllegalArgumentException("Cannot sell expired medicine");
//        if(b.getQuantity()<qty) throw new IllegalArgumentException("Insufficient stock");
//        b.setQuantity(b.getQuantity()-qty);
//        batchRepo.save(b);
//
//        Sale s=new Sale();
//        s.setInvoiceNumber("INV-"+System.currentTimeMillis());
//        s.setCustomerName(customer);
//        s.setSaleDate(LocalDateTime.now());
//        s.setTotalAmount(b.getSellingPrice().multiply(BigDecimal.valueOf(qty)));
//        SaleItem item=new SaleItem();
//        item.setSale(s); item.setBatch(b); item.setQuantity(qty);
//        item.setUnitPrice(b.getSellingPrice()); item.setTotalPrice(s.getTotalAmount());
//        s.getItems().add(item);
//        return saleRepo.save(s);
//    }
//
//    public Map<String,Object> dashboard(){
//        LocalDateTime start=LocalDate.now().atStartOfDay(), end=start.plusDays(1);
//        List<Sale> today=saleRepo.findBySaleDateBetween(start,end);
//        BigDecimal revenue=today.stream().map(Sale::getTotalAmount).filter(Objects::nonNull)
//                .reduce(BigDecimal.ZERO,BigDecimal::add);
//        int sold=today.stream().flatMap(s->s.getItems().stream()).mapToInt(SaleItem::getQuantity).sum();
//        Map<String,Object> m=new LinkedHashMap<>();
//        m.put("todayRevenue",revenue); m.put("medicinesSoldToday",sold);
//        m.put("lowStockCount",lowStock().size());
//        m.put("expiringWithin30Days",expiring(30).size());
//        m.put("expiredCount",batchRepo.findAll().stream().filter(b->b.getExpiryDate().isBefore(LocalDate.now())).count());
//        return m;
//    }
//}
package com.medicalshop.service;
import com.medicalshop.entity.*;
import com.medicalshop.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;

@Service @RequiredArgsConstructor
public class MedicalShopService {
    private final MedicineRepository medicineRepo;
    private final BatchRepository batchRepo;
    private final SaleRepository saleRepo;
    private final UserRepository userRepo;

    public List<Medicine> medicines(){ return medicineRepo.findAll(); }
    public Medicine addMedicine(Medicine m){ return medicineRepo.save(m); }
    public List<MedicineBatch> batches(){ return batchRepo.findAll(); }
    public MedicineBatch addBatch(MedicineBatch b){
        if(b.getMedicine()==null || b.getMedicine().getId()==null) throw new IllegalArgumentException("medicine.id is required");
        b.setMedicine(medicineRepo.findById(b.getMedicine().getId()).orElseThrow());
        return batchRepo.save(b);
    }
    public List<MedicineBatch> expiring(int days){
        return batchRepo.expiring(LocalDate.now().plusDays(days));
    }
    public List<MedicineBatch> lowStock(){ return batchRepo.lowStock(); }

    @Transactional
    public Sale sell(Long batchId, Integer qty, String customer, String username){
        if(qty==null || qty<=0) throw new IllegalArgumentException("Quantity must be greater than zero");
        MedicineBatch b=batchRepo.findById(batchId).orElseThrow();
        if(b.getExpiryDate().isBefore(LocalDate.now())) throw new IllegalArgumentException("Cannot sell expired medicine");
        if(b.getQuantity()<qty) throw new IllegalArgumentException("Insufficient stock");
        b.setQuantity(b.getQuantity()-qty);
        batchRepo.save(b);

        Sale s=new Sale();
        s.setInvoiceNumber("INV-"+System.currentTimeMillis());
        s.setCustomerName(customer);
        s.setSaleDate(LocalDateTime.now());
        s.setTotalAmount(b.getSellingPrice().multiply(BigDecimal.valueOf(qty)));
        s.setSoldBy(userRepo.findByUsername(username).orElseThrow());
        SaleItem item=new SaleItem();
        item.setSale(s); item.setBatch(b); item.setQuantity(qty);
        item.setUnitPrice(b.getSellingPrice()); item.setTotalPrice(s.getTotalAmount());
        s.getItems().add(item);
        return saleRepo.save(s);
    }

    public List<Sale> mySales(String username){ return saleRepo.findBySoldByUsernameOrderBySaleDateDesc(username); }

    public Map<String,Object> dashboard(){
        LocalDateTime start=LocalDate.now().atStartOfDay(), end=start.plusDays(1);
        List<Sale> today=saleRepo.findBySaleDateBetween(start,end);
        BigDecimal revenue=today.stream().map(Sale::getTotalAmount).filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO,BigDecimal::add);
        int sold=today.stream().flatMap(s->s.getItems().stream()).mapToInt(SaleItem::getQuantity).sum();
        Map<String,Object> m=new LinkedHashMap<>();
        m.put("todayRevenue",revenue); m.put("medicinesSoldToday",sold);
        m.put("lowStockCount",lowStock().size());
        m.put("expiringWithin30Days",expiring(30).size());
        m.put("expiredCount",batchRepo.findAll().stream().filter(b->b.getExpiryDate().isBefore(LocalDate.now())).count());
        return m;
    }
}
