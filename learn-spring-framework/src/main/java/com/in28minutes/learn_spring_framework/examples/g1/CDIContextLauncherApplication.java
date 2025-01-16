package com.in28minutes.learn_spring_framework.examples.g1;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named

class Dependency{
	private SomeDependency someDependency;
	@Inject
	public void setDataService(SomeDependency someDependency) {
		System.out.println("Setter Injection");
		this.someDependency=someDependency;
		
	}
	
	public SomeDependency getService() {
		return someDependency;
	}
}
@Named
class SomeDependency{
	
}


@ComponentScan
@Configuration
public class CDIContextLauncherApplication {
	 


	public static void main(String[] args) {
		  
		// TODO Auto-generated method stub
		//var game=new MarioGame();
		//var game= new SuperContraGame();
		try(var context=
				new AnnotationConfigApplicationContext(CDIContextLauncherApplication.class)){
			
			Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
			
			
			
			
		}
		
		
		
		
	}

}
