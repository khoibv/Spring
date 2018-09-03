package vn.com.khoibv.spring.jtademo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {


  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // CSS locations
    registry.addResourceHandler("/css/**")
        .addResourceLocations("classpath:/static/css/", "classpath:/static/css/app/");

    // JS locations
    registry.addResourceHandler("/js/**")
        .addResourceLocations("classpath:/static/js/", "classpath:/static/js/app/");
  }

  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }


  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addRedirectViewController("", "user");
    super.addViewControllers(registry);
  }
}
