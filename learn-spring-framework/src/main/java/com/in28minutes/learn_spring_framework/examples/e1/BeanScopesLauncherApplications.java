package com.in28minutes.learn_spring_framework.examples.e1;


import java.util.Arrays;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
class NormalClass{
	
}
@Component
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class PrototypeClass{
	
	
}

@ComponentScan
@Configuration
public class BeanScopesLauncherApplications {
	 


	public static void main(String[] args) {
		  
		// TODO Auto-generated method stub
		//var game=new MarioGame();
		//var game= new SuperContraGame();
		try(var context=
				new AnnotationConfigApplicationContext(BeanScopesLauncherApplications.class)){
			
			Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
			System.out.println(context.getBean(NormalClass.class));
			System.out.println(context.getBean(NormalClass.class));
			
			System.out.println(context.getBean(PrototypeClass.class));
			System.out.println(context.getBean(PrototypeClass.class));
			System.out.println(context.getBean(PrototypeClass.class));
			
			
			
		}
		
		
		
		
	}

}
