package com.example.simple_rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;
import java.util.List;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;

@Configuration
@EnableWebSecurity
// @Order(SecurityProperties.DEFAULT_FILTER_ORDER)
public class WebSecurityConfig {

    // desde application.properties
    @Value("${cors.origin}")
    private String corsOrigin;
    /*
    @Autowired
    CustomUserDetailsService userDetailsService;

    @Autowired
    JwtRequestFilter jwtRequestFilter;
    */

    /*
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    */




    /*
    las llamadas /auth/** pueden llamarse sin autenticacion
    el resto con autenticacion
    notar que las rutas estan sin /api
     */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())

                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration configuration = new CorsConfiguration();
                    configuration.setAllowedOrigins(Arrays.asList("*"));
                    configuration.setAllowedMethods(Arrays.asList("*"));
                    configuration.setAllowedHeaders(Arrays.asList("*"));
                    return configuration;
                }))

                .authorizeHttpRequests(
                        auth -> auth.requestMatchers("/api/customers/**", "/api/**", "/actuator/**").permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(httpSecurityHeadersConfigurer -> {
                    httpSecurityHeadersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable);
                });


        return http.build();
    }

    /*
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of(corsOrigin));
        configuration.setAllowedMethods(List.of("GET","POST"));
        configuration.setAllowedHeaders(List.of("Authorization","Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**",configuration);

        return source;
    }

     */






/*
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // CookieCsrfTokenRepository csrfTokenRepository = new CookieCsrfTokenRepository();
        // proposal to configure the httpOnly flag...
        // csrfTokenRepository.setCookieHttpOnly(false);
        // csrfTokenRepository.setCookiePath("/");
        // csrf.csrfTokenRepository(csrfTokenRepository) para habilitar CSRF
        http.csrf().disable()
                .authorizeRequests()
                // .antMatchers("/api/auth/login", "/api/companies", "/api/projects", "/api/findings", "/actuator/**")
                .antMatchers("/api/auth/ **", "/actuator/ **")
                .permitAll()
                //.anyRequest()
                //.authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // No session creation

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }
*/

    /*
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception
    {
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER")
                .and()
                .withUser("admin").password("{noop}admin").roles("USER", "ADMIN", "READER", "WRITER")
                .and()
                .withUser("soporteweb").password("{noop}sopweb123").roles("USER", "ADMIN", "READER", "WRITER")
                .and()
                .withUser("audit").password("{noop}audit").roles("USER", "ADMIN", "READER");
    }
    */
    /*
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin(corsOrigin);
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.addExposedHeader("content-disposition");
        source.registerCorsConfiguration("/**", config);
        CorsFilter bean = new CorsFilter(source);
        //bean.setOrder(0);
        return bean;
    }
     */
}
