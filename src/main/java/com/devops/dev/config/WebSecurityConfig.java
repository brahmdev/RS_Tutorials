package com.devops.dev.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
    private static String REALM="MY_TEST_REALM";
    
    @Autowired
    DataSource dataSource;
    
    @Autowired
    LoginSuccessHandler loginSuccessHandler;
    
    /*@Bean
    public UserDetailsService userDetailsService() {
     InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
     manager.createUser(User.withUsername("user").password("password").roles("USER").build());
     return manager;
     }*/
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	 /*http
    	 .authorizeRequests()
    	 .anyRequest().authenticated()
    	 .and()
    	 .formLogin()
    	 .and()
    	 .httpBasic();*/
    	
    	 http
    	 .authorizeRequests()
    	 .antMatchers("/").permitAll() 
    	 /*.antMatchers("/admin/**").hasRole("ADMIN")*/
    	 .anyRequest().authenticated()
    	 .and()
    	 .formLogin()
    	 .loginPage("/login")
    	 .successHandler(loginSuccessHandler)
    	 .permitAll()
    	 .and()
    	 .logout()
    	 .logoutUrl("/logout")
    	 .invalidateHttpSession(true)
    	 ; 
    }


/*     
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("bill").password("abc123").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("tom").password("abc123").roles("USER");
    }*/
    
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
      auth.jdbcAuthentication().dataSource(dataSource)
     .usersByUsernameQuery(
      "select username,password, enabled from users where username=?")
     .authoritiesByUsernameQuery(
      "select username, role from user_roles where username=?");
    } 
   
    /*@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(this.dataSource);
	}*/
     
    /* To allow Pre-flight [OPTIONS] request from browser */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
        web.ignoring().antMatchers("/js/**");
        web.ignoring().antMatchers("/webjars/**");
        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/img/**");
        web.ignoring().antMatchers("/fonts/**");
    }
}
