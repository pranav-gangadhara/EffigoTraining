package com.in28minutes.learn_spring_framework.examples.a1;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
class YourBusinessClass{
	
	Dependency1 dependency1;
	
	Dependency2 dependency2;
	
    
	public YourBusinessClass(Dependency1 dependency1, Dependency2 dependency2) {
		super();
		System.out.println("ConstructorInjection");
		this.dependency1 = dependency1;
		this.dependency2 = dependency2;
	}

	/*@Autowired
	public void setDependency1(Dependency1 dependency1) {
		System.out.println("Setter Injection dependency 1");
		this.dependency1 = dependency1;
	}
    
	@Autowired
	public void setDependency2(Dependency2 dependency2) {
    	System.out.println("Setter Injection Dependency 2");
		this.dependency2 = dependency2;
	}*/

	public String toString() {
		return "Using " + dependency1 + "and" + dependency2;
	}
}

@Component
class Dependency1{
	
}

@Component
class Dependency2{
	
}


@ComponentScan
@Configuration
public class DepInjectionLauncherApplication {
	 


	public static void main(String[] args) {
		  
		// TODO Auto-generated method stub
		//var game=new MarioGame();
		//var game= new SuperContraGame();
		try(var context=
				new AnnotationConfigApplicationContext(DepInjectionLauncherApplication.class)){
			
			
			
			
			System.out.println(context.getBean(YourBusinessClass.class));
			
			
			
		}
		
		
		
		
	}

}
