//package com.medicalshop.repository;
//import com.medicalshop.entity.User; import org.springframework.data.jpa.repository.JpaRepository; import java.util.Optional;
//public interface UserRepository extends JpaRepository<User,Long>{Optional<User> findByUsername(String username);}
package com.medicalshop.repository;
import com.medicalshop.entity.User; import org.springframework.data.jpa.repository.JpaRepository; import java.util.Optional;
public interface UserRepository extends JpaRepository<User,Long>{Optional<User> findByUsername(String username);}
