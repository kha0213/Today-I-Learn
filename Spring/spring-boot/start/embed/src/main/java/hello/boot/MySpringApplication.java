package hello.boot;

import hello.spring.HelloConfig;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.Arrays;

public class MySpringApplication {
    public static void run(Class<?> bootClass, String[] args) {
        System.out.println("MySpringApplication.run args= " + Arrays.toString(args));
        Tomcat tomcat = new Tomcat();

        Connector connector = new Connector();
        connector.setPort(8080);

        tomcat.setConnector(connector);

        // 스프링 컨테이너 생성
        AnnotationConfigWebApplicationContext applicationContext =
                new AnnotationConfigWebApplicationContext();
        applicationContext.register(bootClass);

        // 디스패쳐 서블릿 생성
        DispatcherServlet dispatcher = new DispatcherServlet(applicationContext);

        // 디스패쳐 서블릿 등록
        Context context = tomcat.addContext("", "/");
        tomcat.addServlet("", "dispatcher", dispatcher);
        context.addServletMappingDecoded("/", "dispatcher");

        try {
            tomcat.start();
        } catch (LifecycleException e) {
            throw new RuntimeException(e);
        }
    }
}
