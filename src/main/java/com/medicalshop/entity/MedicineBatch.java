//package com.medicalshop.entity;
//import jakarta.persistence.*;
//import lombok.*;
//import java.math.BigDecimal;
//import java.time.LocalDate;
//@Entity @Getter @Setter @NoArgsConstructor
//@Table(name="medicine_batches")
//public class MedicineBatch {
//    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
//    @ManyToOne(optional=false) private Medicine medicine;
//    @Column(nullable=false) private String batchNumber;
//    @Column(nullable=false) private Integer quantity;
//    @Column(nullable=false, precision=12, scale=2) private BigDecimal purchasePrice;
//    @Column(nullable=false, precision=12, scale=2) private BigDecimal sellingPrice;
//    private LocalDate manufacturingDate;
//    @Column(nullable=false) private LocalDate expiryDate;
//}
package com.medicalshop.entity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
@Entity @Getter @Setter @NoArgsConstructor
@Table(name="medicine_batches")
public class MedicineBatch {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(optional=false) private Medicine medicine;
    @Column(nullable=false) private String batchNumber;
    @Column(nullable=false) private Integer quantity;
    @Column(nullable=false, precision=12, scale=2) private BigDecimal purchasePrice;
    @Column(nullable=false, precision=12, scale=2) private BigDecimal sellingPrice;
    private LocalDate manufacturingDate;
    @Column(nullable=false) private LocalDate expiryDate;
}
