//package com.medicalshop.entity;
//import jakarta.persistence.*; import lombok.*; import java.math.BigDecimal; import java.time.LocalDateTime;
//@Entity @Getter @Setter @NoArgsConstructor public class Purchase{
// @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id; @ManyToOne(optional=false) Supplier supplier; LocalDateTime purchaseDate; BigDecimal totalAmount;
//}
package com.medicalshop.entity;
import jakarta.persistence.*; import lombok.*; import java.math.BigDecimal; import java.time.LocalDateTime;
@Entity @Getter @Setter @NoArgsConstructor public class Purchase{
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id; @ManyToOne(optional=false) Supplier supplier; LocalDateTime purchaseDate; BigDecimal totalAmount;
}
