package vn.com.khoibv.springsessiondemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {


  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // CSS locations
    registry.addResourceHandler("/css/**", "/css/images/**")
        .addResourceLocations("classpath:/static/css/", "classpath:/static/css/sass/");

    // JS locations
    registry.addResourceHandler("/js/**")
        .addResourceLocations("classpath:/static/js/");
  }

  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }


  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addRedirectViewController("", "user");
  }

}
