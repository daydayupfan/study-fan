package classload;

import org.springframework.boot.SpringApplication;
import sun.net.spi.nameservice.dns.DNSNameService;

/**
 * 类加载器学习
 */
public class ClassLoadStudy {

    public static void main(String[] args) {
//        classload();

        classLoadPath();

    }

    /**
     * 查看各加载器加载目录
     */
    private static void classLoadPath() {
        String boot = System.getProperty("sun.boot.class.path");
        System.out.println(boot.replace(";", System.lineSeparator()));
        System.out.println("----------------------------------------");

        String ext = System.getProperty("java.ext.dirs");
        System.out.println(ext.replace(";", System.lineSeparator()));
        System.out.println("----------------------------------------");

        String classPath = System.getProperty("java.class.path");
        System.out.println(classPath.replace(";", System.lineSeparator()));
        System.out.println("----------------------------------------");
    }

    /**
     * classload 使用顺序  自定义classload ->用户app(除开自定义和 jdk中的) -> extension（jdk库除开 bootstrap以外的都用ext）-> bootstrap(c++实现 rt charset.jar 等核心库)
     * 每一个classload的顶级classload 都是bootstrap
     * 查找class 是按照先去 自定义->app->extension->bootstrap的顺序去找
     * 每一层找不到都往父层去找 都找不到就一层一层往下通知 都找不到 classNotFoundException
     * 注意：这里类加载器之间没有继承关系
     */
    private static void classload() {
        System.out.println(String.class.getClassLoader());
        System.out.println(ClassLoadStudy.class.getClassLoader());
        System.out.println(ClassLoadStudy.class.getClassLoader().getClass().getClassLoader());
        System.out.println(DNSNameService.class.getClassLoader());
        System.out.println(DNSNameService.class.getClassLoader().getClass().getClassLoader());
        System.out.println(SpringApplication.class.getClassLoader());
    }
}
