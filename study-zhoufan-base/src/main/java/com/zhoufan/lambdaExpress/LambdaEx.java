package com.zhoufan.lambdaExpress;

import com.zhoufan.lombok.vo.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * lambda表达式练习
 */
public class LambdaEx{

    public static void main(String[] args) {
    //        innerClassWay();
//    lambdaWay();
//    lambdaForEach();
//        predicateAndConsumer();
//        lambdaStream();

        optionalTest();
    }

    /**
     * lambda 表达式
     */
    public static void lambdaEXpress(){
        new Thread( () -> System.out.println("In Java8, Lambda expression rocks !!") ).start();
    }

    /**
     * 内部类方式
     */
    public static void innerClassWay(){
        Operation<Integer> operation=new Operation<Integer>() {
            @Override
            public Integer add(Integer a, Integer b) {
                return a+b;
            }

        };
        System.out.println(operation.add(5,6));
    }

    /**
     * lambda方式
     */
    public static void  lambdaWay(){
        Operation<Integer> operation=(Integer a,Integer b)-> a+b;
        Operation<Integer> operation1=( a, b)-> a*b;
        Operation<String> operation2=( a, b)-> a+b;
        Operation<String> operation3=( a, b)->{
            System.out.println("语句方式");
            return a+""+b;
        };

        System.out.println("a+b="+operation.add(6,7));
        System.out.println("a*b="+operation1.add(6,7));
        System.out.println("a+b="+operation2.add("111111","asdasda"));
        System.out.println("语句="+operation3.add("111111","asdasda"));
    }


    public static void lambdaForEach() {
        List<Student> list=getList();
        /**
         * lambda表达式方式
         */
        list.forEach(s->{
            System.out.println(s);
        });

    }


    /**
     * Predicate  Consumer 练习
     */
    public static void predicateAndConsumer(){
        List<Student> list=getList();

        Predicate<Student> predicate= s->s.getName().startsWith("");
        Consumer<Student> consumer= s-> System.out.println(s.getName());

        //        list.forEach(consumer);

        list.forEach(s->{
            if(predicate.test(s)){
                consumer.accept(s);
            }
        });
    }

    public static void lambdaStream() {
        List<Student> list=getList();
        //list 大小
        System.out.println("list.stream.cout="+list.stream().count());
        //使用distinct 必须要重写equals 和hashCode方法
        System.out.println("list.stream.distinct.count="+list.stream().distinct().count());
        //过滤姓名前缀包含zhang的字符串并进行打印 这种方式更直接
//        list.stream().filter(s->s.getName().startsWith("")).forEach(s-> System.out.println(s));
        //与上面等价
        list.stream().filter(s->s.getName().startsWith("zhong")).forEach(System.out::println);
    }

    /**
     *  Optional<T> 测试
     */
    public static void optionalTest(){
        Student student=getList().get(0);
        Optional<Student> optional= Optional.ofNullable(student);

        if(optional.isPresent()){
            System.out.println("optional.isPresent()方式："+optional.get());
        }
        System.out.print("optional.ifPresent()方式：");
        optional.ifPresent(System.out::println);

        String message=null;
        //夺命连环null检查 第一个map 先对student对象判空
//        if(student!=null){
//            String sno= student.getSNo();
//            if(sno!=null){
//                message=sno.toUpperCase();
//            }else{
//                message="abc";
//            }
//        }else{
//            message="abc";
//        }
        //上面和下面语句等价
        message=optional.map(s->s.getSNo()).map(sno->sno.toUpperCase()).orElse("abc");
        System.out.println(message);
    }


    /**
     * 创建list
     * @return
     */
    private static List<Student> getList(){
        List<Student> list=new ArrayList<>();
        list.add(Student.builder().age(23).name("zhangsan").build());
        list.add(Student.builder().age(24).name("lisi").build());
        list.add(Student.builder().age(25).name("wangwu").build());
        list.add(Student.builder().age(25).name("wangwu").build());

        return  list;
    }

}
