package cn.lnu.classloader;

import sun.net.spi.nameservice.dns.DNSNameService;

public class Demo1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Demo1 d=new Demo1();
		ClassLoader cl=Demo1.class.getClassLoader();//通过类的字节码调用这个类的类加载器，返回该类的类加载器
		System.out.println(cl);//sun.misc.Launcher$AppClassLoader@7259da
		System.out.println(d.getClass().getClassLoader());//sun.misc.Launcher$AppClassLoader@7259da
		
		System.out.println(DNSNameService.class.getClassLoader());//DNSNameService是java扩展jar包里的一个api类 sun.misc.Launcher$ExtClassLoader@16930e2
		
		System.out.println(String.class.getClassLoader());//null,
	}

}
