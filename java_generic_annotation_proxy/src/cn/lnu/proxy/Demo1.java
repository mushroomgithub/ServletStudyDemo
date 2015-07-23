package cn.lnu.proxy;

public class Demo1 {

	/**
	 * 找春哥唱歌，跳舞需要先找其代理，代理对其拦截之后，完成要钱操作，再去调用真实对象的方法去唱歌跳舞
	 * @param args
	 */
	public static void main(String[] args) {
		LiyuchunProxy proxy=new LiyuchunProxy();//创建出李宇春这个对象的代理对象
		Person person=proxy.createProxy();//这个代理对象通过这个方法创建李宇春的代理，这个返回值就是李宇春的代理，负责拦截对李宇春的直接访问
		
		String singres=person.sing("爱你一万年");//实际调用的代理类中的invoke方法
		String danceres=person.dance("钢管舞");
		
		System.out.println(singres+"  "+danceres);
	}

}
