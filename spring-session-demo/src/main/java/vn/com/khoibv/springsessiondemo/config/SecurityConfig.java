package vn.com.khoibv.springsessiondemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import vn.com.khoibv.springsessiondemo.common.Constants;

@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@EnableRedisHttpSession
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//    final String[] permitAllUrls = new String[] { "/css/**", "/js/**", "/fonts/**", "/img/**", "/healthCheck", "/",
//        "/common/**", "/api/**", "/updateCastUser" };
////    List<ScreenEntity> listScreen = screenService.findAllAccess();
//
//    // Do not need security checking
//    ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorization = http.csrf()
//        .ignoringAntMatchers("/updateCastUser").and().authorizeRequests().antMatchers(permitAllUrls).permitAll();
////    for (ScreenEntity screenEntity : listScreen) {
////      if (StringUtils.isEmpty(screenEntity.getAccessRole())) {
////        authorization.antMatchers(screenEntity.getModulePath()).permitAll();
////      } else {
////        String[] listRole = screenEntity.getAccessRole().split(Constants.COMMA);
////        if (listRole.length > 0) {
////          authorization.antMatchers(screenEntity.getModulePath()).hasAnyRole(listRole);
////          authorization.antMatchers(screenEntity.getModulePathForSecurity()).hasAnyRole(listRole);
////        }
////      }
////    }
//
//    // Apply security checking for rest requests
//    authorization.anyRequest().authenticated()
//        .and()
//        .formLogin()//.loginPage(Constants.Url.LOGIN)
//        .defaultSuccessUrl(Constants.Url.HOME)
//        .failureUrl(Constants.Url.LOGIN_FAILED).permitAll()
////        .failureHandler(authenticationHandler())
//        .and()
//        .logout()
//        .logoutRequestMatcher(new AntPathRequestMatcher(Constants.Url.LOGOUT))
//        .logoutSuccessUrl(Constants.Url.LOGIN)
//        .and()
//        .exceptionHandling()
////        .accessDeniedHandler(accessDeniedHandler)
//        .and()
////        .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
//    ;
//  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .inMemoryAuthentication()
        .passwordEncoder(NoOpPasswordEncoder.getInstance())
        .withUser("user").password("password").roles("USER");
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(11);
  }


  @Value("${spring.redis.host}")
  private String redisHost;

  @Value("${spring.redis.port}")
  private int redisPort;

  /**
   * Config Redis connection for Session
   */
  @Bean
  public LettuceConnectionFactory connectionFactory() {
    return new LettuceConnectionFactory(redisHost, redisPort);
  }
}
