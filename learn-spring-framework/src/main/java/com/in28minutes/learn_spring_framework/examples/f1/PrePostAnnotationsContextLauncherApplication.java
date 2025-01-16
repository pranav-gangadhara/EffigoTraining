package com.in28minutes.learn_spring_framework.examples.f1;


import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
class Dependency{
	private SomeDependency someDependency;

	public Dependency(SomeDependency someDependency) {
		super();
		this.someDependency = someDependency;
	}
	
	@PostConstruct
	public void initialization() {
		someDependency.getReady();
	}
	@PreDestroy
	public void cleanup() {
		System.out.println("Clean Up Code");
	}
	
}
@Component
class SomeDependency{
	public void getReady() {
		System.out.println("Get Ready method called");
	}
	
}
@ComponentScan
@Configuration
public class PrePostAnnotationsContextLauncherApplication {
	 


	public static void main(String[] args) {
		  
		// TODO Auto-generated method stub
		//var game=new MarioGame();
		//var game= new SuperContraGame();
		try(var context=
				new AnnotationConfigApplicationContext(PrePostAnnotationsContextLauncherApplication.class)){
			
			Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
			
			
			
			
		}
		
		
		
		
	}

}
