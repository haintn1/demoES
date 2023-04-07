package digi.ecomm.platformservice.web;

import digi.ecomm.platformservice.web.impl.RequestAttributeServiceImpl;
import digi.ecomm.platformservice.web.interceptor.L10NDetectInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.MappedInterceptor;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

@Configuration
public class L10NWebConfiguration implements WebMvcConfigurer {

    @Bean("requestAttributeService")
    public RequestAttributeService requestAttributeService() {
        return new RequestAttributeServiceImpl();
    }

    @Bean
    public LocaleResolver localeResolver() {
        final AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(Locale.US);
        return localeResolver;
    }

    @Bean("l10nDetectInterceptor")
    public L10NDetectInterceptor languageDetectInterceptor() {
        return new L10NDetectInterceptor(requestAttributeService());
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(languageDetectInterceptor());
    }

    @Bean("languageDetectMappedInterceptor")
    public MappedInterceptor languageDetectMappedInterceptor() {
        return new MappedInterceptor(new String[]{"/**"}, languageDetectInterceptor());
    }
}
