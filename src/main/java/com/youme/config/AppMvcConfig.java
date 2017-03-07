package com.youme.config;


import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan(basePackages = { "com.youme" })
@MapperScan("com.youme.mapper")
public class AppMvcConfig extends WebMvcConfigurerAdapter {

	private static final String MESSAGE_SOURCE = "/WEB-INF/i18n/messages";
	private static final String VIEWS = "/WEB-INF/views/";
	private static final String RESOURCES_LOCATION = "/resources/";
	private static final String RESOURCES_HANDLER = RESOURCES_LOCATION + "**";


	//static resources

//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/css/**").addResourceLocations("/css/");
//		registry.addResourceHandler("/img/**").addResourceLocations("/img/");
//		registry.addResourceHandler("/js/**").addResourceLocations("/js/");
//	}



	//定义spring文件上传编码
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("UTF-8");
		return multipartResolver;
	}

	//解析json返回数据
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		List<MediaType> mediaTypes = new ArrayList(converter.getSupportedMediaTypes());
		converter.setSupportedMediaTypes(mediaTypes);
		mediaTypes.addAll(asList(MediaType.TEXT_PLAIN, MediaType.TEXT_HTML, MediaType.TEXT_XML));
		converters.add(converter);
	}
/*

	//配置diamond,如果本地没有配置diamond可以注释该段代码
	@Bean
	public DiamondSDK diamondSDK() {
		Minerable minerable = new Miner().getMiner("poet.diamond", "diamondConfig");
		DiamondSDK diamondSDK = new DiamondSDK(
				new DiamondSDKConf(
						Lists.newArrayList(new DiamondConf(
								minerable.getString("diamondIp"),
								minerable.getInt("diamondPort"),
								minerable.getString("diamondUsername"),
								minerable.getString("diamondPassword")))));
		return diamondSDK;
	}
*/

	//快捷定义ViewController
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}

	/**
	 * Jsp View resolver
	 *//*
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix(VIEWS);
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
     */
	/**
	 * 限制上传文件的大小
	 */
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver commonsMultipartResolver() {
		CommonsMultipartResolver common = new CommonsMultipartResolver();
		common.setMaxUploadSize(10 * 1024 * 1024);// 10M
		return common;
	}


	/**
	 * 字符串消息转换器
	 */
	@Bean
	public StringHttpMessageConverter stringHttpMessageConverter() {
		StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
		stringHttpMessageConverter.setDefaultCharset(Charset.forName("UTF-8"));
		return stringHttpMessageConverter;
	}

	/**
			*
	JSON 数据处理
    */
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		mappingJackson2HttpMessageConverter.setDefaultCharset(Charset.forName("UTF-8"));
		return mappingJackson2HttpMessageConverter;
	}

	/**
	 * 注册消息转换器
	 *//*
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(stringHttpMessageConverter());
		converters.add(mappingJackson2HttpMessageConverter());
	}

	*//**
	 * 默认
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean(name = "messageSource")
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename(MESSAGE_SOURCE);
		messageSource.setCacheSeconds(5);
		return messageSource;
	}

	/**html视图*/
// 配置thymeleaf视图解析器,
	// 保留原有配置
	// 加入自定义参数 如${e.res()}
	@Bean
	public ThymeleafViewResolver thymeleafViewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setCharacterEncoding("UTF-8");

		Map<String, Object> oldStaticVariables = viewResolver.getStaticVariables();
		Map<String, Object> staticVariables = Maps.newHashMap(oldStaticVariables);
		/*staticVariables.put("e", new SpringUtils());*/
		viewResolver.setStaticVariables(staticVariables);
		return viewResolver;
}

	//配置模板引擎
	/*@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setTemplateResolver(templateResolver());
		engine.addDialect(new LayoutDialect());
		return engine;
	}
*/
	//配置模板解析器
	@Bean
	public TemplateResolver templateResolver() {
		ClassLoaderTemplateResolver templateResolver;
		templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setTemplateMode("XHTML");
		templateResolver.setPrefix(VIEWS);
		templateResolver.setCharacterEncoding("UTF-8");
		templateResolver.setSuffix(".html");
		templateResolver.setCacheable(false); // true by default
		return templateResolver;
	}
	/*@Bean
	public ITemplateResolver templateResolver() {
		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
		resolver.setPrefix(VIEWS);
		resolver.setSuffix(".html");
		resolver.setTemplateMode(TemplateMode.HTML);
		resolver.setCacheable(false);
		resolver.setCharacterEncoding("UTF-8");
		resolver.setOrder(1);
		resolver.setCacheable(false);
		return resolver;
	}*/

	@Bean
	public MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter(){
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(ImmutableList.of(MediaType.TEXT_HTML, MediaType.APPLICATION_JSON));
		return converter;
	}


	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		templateEngine.addDialect(new SpringSecurityDialect());
		templateEngine.addDialect(new Java8TimeDialect());
		return templateEngine;
	}

	@Bean
	public ViewResolver viewResolver() {
		ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
		thymeleafViewResolver.setTemplateEngine(templateEngine());
		thymeleafViewResolver.setCharacterEncoding("UTF-8");
		return thymeleafViewResolver;
	}

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(RESOURCES_HANDLER).addResourceLocations(RESOURCES_LOCATION);
    }

}
