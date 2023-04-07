package digi.ecomm.user.config;

import digi.ecomm.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private static final String AUTHORIZATION_TYPE = "Bearer ";

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserService customUserDetailsService;

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain)
            throws ServletException, IOException {
        try {
            final String jwt = getJwtFromRequest(request);

            if (StringUtils.isNotEmpty(jwt) && tokenProvider.validateToken(jwt)) {
                final Long userId = tokenProvider.getUserIdFromJWT(jwt);
                final UserDetails userDetails = customUserDetailsService.loadUserById(userId);
                final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            LOGGER.error("failed on set user authentication", ex);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(final HttpServletRequest request) {
        final String bearerToken = request.getHeader("Authorization");
        if (StringUtils.isNotEmpty(bearerToken) && bearerToken.startsWith(AUTHORIZATION_TYPE)) {
            return bearerToken.substring(AUTHORIZATION_TYPE.length());
        }
        return null;
    }
}
