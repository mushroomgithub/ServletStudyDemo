package cn.lnu.listener;

import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
//
public class TestListenerDemo1 {

	/**
	 *面试题：请描述一下java事件监听机制：
	 *1.java的事件监听机制涉及到三个组件：事件源，事件监听器，事件对象
	 *2.当事件源上发生操作时，他将会调用事件监听器的一个方法，并且在调用这个方法时，会传递事件对象过来
	 *3.事件监听器有开发人员编写，开发人员在事件监听器中，通过事件对象可以拿到事件源，从而对事件源上的操作进行处理。
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Frame f=new Frame();
		f.setSize(400, 400);
		f.setVisible(true);
		
		f.addWindowListener(new myListener());//向事件源注册监听器
	}

}

class myListener implements WindowListener{//事件监听器类

	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowClosing(WindowEvent e) {//e传递过在事件源上触发事件时，传递过来的事件对象
		// TODO Auto-generated method stub
		Frame f=(Frame) e.getSource();
		f.dispose();
		System.out.println("窗口被关闭！");
	}

	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowIconified(WindowEvent e) {//最小化
		// TODO Auto-generated method stub
		Frame f=(Frame) e.getSource();
		System.out.println("最小化事件被触发！");
	}

	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}