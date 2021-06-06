package com.zhoufan.classinit;

/**
 * 加载顺序 静态变量 局部变量 二者都有多个的情况 同类从上至下加载
 *
 */
public class ClassLoad {
	static int s;//成员变量，类变量
	int i;//成员变量，实例变量
	int j;//成员变量，实例变量
	{
		int i = 1;//非静态代码块中的局部变量 i
		i++;
		j++;
		s++;
	}
	public void test(int j){//形参，局部变量,j
		j++;
		i++;
		s++;
	}
	public static void main(String[] args) {//形参，局部变量，args
		// i=0 j=1 s=1
		ClassLoad obj1 = new ClassLoad();//局部变量，obj1
		// i=0 j=1 s=2
		ClassLoad obj2 = new ClassLoad();//局部变量，obj2

		// i=1 j=1 s=3
		obj1.test(10);
		// i=2 j=1 s=4
		obj1.test(20);
		// i=1 j=1 s= 5
		obj2.test(30);

		// 2 1 5
		System.out.println(obj1.i + "," + obj1.j + "," + obj1.s);
//		1 1 5
		System.out.println(obj2.i + "," + obj2.j + "," + obj2.s);
	}
}

