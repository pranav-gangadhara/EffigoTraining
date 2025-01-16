package com.in28minutes.learn_spring_framework.game;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;



@ComponentScan("com.in28minutes.learn_spring_framework.game")
@Configuration
public class GamingAppLauncherApplication {
	 


	public static void main(String[] args) {
		  
		// TODO Auto-generated method stub
		//var game=new MarioGame();
		//var game= new SuperContraGame();
		try(var context=
				new AnnotationConfigApplicationContext(GamingAppLauncherApplication.class)){
			
			context.getBean(GameRunner.class).run();
			context.getBean(GamingConsole.class).up();
			
		}
		
		
		
		
	}

}
