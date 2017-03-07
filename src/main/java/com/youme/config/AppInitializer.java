package com.youme.config;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 系统入口
 * 
 * @author Youme.Ma
 *
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	//配置扫描根路径
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[]{RootConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}

	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return new Filter[]{characterEncodingFilter};
	}

	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
	}
//	@Override
//	protected Class<?>[] getRootConfigClasses() {
//		return new Class[] { AppDataAccessConfig.class };
//	}
//
//	@Override
//	protected Class<?>[] getServletConfigClasses() {
//		return new Class[] { AppMvcConfig.class };
//	}
//
//	@Override
//	protected String[] getServletMappings() {
//		return new String[] { "/" };
//	}
//
//	@Bean
//	public CharacterEncodingFilter characterEncodingFilter() {
//		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//		characterEncodingFilter.setEncoding("UTF-8");
//		characterEncodingFilter.setForceEncoding(true);
//		return characterEncodingFilter;
//	}
//
//	@Override
//	protected Filter[] getServletFilters() {
//		return new Filter[] { characterEncodingFilter() };
//	}
}
