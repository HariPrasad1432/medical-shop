package com.medicalshop.controller;
import com.medicalshop.entity.User; import com.medicalshop.repository.UserRepository; import lombok.RequiredArgsConstructor; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.security.access.prepost.PreAuthorize; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/users") @RequiredArgsConstructor @PreAuthorize("hasRole('ADMIN')")
public class UserController {
 private final UserRepository users; private final PasswordEncoder encoder;
 @GetMapping public List<User> all(){return users.findAll();}
 @PostMapping public User create(@RequestBody Map<String,String> r){String u=r.get("username"),p=r.get("password"); if(u==null||u.isBlank()||p==null||p.isBlank())throw new IllegalArgumentException("Username and password are required"); if(users.findByUsername(u).isPresent())throw new IllegalArgumentException("Username already exists"); User.Role role="ADMIN".equalsIgnoreCase(r.get("role"))?User.Role.ADMIN:User.Role.PHARMACIST; return users.save(new User(u,encoder.encode(p),role));}
}
