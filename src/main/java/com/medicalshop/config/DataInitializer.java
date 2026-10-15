package com.medicalshop.config;
import com.medicalshop.entity.User; import com.medicalshop.repository.UserRepository; import org.springframework.context.annotation.*; import org.springframework.boot.CommandLineRunner; import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration public class DataInitializer{
 @Bean CommandLineRunner seed(UserRepository r,PasswordEncoder e){return a->{if(r.findByUsername("admin").isEmpty())r.save(new User("admin",e.encode("admin123"),User.Role.ADMIN));if(r.findByUsername("pharmacist").isEmpty())r.save(new User("pharmacist",e.encode("pharma123"),User.Role.PHARMACIST));};}
}
