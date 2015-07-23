package cn.lnu.proxy;

public class Liyuchun implements Person {
	public String sing(String name){
		System.out.println("´º¸ç³ª"+name+"¸èÁË£¡");
		
		return "Ğ»Ğ»£¡";
	}
	
	public String dance(String name){
		System.out.println("´º¸çÌø"+name+"ÎèÁË£¡");
		return "·ÉÎÇ";
	}
}
