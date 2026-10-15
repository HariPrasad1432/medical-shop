//package com.medicalshop.entity;
//import jakarta.persistence.*;
//import lombok.*;
//import java.math.BigDecimal;
//@Entity @Getter @Setter @NoArgsConstructor
//@Table(name="sale_items")
//public class SaleItem {
//    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
//    @ManyToOne(optional=false) private Sale sale;
//    @ManyToOne(optional=false) private MedicineBatch batch;
//    private Integer quantity;
//    @Column(precision=12, scale=2) private BigDecimal unitPrice;
//    @Column(precision=12, scale=2) private BigDecimal totalPrice;
//}
package com.medicalshop.entity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
@Entity @Getter @Setter @NoArgsConstructor
@Table(name="sale_items")
public class SaleItem {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(optional=false) private Sale sale;
    @ManyToOne(optional=false) private MedicineBatch batch;
    private Integer quantity;
    @Column(precision=12, scale=2) private BigDecimal unitPrice;
    @Column(precision=12, scale=2) private BigDecimal totalPrice;
}
