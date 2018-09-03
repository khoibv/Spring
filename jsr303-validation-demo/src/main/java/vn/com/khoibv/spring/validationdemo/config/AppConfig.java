package vn.com.khoibv.spring.validationdemo.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
public class AppConfig {

  /**
   * Message resource bean
   * Load from messages.properties file
   *
   * @return
   */
  @Bean(name = "messageSource")
  public MessageSource messageSource() {
    ReloadableResourceBundleMessageSource bean = new ReloadableResourceBundleMessageSource();
    bean.addBasenames("classpath:messages", "classpath:labels");
    return bean;
  }

  /**
   * Label resource bean
   * Load from labels.properties file
   *
   * @return
   */
  @Bean(name = "labels")
  public MessageSource labelSource() {
    ReloadableResourceBundleMessageSource bean = new ReloadableResourceBundleMessageSource();
    bean.addBasenames("classpath:labels");
    return bean;
  }

}
