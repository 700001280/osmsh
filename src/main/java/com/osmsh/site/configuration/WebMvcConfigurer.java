package com.osmsh.site.configuration;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;


import io.tracee.binding.springmvc.TraceeInterceptor;

@Configuration
@EnableTransactionManagement
public class WebMvcConfigurer extends WebMvcConfigurerAdapter implements ApplicationContextAware{

	@Autowired
	public ThymeleafProperties thymeleafProperties;

	/**
	 * TODO Auto-generated attribute documentation
	 */
	@Autowired
	public TemplateEngine templateEngine;

	/**
	 * TODO Auto-generated attribute documentation
	 */
	public ApplicationContext applicationContext;

	/**
	 * TODO Auto-generated method documentation
	 * 
	 * @return ThymeleafViewResolver
	 */
	@Bean
	public ThymeleafViewResolver javascriptThymeleafViewResolver() {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(this.templateEngine);
		resolver.setCharacterEncoding("UTF-8");
		resolver.setContentType("application/javascript");
		resolver.setViewNames(new String[] { "*.js" });
		resolver.setCache(this.thymeleafProperties.isCache());
		return resolver;
	}

	/**
	 * TODO Auto-generated method documentation
	 * 
	 * @return SpringResourceTemplateResolver
	 */
	@Bean
	public SpringResourceTemplateResolver javascriptTemplateResolver() {
		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
		resolver.setApplicationContext(this.applicationContext);
		resolver.setPrefix("classpath:/templates/fragments/js/");
		resolver.setTemplateMode(TemplateMode.JAVASCRIPT);
		resolver.setCharacterEncoding("UTF-8");
		resolver.setCheckExistence(true);
		resolver.setCacheable(this.thymeleafProperties.isCache());
		return resolver;
	}

	/**
	 * TODO Auto-generated method documentation
	 * 
	 * @param applicationContext
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	//////////////////////////////////////////////////////////////

	@Bean
	public LocalValidatorFactoryBean validator() {
		return new LocalValidatorFactoryBean();
	}

	/**
	 * TODO Auto-generated method documentation
	 * 
	 * @return LocaleResolver
	 */
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(new Locale("en"));
		return localeResolver;
	}

	/**
	 * TODO Auto-generated method documentation
	 * 
	 * @return LocaleChangeInterceptor
	 */
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}

	/**
	 * TODO Auto-generated method documentation
	 * 
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
		registry.addInterceptor(new TraceeInterceptor());
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
	}


	/**
	 * Handles favicon.ico requests assuring no <code>404 Not Found</code> error
	 * is returned.
	 */
	@Controller
	static class FaviconController {
		@RequestMapping("favicon.ico")
		String favicon() {
			return "forward:/resources/images/favicon.ico";
		}
	}



}
