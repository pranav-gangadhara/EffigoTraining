package com.in28minutes.learn_spring_framework.game;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GameRunner {
   GamingConsole s;
   public GameRunner(@Qualifier("SuperContraGameQualifier") GamingConsole s) {
	   this.s=s;
   }
   public void run() {
	   System.out.println("Game Running" + s);
	   s.up();
	   s.down();
	   s.left();
	   s.right();
   }
}
