package org.negro.tournament;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues() )
		.and()
		.authorizeRequests()
        .antMatchers("/", "/login", "/singUp").permitAll()
        .anyRequest().authenticated()
        
        .and()
        .formLogin()
        .permitAll()
        
        .and()
        .logout()
        
        .and().csrf().disable();
	}
}
