package com.sip.ams.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.sip.ams.service.UserService;


@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserService userService;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
        .authorizeRequests()
        .antMatchers(
            "/registration**",
            "/js/**",
            "/css/**",
            "/img/**",
            "/webjars/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .permitAll()
        .and()
        .logout()
        .invalidateHttpSession(true)
        .clearAuthentication(true)
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/login?logout")
        .permitAll();
		
		
	}
	
	@Bean 
	public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
	
	/**
	 * @author Bloc D
	 *  @override est utilisé pour définir une méthode qui est héritée de la classe parente. On ne l'utilise donc que dans
	 *   le cas de l'héritage. En plaçant ce mot-clé dans le commentaire de la méthode réécrite, le compilateur vérifiera
	 *    que la méthode est correctement utilisée 
	 *  (mêmes arguments, même type de valeur de retour) et affichera un message d'avertissement si ce n'est pas le cas
	 *
	 *@Bean and @Autowired do two very different things. The other answers here explain in a little more detail, but at a simpler level:

    @Bean tells Spring 'here is an instance of this class, please keep hold of it and give it back to me when I ask'.

    @Autowired says 'please give me an instance of this class, for example, one that I created with an @Bean annotation earlier'.

	 */
	

}
