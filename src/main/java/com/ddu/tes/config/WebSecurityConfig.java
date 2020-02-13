package com.ddu.tes.config;

import com.ddu.tes.service.user.UserService;
import com.ddu.tes.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.web.session.HttpSessionEventPublisher;


/*
*
 * @author ghabtamu
*/
@Configuration
@EnableWebSecurity
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    CustomUserDetailService customUserDetailService;

    @Override
    //this configuration is for handling user requests
    protected void configure(HttpSecurity http)  {

        try {

            http.formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/perform_login")
                    .defaultSuccessUrl("/dashboard",true)
                    .failureUrl("/login?error")
                    .usernameParameter("credential").passwordParameter("password")
                    .and()
                    .logout().logoutSuccessUrl("/login?logout").deleteCookies("JSESSIONID").invalidateHttpSession(true)
                    .and()
                    .exceptionHandling().accessDeniedPage("/403")
                    .setBuilder(http);

            http.authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/dashboard").authenticated()
                    .antMatchers("/home").access("hasAnyRole('"+ Constant.ADMIN_ROLE+"')")
                    .antMatchers("/user/**").access("hasAnyRole('"+ Constant.ADMIN_ROLE+"')")
                    .antMatchers("/answerpage/teaherpage").access("hasAnyRole('"+ Constant.TEACHER_ROLE+"')")
                    .antMatchers("/answerpage/chairedpage").access("hasAnyRole('"+Constant.CHAIRED_ROLE+"')")
                    .antMatchers("/answerpage/acceptAnswer").permitAll()
                    .antMatchers("/forgetPassword").anonymous();


            http.sessionManagement().maximumSessions(1). maxSessionsPreventsLogin(true);
            http.sessionManagement().sessionFixation().migrateSession()
                    .sessionAuthenticationStrategy( registerSessionAuthStr() );
            http.csrf().disable();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("Exception here");
        }
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers(
                        "/error",
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.ttf",
                        "/**/*.woff",
                        "/**/*.woff2",
                        "/**/*.map",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js");
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder())
              ;
    }

    @Bean
    public SessionRegistry sessionRegistry( ) {
        SessionRegistry sessionRegistry = new SessionRegistryImpl( );
        return sessionRegistry;
    }


    @Bean
    public RegisterSessionAuthenticationStrategy registerSessionAuthStr( ) {
        return new RegisterSessionAuthenticationStrategy( sessionRegistry( ) );
    }
    ///Very important ,you wont login again after logout if you dont include this
    @Bean
    public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
    }

    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        firewall.setAllowSemicolon(true);
        return firewall;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}