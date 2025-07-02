package com.example.demo.security;

import java.io.IOException;
import java.util.List;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.demo.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;

    public JwtAuthFilter(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
        throws ServletException, IOException {
    	
    	String path = req.getServletPath();
    	if (path.startsWith("/auth/login")) {
    	    chain.doFilter(req, res);
    	    return;
    	}

        String header = req.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(req, res);
            return;
        }

        /*
         * Dopo aver "scartato" il caso in cui si sta passando per la login e il caso in cui non è presente ancora un token salvato...
         * procede con la validazione del token (e del ruolo utente) per tutte le richieste ai vari end-point dell'applicativo.
         * Se tutto è ok UsernamePasswordAuthenticationToken auth rappresente la corretta autenticazione/autorizzazione dell'utente.
         */
        String token = header.substring(7);
        try {
            Claims claims = JwtUtil.validateToken(token);
            String username = claims.getSubject();
            String role = claims.get("role", String.class);

            UserDetails user = userDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                user, null, List.of(new SimpleGrantedAuthority(role))
            );

            SecurityContextHolder.getContext().setAuthentication(auth);

        } catch (Exception e) {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token non valido o scaduto");
            return;
        }

        chain.doFilter(req, res);
    }
}
