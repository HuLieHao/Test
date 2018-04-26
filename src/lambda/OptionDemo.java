package lambda;

import java.util.Optional;

/**
 * User:  maktub
 * Date:   16/7/4 下午6:52
 */
public class OptionDemo {

    static class Demo {

        private static Optional<String> name;

        public static Optional<String> getName() {
            return name;
        }
    }

    public static void main(String[] args) throws Throwable {


        Optional name = Optional.of("dota");
        Optional empty = Optional.ofNullable(null);

//        System.out.println(name.isPresent());
//
//        System.out.println(empty.isPresent());
//
//        System.out.println(name.get());

//        System.out.println(empty.get());

        //ifPresent没有返回值
//        name.ifPresent(value -> System.out.println(value));
//
//        empty.ifPresent(value -> System.out.println(value));
//
//        System.out.println(name.orElse("name There is no value"));
//        System.out.println(empty.orElse("empty There is no value"));

//        System.out.println(name.orElseGet(() -> "Dota default value"));
//        System.out.println(empty.orElseGet(() -> {
//            return "empty default value";
//        }));


//        System.out.println(name.orElseThrow(() -> new ValueAbsentException()));

        System.out.println(name.map(value -> {System.out.println(value);return value;}).get());

    }

}

class ValueAbsentException extends Throwable {

    public ValueAbsentException() {
        super();
    }

    public ValueAbsentException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return "No value present in the Optional instance";
    }
}
