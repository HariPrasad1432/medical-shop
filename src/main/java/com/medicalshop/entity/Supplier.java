//package com.medicalshop.entity;
//import jakarta.persistence.*; import lombok.*;
//@Entity @Getter @Setter @NoArgsConstructor public class Supplier{
// @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id; @Column(nullable=false) String name; String phone; String email; String address;
//}
package com.medicalshop.entity;
import jakarta.persistence.*; import lombok.*;
@Entity @Getter @Setter @NoArgsConstructor public class Supplier{
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id; @Column(nullable=false) String name; String phone; String email; String address;
}
