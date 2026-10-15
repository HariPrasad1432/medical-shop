//package com.medicalshop.entity;
//import jakarta.persistence.*;
//import lombok.*;
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.*;
//@Entity @Getter @Setter @NoArgsConstructor
//@Table(name="sales")
//public class Sale {
//    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
//    @Column(nullable=false, unique=true) private String invoiceNumber;
//    private String customerName;
//    private LocalDateTime saleDate;
//    @Column(precision=12, scale=2) private BigDecimal totalAmount;
//    @OneToMany(mappedBy="sale", cascade=CascadeType.ALL, orphanRemoval=true)
//    private List<SaleItem> items = new ArrayList<>();
//}
package com.medicalshop.entity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity @Getter @Setter @NoArgsConstructor
@Table(name="sales")
public class Sale {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false, unique=true) private String invoiceNumber;
    private String customerName;
    private LocalDateTime saleDate;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sold_by_id")
    private User soldBy;
    @Column(precision=12, scale=2) private BigDecimal totalAmount;
    @OneToMany(mappedBy="sale", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<SaleItem> items = new ArrayList<>();
	
}
