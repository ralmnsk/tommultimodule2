package com.github.ralmnsk.demo.config;

import com.github.ralmnsk.dao.connection.JpaConfig;
import com.github.ralmnsk.service.ServiceConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Slf4j
@Configuration
@EnableWebMvc
//@ComponentScan(basePackages = {"com.github.ralmnsk.demo"})
//@Import(ServiceConfiguration.class)
public class WebConfiguration implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/login");
//        registry.addViewController("/welcome").setViewName("welcome");
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        registry.viewResolver(resolver);
//
//        registry.viewResolver(new ThymeleafViewResolver());
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/webapp/**")
//                .addResourceLocations("/webapp/");
//    }


    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }


}
