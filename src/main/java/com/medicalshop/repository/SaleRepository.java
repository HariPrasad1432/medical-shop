//package com.medicalshop.repository;
//import com.medicalshop.entity.Sale;
//import org.springframework.data.jpa.repository.*;
//import java.time.LocalDateTime;
//import java.util.*;
//public interface SaleRepository extends JpaRepository<Sale,Long>{
//    List<Sale> findBySaleDateBetween(LocalDateTime from, LocalDateTime to);
//}
package com.medicalshop.repository;
import com.medicalshop.entity.Sale;
import org.springframework.data.jpa.repository.*;
import java.time.LocalDateTime;
import java.util.*;
public interface SaleRepository extends JpaRepository<Sale,Long>{
    List<Sale> findBySaleDateBetween(LocalDateTime from, LocalDateTime to);
    List<Sale> findBySoldByUsernameOrderBySaleDateDesc(String username);
}
