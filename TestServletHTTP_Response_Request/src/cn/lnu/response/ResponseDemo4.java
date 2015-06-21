package cn.lnu.response;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//response输出随机图片
public class ResponseDemo4 extends HttpServlet {
	public static final int width=120;
	public static final int height=35;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//首先在内存中构建一副图像
		BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		//往这幅图像上写数据，首先获得这个图像对象
		Graphics g=image.getGraphics();
		//1，设置背景色
		setBackGround(g);
		//2,设置边框
		setBorder(g);
		//3，画干扰线
		drawRandomLine(g);
		//4,向图像上写随机数
		drawRandomNum((Graphics2D )g);
		//5，图形写给浏览器
		response.setContentType("image/jpeg");
		//发送控制浏览器不要缓存
		response.setDateHeader("expries", -1);
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("Pragma", "no-cache");
		ImageIO.write(image,"jpg",response.getOutputStream());
		
	}

	private void setBackGround(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
	}
	private void setBorder(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLUE);
		g.drawRect(1, 1, width-2, height-2);
	}
	
	private void drawRandomLine(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.GREEN);
		//画四条干扰线
		for(int i=0;i<5;i++){
			//随机获得干扰线起止坐标
			int x1=new Random().nextInt(width);//生成0-120之间的随机数
			int y1=new Random().nextInt(height);//生成0-25之间的随机数
			
			int x2=new Random().nextInt(width);//生成0-120之间的随机数
			int y2=new Random().nextInt(height);//生成0-25之间的随机数
			
			g.drawLine(x1, y1, x2, y2);
		}
	}
	
	private void drawRandomNum(Graphics2D g) {//为了给汉字添加旋转功能，这里使用Graphics2D 类
		// TODO Auto-generated method stub
		//设置字体颜色和字体
		g.setColor(Color.RED);
		g.setFont(new Font("宋体",Font.BOLD,20));
		
		//常用汉字
		String base="\u7684\u4e00\u4e86\u662f\u6211\u4e0d\u5728\u4eba\u4eec\u6709";
		int x=5;//表示汉字在图形上写的开始位置
		//生成四个随机汉字，需要知道汉字区间 [\u4e00-\u9fa5]
		for(int i=0;i<5;i++){
			//在常用汉字长度范围上随机选择一个0-base.length()-1的位置，然后返回该位置的汉字字符
			String ch=base.charAt(new Random().nextInt(base.length()))+"";
			int degree=new Random().nextInt()%30;//生成一个-30-30之间的随机数
			g.rotate(degree*Math.PI/180, x, 20);//设置字体旋转的弧度
			g.drawString(ch, x, 20);
			g.rotate(-degree*Math.PI/180, x, 20);//旋转完之后，为了下次旋转正常，还需要设置回之前的旋转弧度
			x+=30;
			
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
