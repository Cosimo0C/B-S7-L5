package cosimocrupi.L5.security;

import cosimocrupi.L5.entities.Utente;
import cosimocrupi.L5.exceptions.UnauthorizedException;
import cosimocrupi.L5.services.UtenteService;
import cosimocrupi.L5.tools.JWTTools;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class JWTFilter extends OncePerRequestFilter {
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private UtenteService utenteService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHead = request.getHeader("Authorization");
        if (authHead == null || !authHead.startsWith("Bearer ")){
            throw new UnauthorizedException("Inserire il token nel formato corretto!");
        }
        String accT = authHead.replace("Bearer ", "");
        jwtTools.verifyToken(accT);
        String utenteId = jwtTools.extractIdFromToken(accT);
        Utente ut = this.utenteService.findById(UUID.fromString(utenteId));
        Authentication authentication = new UsernamePasswordAuthenticationToken(ut, null, ut.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
