package test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * User:  maktub
 * Date:   16/1/28 下午4:28
 */
public class GsonDemo {

    public static void main(String[] args) {

        Gson gson = new Gson();
        List<String> list = gson.fromJson("", new TypeToken<List<String>>(){}.getType());

        System.out.println(list.size());
    }

}
