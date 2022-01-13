package portal.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import portal.service.impl.UserDetailServiceImpl;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private UserDetailServiceImpl userDetailService; 
	@Autowired
	private JwtUtils jwtUtils; 
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String requestTokenHeader = request.getHeader("Authorization");
		String userName=null;
		String jwtToken=null;
		
		if(requestTokenHeader!=null &&requestTokenHeader.startsWith("Bearer "))
		{
			jwtToken=requestTokenHeader.substring(7);
			try {
			userName = jwtUtils.extractUsername(jwtToken);
			} catch(ExpiredJwtException ex){
				ex.printStackTrace();
				System.out.println("jwt token has expired");
			} catch(Exception e) {
				e.printStackTrace();
				System.out.println("error");
			}
		} else {
			System.out.println("Invalid token, not start with bearer");
		}
		
		System.out.println(userName);
		
		if(userName!=null&&SecurityContextHolder.getContext().getAuthentication()==null) {
			final UserDetails userDetails = userDetailService.loadUserByUsername(userName);
			if(jwtUtils.validateToken(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken tokenAuth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				tokenAuth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(tokenAuth);
				
			} 
		} else {
			System.out.println("Token is not valid");
		}
		
		
		filterChain.doFilter(request, response);
	}

}
