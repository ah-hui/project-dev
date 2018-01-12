package ind.lgh.system.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 过滤器测试 - 目前只过滤请求输出url
 *
 * @author lgh
 * @since 2017-12-21
 */
@Configuration
public class WebConfiguration {

    /**
     * 注册过滤器
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new MyFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.addInitParameter("parmName", "parmValue");
        registrationBean.setName("MyFilter");
        registrationBean.setOrder(1);
        return registrationBean;
    }

    /**
     * 实现自定义过滤器
     */
    public class MyFilter implements Filter {

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {

        }

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            System.out.println("MyFilter, URI: " + request.getRequestURI()+", method: "+request.getMethod());
            filterChain.doFilter(servletRequest, servletResponse);
        }

        @Override
        public void destroy() {

        }
    }
}
