package tw.com.phctw.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    // 設定 Root ApplicationContext（父容器）用於管理 Service、Repository、資料庫等非 Web 層的 Bean
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { AppConfig.class, SecurityBeansConfig.class, SecurityWebConfig.class };
    }

    // 設定 Servlet ApplicationContext（子容器）用於管理 Spring MVC 專用的 Bean（Controller、ViewResolver 等）
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { MvcConfig.class };
    }

    // 設置 DispatcherServlet 的攔截路徑
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

}
