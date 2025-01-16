package com.in28minutes.learn_spring_framework.examples.d1;


import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
class ClassA{
	
}
@Component
@Lazy
class ClassB{
	private ClassA classA;

	public ClassB(ClassA classA) {
		super();
		System.out.println("Some Initialization Logic");
		this.classA = classA;
	}
	public void display() {
		System.out.print("Hi This is Pranav");
	}
}

@ComponentScan
@Configuration
public class LazyInitializationLauncherApplication {
	 


	public static void main(String[] args) {
		  
		// TODO Auto-generated method stub
		//var game=new MarioGame();
		//var game= new SuperContraGame();
		try(var context=
				new AnnotationConfigApplicationContext(LazyInitializationLauncherApplication.class)){
			
			
			
			
			context.getBean(ClassB.class).display();
			Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
			
		}
		
		
		
		
	}

}
