package com.in28minutes.learn_spring_framework.examples.h1;


import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;



@ComponentScan
@Configuration
public class XmlConfigurationContextLauncherApplication {
	 


	public static void main(String[] args) {
		  
		// TODO Auto-generated method stub
		//var game=new MarioGame();
		//var game= new SuperContraGame();
		try(var context=
				new ClassPathXmlApplicationContext("contextConfiguration.xml")){
			
			Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
			System.out.println(context.getBean("name"));
			
			
			
			
		}
		
		
		
		
	}

}
