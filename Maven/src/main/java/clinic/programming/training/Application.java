package clinic.programming.training;
import java.util.*;



public class Application {
    
    public Application() {
        System.out.println ("Inside Application");
    }
    public void greet(){
        List<Integer> arr=new ArrayList<>();
        arr.add(1);
        for(int a:arr){
            System.out.println(a);
        }
    }

    // method main(): ALWAYS the APPLICATION entry point
    public static void main (String[] args) {
    	System.out.println ("Starting Application");
	Application app = new Application();
    app.greet();
    }
}