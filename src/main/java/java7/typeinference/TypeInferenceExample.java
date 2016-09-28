package java7.typeinference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// http://docs.oracle.com/javase/7/docs/technotes/guides/language/type-inference-generic-instance-creation.html

public class TypeInferenceExample {

    public static void main(String[] args) {
        List<String> strs = new ArrayList<>();//  no need of "new ArrayList<String>()".
        strs.add("a");
        strs.add("b");
        strs.add("c");
        System.out.println(strs);
        
        // The following statement should fail since addAll expects Collection<? extends String>
        //strs.addAll(new ArrayList<>());       
        
        // The following statements compile:
        List<? extends String> list2 = new ArrayList<>();
        strs.addAll(list2);
        
        Map<String, List<String>> myMap = new HashMap<>(); //  no need of "new HashMap<String, List<String>>()".
        myMap.put("a", strs);
        
        //Note that to take advantage of automatic type inference during generic class instantiation, you must specify the diamond. In the following example, the compiler generates an unchecked conversion warning because the HashMap() constructor refers to the HashMap raw type, not the Map<String, List<String>> type:
        Map<String, List<String>> myMap1 = new HashMap(); // unchecked conversion warning

        
    }

}
