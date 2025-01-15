package clinic.programming.training;
import java.util.*;
import org.apache.commons.lang3.StringUtils;




public class Application {
 
    public int countWords(String words)
    {
        String[] seperateWords = StringUtils.split(words,' ');
        return (seperateWords==null) ? 0 : seperateWords.length;
    }
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
    int count=app.countWords("my name is pranav");
    System.out.println(count);
    }
}