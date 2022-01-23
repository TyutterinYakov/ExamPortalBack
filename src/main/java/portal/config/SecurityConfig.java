package portal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import portal.security.JwtConfigurer;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final JwtConfigurer jwtConfigurer;
	
	@Autowired
	public SecurityConfig(JwtConfigurer jwtConfigurer) {
		super();
		this.jwtConfigurer = jwtConfigurer;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception { //SPRING CONFIG
		
		http
		.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/generate-token", "/current-user").permitAll()
		.antMatchers(HttpMethod.OPTIONS)
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.apply(jwtConfigurer);
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManagerBean();
	}
	
	
	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}
	
}
