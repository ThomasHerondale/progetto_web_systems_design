package it.wsda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebSystemsDesignProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebSystemsDesignProjectApplication.class, args);
    }
    @Bean
    public ServletRegistrationBean<DataReceiverServlet> dataReceiverServlet() {
        return new ServletRegistrationBean<>(new DataReceiverServlet(), "/receiveData");
    }

    @Bean
    public ServletRegistrationBean<DataReportServlet> dataReportServlet() {
        return new ServletRegistrationBean<>(new DataReportServlet(), "/reportData");
    }
}
