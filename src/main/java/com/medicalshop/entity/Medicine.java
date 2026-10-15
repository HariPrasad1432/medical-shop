//package com.medicalshop.entity;
//import jakarta.persistence.*;
//import lombok.*;
//@Entity @Getter @Setter @NoArgsConstructor
//public class Medicine {
//    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
//    @Column(nullable=false) private String name;
//    private String category;
//    private String manufacturer;
//    private Integer reorderLevel = 10;
//}
package com.medicalshop.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity @Getter @Setter @NoArgsConstructor
public class Medicine {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false) private String name;
    private String category;
    private String manufacturer;
    private Integer reorderLevel = 10;
}
