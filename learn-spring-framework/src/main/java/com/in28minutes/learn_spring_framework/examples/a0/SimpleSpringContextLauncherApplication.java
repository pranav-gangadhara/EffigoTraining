package com.in28minutes.learn_spring_framework.examples.a0;


import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;



@ComponentScan
@Configuration
public class SimpleSpringContextLauncherApplication {
	 


	public static void main(String[] args) {
		  
		// TODO Auto-generated method stub
		//var game=new MarioGame();
		//var game= new SuperContraGame();
		try(var context=
				new AnnotationConfigApplicationContext(SimpleSpringContextLauncherApplication.class)){
			
			Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
			
			
			
			
		}
		
		
		
		
	}

}
