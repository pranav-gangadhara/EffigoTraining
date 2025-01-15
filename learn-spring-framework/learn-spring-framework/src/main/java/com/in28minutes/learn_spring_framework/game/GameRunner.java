package com.in28minutes.learn_spring_framework.game;


public class GameRunner {
   GamingConsole s;
   public GameRunner(GamingConsole s) {
	   this.s=s;
   }
   public void run() {
	   System.out.println("Game Running" + s);
	   s.up();
	   s.down();
   }
}
