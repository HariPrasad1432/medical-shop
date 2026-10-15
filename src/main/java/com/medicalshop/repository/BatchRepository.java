//package com.medicalshop.repository;
//import com.medicalshop.entity.MedicineBatch;
//import org.springframework.data.jpa.repository.*;
//import org.springframework.data.repository.query.Param;
//import java.time.LocalDate;
//import java.util.*;
//public interface BatchRepository extends JpaRepository<MedicineBatch,Long>{
//    @Query("select b from MedicineBatch b where b.quantity > 0 and b.expiryDate <= :date order by b.expiryDate")
//    List<MedicineBatch> expiring(@Param("date") LocalDate date);
//    @Query("select b from MedicineBatch b where b.quantity <= b.medicine.reorderLevel")
//    List<MedicineBatch> lowStock();
//    List<MedicineBatch> findByMedicineIdOrderByExpiryDateAsc(Long medicineId);
//}
package com.medicalshop.repository;
import com.medicalshop.entity.MedicineBatch;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.*;
public interface BatchRepository extends JpaRepository<MedicineBatch,Long>{
    @Query("select b from MedicineBatch b where b.quantity > 0 and b.expiryDate <= :date order by b.expiryDate")
    List<MedicineBatch> expiring(@Param("date") LocalDate date);
    @Query("select b from MedicineBatch b where b.quantity <= b.medicine.reorderLevel")
    List<MedicineBatch> lowStock();
    List<MedicineBatch> findByMedicineIdOrderByExpiryDateAsc(Long medicineId);
}

