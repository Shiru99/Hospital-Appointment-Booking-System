package get.sterlite.Authentication.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import get.sterlite.Authentication.model.AuthenticationFailResponse;
import get.sterlite.Authentication.util.JwtUtil;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

            String jwt = authorizationHeader.substring(7);

            try {
                if (jwtUtil.validateToken(jwt)) {

                    UsernamePasswordAuthenticationToken UPAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            null,
                            null, null);

                    SecurityContextHolder.getContext().setAuthentication(UPAuthenticationToken);
                }
            } catch (Exception e) {

                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");

                ObjectMapper objectMapper = new ObjectMapper();
                String authenticationFail = objectMapper
                        .writeValueAsString(new AuthenticationFailResponse(e.getMessage()));

                response.getWriter().write(authenticationFail);

                return;

            }

        }

        chain.doFilter(request, response);

    }

}
