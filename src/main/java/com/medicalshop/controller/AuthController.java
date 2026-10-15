package com.medicalshop.controller;
import com.medicalshop.entity.User; import com.medicalshop.repository.UserRepository; import com.medicalshop.security.JwtService;
import lombok.RequiredArgsConstructor; import org.springframework.security.authentication.*; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/auth") @RequiredArgsConstructor
public class AuthController{
 private final AuthenticationManager am; private final UserRepository users; private final PasswordEncoder encoder; private final JwtService jwt;
 @PostMapping("/login") public Map<String,String> login(@RequestBody Map<String,String> r){
  am.authenticate(new UsernamePasswordAuthenticationToken(r.get("username"),r.get("password"))); User u=users.findByUsername(r.get("username")).orElseThrow();
  return Map.of("token",jwt.generate(u.getUsername(),u.getRole().name()),"role",u.getRole().name(),"username",u.getUsername());
 }
 @PostMapping("/register") public Map<String,String> register(@RequestBody Map<String,String> r){
  if(users.findByUsername(r.get("username")).isPresent())throw new IllegalArgumentException("Username already exists");
  User.Role role="ADMIN".equalsIgnoreCase(r.getOrDefault("role","PHARMACIST"))?User.Role.ADMIN:User.Role.PHARMACIST;
  users.save(new User(r.get("username"),encoder.encode(r.get("password")),role)); return Map.of("message","User created");
 }
}
