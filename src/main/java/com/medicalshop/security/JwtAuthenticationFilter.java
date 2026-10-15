//package com.medicalshop.security;
//import jakarta.servlet.*; import jakarta.servlet.http.*; import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; import org.springframework.security.core.context.SecurityContextHolder; import org.springframework.security.core.authority.SimpleGrantedAuthority; import org.springframework.stereotype.Component; import org.springframework.web.filter.OncePerRequestFilter; import java.io.IOException; import java.util.*;
////@Component public class JwtAuthenticationFilter extends OncePerRequestFilter{
//// private final JwtService jwt; public JwtAuthenticationFilter(JwtService j){jwt=j;}
//// protected void doFilterInternal(HttpServletRequest r,HttpServletResponse s,FilterChain c)throws ServletException,IOException{
////  String h=r.getHeader("Authorization");
////  if(h!=null&&h.startsWith("Bearer ")){try{var cl=jwt.claims(h.substring(7));var a=new UsernamePasswordAuthenticationToken(cl.getSubject(),null,List.of(new SimpleGrantedAuthority("ROLE_"+cl.get("role",String.class))));SecurityContextHolder.getContext().setAuthentication(a);}catch(Exception ignored){}}
////  c.doFilter(r,s);
//// }
////}
//@Component
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    private final JwtService jwt;
//
//    public JwtAuthenticationFilter(JwtService j) {
//        jwt = j;
//    }
//
//    @Override
//    protected void doFilterInternal(
//            HttpServletRequest r,
//            HttpServletResponse s,
//            FilterChain c)
//            throws ServletException, IOException {
//
//        String h = r.getHeader("Authorization");
//
//        System.out.println("Request: " + r.getRequestURI());
//        System.out.println("Authorization: " + h);
//
//        if (h != null && h.startsWith("Bearer ")) {
//            try {
//                var cl = jwt.claims(h.substring(7));
//
//                String username = cl.getSubject();
//                String role = cl.get("role", String.class);
//
//                System.out.println("JWT username: " + username);
//                System.out.println("JWT role: " + role);
//
//                var authority =
//                        new SimpleGrantedAuthority("ROLE_" + role);
//
//                var authentication =
//                        new UsernamePasswordAuthenticationToken(
//                                username,
//                                null,
//                                List.of(authority)
//                        );
//
//                SecurityContextHolder
//                        .getContext()
//                        .setAuthentication(authentication);
//
//            } catch (Exception e) {
//                System.out.println("JWT validation failed:");
//                e.printStackTrace();
//            }
//        }
//
//        c.doFilter(r, s);
//    }
//}
package com.medicalshop.security;
import jakarta.servlet.*; import jakarta.servlet.http.*; import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; import org.springframework.security.core.context.SecurityContextHolder; import org.springframework.security.core.authority.SimpleGrantedAuthority; import org.springframework.stereotype.Component; import org.springframework.web.filter.OncePerRequestFilter; import java.io.IOException; import java.util.*;
@Component public class JwtAuthenticationFilter extends OncePerRequestFilter{
 private final JwtService jwt; public JwtAuthenticationFilter(JwtService j){jwt=j;}
 protected void doFilterInternal(HttpServletRequest r,HttpServletResponse s,FilterChain c)throws ServletException,IOException{
  String h=r.getHeader("Authorization");
  if(h!=null&&h.startsWith("Bearer ")){try{var cl=jwt.claims(h.substring(7));var a=new UsernamePasswordAuthenticationToken(cl.getSubject(),null,List.of(new SimpleGrantedAuthority("ROLE_"+cl.get("role",String.class))));SecurityContextHolder.getContext().setAuthentication(a);}catch(Exception ignored){}}
  c.doFilter(r,s);
 }
}
