package hello.core.beandefinition;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import hello.core.AppConfig;

public class BeanDefinitionTest {

    // 어떻게 설정 정보를 가지고 오느냐에 따라 메타 정보가 바뀜...
    //AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml"); 

    @Test
    @DisplayName("빈 설정 메타 정보 확인")
    void findApplicationBean(){

        // ApplicationContext 로 타입을 선언(ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml"); ) 하면 해당 함수가 오류남
        // 그래서 어쩔 수 없이 구체 클래스로 선언함.. 사실 그 함수를 가지고 있는 추상 타입을 찾아서 쓰면 될듯.
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();    
        for(String name : beanDefinitionNames){

            BeanDefinition beanDefinition = ac.getBeanDefinition(name);     

            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                System.out.println("BeanDefinitionName = " + name + " beanDefinition = " + beanDefinition);
            }

        }

    }

}
