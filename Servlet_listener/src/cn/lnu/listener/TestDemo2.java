package cn.lnu.listener;
//测试观察者模式实现的自己创建的事件源对象想要被别人监听
public class TestDemo2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person p=new Person();
		p.registerListener(new myListener1());
		
		p.eat();
		p.run();
	}
	
}

class myListener1 implements PersonListener{

	public void doeat(Event e) {
		// TODO Auto-generated method stub
		System.out.println(e.getPerson()+"你怎么就知道吃啊，属猪的吗？");
	}

	public void dorun(Event e) {
		// TODO Auto-generated method stub
		System.out.println(e.getPerson()+"你真是太爱运动了！");
	}
	
}