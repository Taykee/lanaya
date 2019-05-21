package cn.lanaya.business.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Bean
    public CustomUserDetailsService customUserDetailsService(){
        return new CustomUserDetailsService();
    }

    @Bean
    public PasswordEncoderImpl passwordEncoder(){
        return new PasswordEncoderImpl();
    }

    @Override
    protected void configure(HttpSecurity security)throws Exception{
        security.authorizeRequests().antMatchers("/css/**", "/img/**", "/plugins/**", "login.html")
                .permitAll().anyRequest().authenticated().and().formLogin()
                .loginPage("/login").successForwardUrl("/index")
                .failureForwardUrl("/login?error=1").and()
                .exceptionHandling().accessDeniedPage("/403");
        security.logout().logoutSuccessUrl("/login");
    }

}
