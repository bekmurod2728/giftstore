package uz.giftstore.config;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.giftstore.utils.JwtTokenUteils;

import java.io.IOException;
import java.util.stream.Collectors;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenUteils jwtTokenUtiels;
@Autowired
    public JwtRequestFilter(JwtTokenUteils jwtTokenUtiels) {
        this.jwtTokenUtiels = jwtTokenUtiels;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader=request.getHeader("Authorization");
        String username=null;
        String jwt=null;
        if (authHeader !=null && authHeader.startsWith("Bearer ")){
            jwt=authHeader.substring(7);
            username=jwtTokenUtiels.getUsername(jwt);
        }
        if (username !=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    jwtTokenUtiels.getRoles(jwt).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
            );
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        filterChain.doFilter(request,response);
    }
}
