import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User:  maktub
 * Date:   16/7/19 上午10:54
 */
public class SyntacticSugar {

//    public int method(List<String> list){
//        System.out.println("List String");
//        return 1;
//    }
    public boolean method(List<Integer> list){
        System.out.println("List Int");
        return true;
    }

    public static void main(String[] args) {

        Map<Integer,String> map = new HashMap<Integer,String>();
        map.put(1,"No.1");
        map.put(2,"No.2");
        System.out.println(map.get(1));
        System.out.println(map.get(2));
    }
}
