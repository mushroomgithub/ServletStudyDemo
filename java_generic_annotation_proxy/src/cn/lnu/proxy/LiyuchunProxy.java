package cn.lnu.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//这个类用于专门产生李宇春的代理,拦截对真实业务对象(春春)的直接访问
public class LiyuchunProxy {//代理类，用于产生chunchun代理
	private Person chunchun=new Liyuchun();//用于记住是产生Liyuchun的代理
	
	//Demo----->Person person=LiyuchunProxy.createProxy();    person.sing();   person.dance()
	public Person createProxy(){
		//newProxyInstance函数的第二个参数是一个接口，这里使用chunchun这个对象的接口创建春春的代理，第一个参数是一个代理类类加载器，这里使用本类的类加载器来创建这个代理，而第三个参数指定产生的这个代理干什么事情
		//实际开发中，使用这个方法来创建某个对象的代理对象，并且在下面invoke函数中实现如何拦截
		return (Person) Proxy.newProxyInstance(LiyuchunProxy.class.getClassLoader(), chunchun.getClass().getInterfaces(), new InvocationHandler(){
			
			//在这里指定产生的代理干什么事情,代理跟真实业务对象具有相同的行为
			
			/*
			 * proxy：把代理对象自身传递进来，这里就是person，即this，这个参数一般用户不多
			 * method：代表当前调用的方法
			 * args：调用方法的参数
			 * 
			 * 在外面创建的代理person的sing或者dance方法实际上都是调用这个invoke函数,并将相关参数传递给invoke函数
			 * */
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				String methodname=method.getName();//获得外面创建的代理person调用的方法
				
				if(methodname.equals("sing")){//这个代理的内部的这个方法实现拦截了对春哥(真实业务对象)的直接访问，然后收钱后再通知春哥唱歌跳舞
					System.out.println("拿一万块来，不然老子不唱歌！");
					//拦截之后，告诉春哥去唱歌，将春哥这个对象传递进来，参数继续向下传
					return method.invoke(chunchun, args);//返回值谢谢返回给外面调用方法的代理人
				}else if(methodname.equals("dance")){
					System.out.println("拿两万块来，不然老子不跳舞！");
					
					return method.invoke(chunchun, args);//返回值，飞吻
				}else{
					System.out.println("春哥不支持这个功能！");
				}
				return null;
			}
			
		});
	}
	
}
