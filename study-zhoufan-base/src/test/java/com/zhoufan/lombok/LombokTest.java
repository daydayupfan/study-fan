package com.zhoufan.lombok;

import com.zhoufan.lombok.vo.Student;
import org.junit.Test;

public class LombokTest {

    @Test
    public void testStudent(){

        Student student=new Student();
        student.setAge(15);
        student.setName("zhangsan");
        student.setSNo("123123");
        System.out.println(student);

        Student s1=new Student("wangwu","22222",2);
        System.out.println(s1);

        //先安装Lombok插件 不然导入依赖后编辑器中是不能正常使用的 在实体类中使用@Builder 之后便可使用
        Student student1=Student.builder().name("lisi").age(34).build();
        System.out.println(student1);

    }

    @Test
    public void test1(){
//        String a="123456";
//        System.out.println(a.substring(5));

//        String str1="2.3";
//        BigDecimal bd=new BigDecimal(str1);
//        System.out.println(bd);
//        String a="BKI00005942631907120004689999599502901 11      30JUNHKG  PEK  CA  6510 Y 13JUL0730 OK1PCYOFFHK     IN90                                 ";
//        System.out.println("最后四个="+a.substring(88, 103));
        
        String asda="BKS00000005241908090000019993055764779 ";

        System.out.println("len="+asda.length());
        System.out.println(asda.substring(25, 28));
        System.out.println(asda.substring(28, 31));
        System.out.println(asda.substring(31, 38));
            
    }

    
    
}
