//package com.medicalshop.security;
//import io.jsonwebtoken.*; import io.jsonwebtoken.security.Keys; import org.springframework.beans.factory.annotation.Value; import org.springframework.stereotype.Service;
//import javax.crypto.SecretKey; import java.nio.charset.StandardCharsets; import java.util.Date;
//@Service public class JwtService{
// private final SecretKey key; private final long exp;
// public JwtService(@Value("${app.jwt.secret}")String s,@Value("${app.jwt.expiration-ms}")long e){key=Keys.hmacShaKeyFor(s.getBytes(StandardCharsets.UTF_8));exp=e;}
// public String generate(String u,String role){return Jwts.builder().subject(u).claim("role",role).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis()+exp)).signWith(key).compact();}
// public Claims claims(String t){return Jwts.parser().verifyWith(key).build().parseSignedClaims(t).getPayload();}
//}
package com.medicalshop.security;
import io.jsonwebtoken.*; import io.jsonwebtoken.security.Keys; import org.springframework.beans.factory.annotation.Value; import org.springframework.stereotype.Service;
import javax.crypto.SecretKey; import java.nio.charset.StandardCharsets; import java.util.Date;
@Service public class JwtService{
 private final SecretKey key; private final long exp;
 public JwtService(@Value("${app.jwt.secret}")String s,@Value("${app.jwt.expiration-ms}")long e){key=Keys.hmacShaKeyFor(s.getBytes(StandardCharsets.UTF_8));exp=e;}
 public String generate(String u,String role){return Jwts.builder().subject(u).claim("role",role).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis()+exp)).signWith(key).compact();}
 public Claims claims(String t){return Jwts.parser().verifyWith(key).build().parseSignedClaims(t).getPayload();}
}
