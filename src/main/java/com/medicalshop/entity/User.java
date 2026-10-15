//package com.medicalshop.entity;
//import jakarta.persistence.*; import lombok.*;
//@Entity @Getter @Setter @NoArgsConstructor @Table(name="users")
//public class User {
// @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
// @Column(nullable=false,unique=true) private String username;
// @Column(nullable=false) private String password;
// @Enumerated(EnumType.STRING) @Column(nullable=false) private Role role;
// public User(String u,String p,Role r){username=u;password=p;role=r;}
// public enum Role{ADMIN,PHARMACIST}
//}
package com.medicalshop.entity;
import jakarta.persistence.*; import lombok.*; import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity @Getter @Setter @NoArgsConstructor @Table(name="users")
public class User {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,unique=true) private String username;
 @JsonIgnore @Column(nullable=false) private String password;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private Role role;
 public User(String u,String p,Role r){username=u;password=p;role=r;}
 public enum Role{ADMIN,PHARMACIST}
}
