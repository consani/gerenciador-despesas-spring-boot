package br.com.impacta.springmvc.gerenciadordespesas;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.authorizeRequests()
				.antMatchers("/despesas/nova").hasRole("ADMIN")
				.antMatchers("/despesas").hasRole("USUARIO_COMUM")
				.anyRequest().authenticated()
				.and()
				.formLogin(); 			
	}
	
	@Override
	  public void configure(AuthenticationManagerBuilder builder) throws Exception {
	    builder.inMemoryAuthentication()
	           .withUser("joao").password("joao123")
	           .roles("USUARIO_COMUM")
	           .and()
	           .withUser("maria").password("maria123")
	           .roles("USUARIO_COMUM");
	}
	
	
	
}
