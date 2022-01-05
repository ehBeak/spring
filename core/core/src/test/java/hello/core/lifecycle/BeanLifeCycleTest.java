package hello.core.lifecycle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.Lifecycle;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    @DisplayName("빈 생명주기 콜백_X")
    public void lifeCycleTest_X() {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Test
    @DisplayName("빈 생명주기 콜백_인터페이스")
    public void lifeCycleTest_Interface() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleInterfaceConfig.class);
        ac.getBean(NetworkClientInterface.class);
        ac.close();
    }

    @Test
    @DisplayName("빈 생명주기 콜백_메서드")
    public void networkClientMethod() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleMethodConfig.class);
        ac.getBean(NetworkClientMethod.class);
        ac.close();
    }
    @Test
    @DisplayName("빈 생명주기 콜백_애노테이션")
    public void networkClientAnnotation() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleAnnotationConfig.class);
        ac.getBean(NetworkClientAnnotation.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {

        @Bean
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring");
            return networkClient;
        }

    }

    @Configuration
    static class LifeCycleInterfaceConfig {

        @Bean
        public NetworkClientInterface networkClientInterface(){
            NetworkClientInterface networkClient = new NetworkClientInterface();
            networkClient.setUrl("http://hello-spring-interface");

            return networkClient;
        }

    }

    @Configuration
    static class LifeCycleMethodConfig {

        @Bean(initMethod = "init", destroyMethod = "close")
        public NetworkClientMethod networkClientMethod(){
            NetworkClientMethod networkClient = new NetworkClientMethod();
            networkClient.setUrl("http://hello-spring-method");

            return networkClient;
        }

    }

    @Configuration
    static class LifeCycleAnnotationConfig {

        @Bean
        public NetworkClientAnnotation networkClientAnnotation(){
            NetworkClientAnnotation networkClient = new NetworkClientAnnotation();
            networkClient.setUrl("http://hello-spring-annotation");

            return networkClient;
        }

    }


}
