package hello.core.beanfind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        String[] beanNames = ac.getBeanDefinitionNames();
        for(String name : beanNames){
            Object bean = ac.getBean(name);
            System.out.println("name = " + name + "object = " + bean);
        }
    }

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAppBean(){
        String[] beanNames = ac.getBeanDefinitionNames();
        for(String name : beanNames){

            BeanDefinition beanDefinition = ac.getBeanDefinition(name);

            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(name);
                System.out.println("name = " + name + "object = " + bean);            
            }
        }
    }

}
