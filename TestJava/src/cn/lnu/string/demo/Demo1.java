package cn.lnu.string.demo;

public class Demo1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] keywords = {"微软", "计算机", "亚洲"};
		String[] text = {"微软", "亚洲", "研究院", "成立", "于", "1998", "年", "，", "我们",
				"的", "使命", "是", "使", "未来", "的", "计算机", "能够", "看", "、", "听",
				"、", "学", "，", "能", "用", "自然语言", "与", "人类", "进行", "交流", "。",
				"在", "此", "基础", "上", "，", "微软", "亚洲", "研究院", "还", "将", "促进",
				"计算机", "在", "亚太", "地区", "的", "普及", "，", "改善", "亚太", "用户", "的",
				"计算", "体验", "。" };
		//String[] keywords2={"微软","计算机","亚洲"};
		String[] keywords2=keywords;
		System.out.println("keywords VS text in hashcode:"+(keywords.hashCode()==text.hashCode()));
		System.out.println("keywords VS keywords2 in hashcode:"+(keywords.hashCode()==keywords2.hashCode()));
		System.out.println("<--------------------------------->");
		String s = new String("sb");
		String ss = new String("sb");
		String s2="sb";
		System.out.println("s VS ss in hashcode:"+(s.hashCode()==ss.hashCode()));
		System.out.println("s VS s2 in hashcode:"+(s.hashCode()==s2.hashCode()));
	}

}
