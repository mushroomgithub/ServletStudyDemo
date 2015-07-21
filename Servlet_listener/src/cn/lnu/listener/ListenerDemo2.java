package cn.lnu.listener;
//观察者设计模式（Observer设计模式），demo1是演示我们在已有事件对象上发生的监听事件，我们只需要写事件监听器类即可然后注册给事件源，
//也就是说demo1是演示自己写个监听器，去监听别人的对象上发生的行为；而这个demo2则演示我们自己设计一个事件对象，想被别人监听，这就需要使用观察者设计模式了

class Person{//比如说设计一个人想要被别人监听,也即是人这个对象的下面这两个方法我们想要被别人监听，所以需要允许别人向人这个对象上注册一个监听器
	
	private PersonListener listener;//定义一个变量记住传递进来的事件监听器
	
	public void run(){
		if(listener!=null){//判断人家又没有传递监听器进来，如传递进来了,则在调用run方法之前，先调用事件监听器的对应方法先处理这个方法调用
			Event e=new Event(this);
			this.listener.dorun(e);
		}
		System.out.println("run");
	}
	
	public void eat(){
		if(listener!=null){
			Event e=new Event(this);
			this.listener.doeat(e);
		}
		System.out.println("eat");
	}
	
	public void registerListener(PersonListener listener){//允许别人向人这个对象上注册一个监听器，监听人的上面两个动作，事件监听器是有人这个事件源对象调用的，之后事件源的两个方法会调用事件监听器的方法，因此我们还需要对外暴露一个接口
		this.listener=listener;
	}
}
/*人这个对象上的两个方法想被人监听，就对外暴露一个相对应的接口，别人想要监听这个对象，需要实现这个接口，就相当于创建一个监听器，然后在该对象上注册你实现这个接口的事件监听器，
 * 在这个对象上注册监听器之后，人这个对象就会在处理相应的方法之前先去调用监听器的方法进行处理，然后再去调用人这个对象的方法体
 * */
interface PersonListener{//别人实现这个接口，就相当于写了个事件监听器
	public void dorun(Event e);
	
	public void doeat(Event e);
}

//还有设计一个事件对象，主要用于封装事件源
class Event{
	private Person person;

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Event(Person person) {
		super();
		this.person = person;
	}
	
	
	
}
