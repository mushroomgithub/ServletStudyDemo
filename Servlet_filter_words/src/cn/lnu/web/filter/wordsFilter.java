package cn.lnu.web.filter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
//过滤敏感词汇的过滤器
public class wordsFilter implements Filter {
	private List<String> banWords = new ArrayList();//取出的每一行数据相当于一个正则表达式
	private List<String> auditWords = new ArrayList();
	private List<String> replaceWords = new ArrayList();
	
	public void init(FilterConfig filterConfig) throws ServletException {
		try{
			String path = wordsFilter.class.getClassLoader().getResource("cn/lnu/words").getPath();
			File files[] =  new File(path).listFiles();
			for(File file : files){
				if(!file.getName().endsWith(".txt")){
					continue;
				}
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line = null;
				while((line=br.readLine())!=null){
					String s[] = line.split("\\|");
					if(s.length!=2){
						continue;
					}
					if(s[1].trim().equals("1")){
						banWords.add(s[0].trim());
					}
					
					if(s[1].trim().equals("2")){
						auditWords.add(s[0].trim());
					}
					
					if(s[1].trim().equals("3")){
						replaceWords.add(s[0].trim());
					}
				}
			}
			System.out.println("haha");
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//处理拦截下的请求中的数据里有没有敏感词汇
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) resp;
		
		//检查提交数据中是否含有禁用词
		Enumeration e=request.getParameterNames();
		while(e.hasMoreElements()){
			String name=(String) e.nextElement();
			String data=request.getParameter(name);
			String regdata=data.replaceAll(" +", "");//将获得数据中的存在的一个或多个空格，替换为"",即去除空格
			for(String regex:banWords){
				Pattern pattern=Pattern.compile(regex);//将字符格式的正则表达式编译为对象格式的正则表达式
				Matcher match=pattern.matcher(regdata);//用这个正则表达式对象去匹配字符串data
				if(match.find()){//如果字符串data中存在可以被pattern正则对象匹配的子串，find函数就返回true，否则返回false
					request.setAttribute("message", "文章中包含非法词汇，请检查后再提交");
					request.getRequestDispatcher("/message.jsp").forward(request, response);
					return;
				}
			}
		}
		
		//检查审核词，出现审核词，高亮处理，这是原有的request就没有高亮功能，需要增强之后再放行
		
		//检查替换词，也是放行，也需要增强request
		chain.doFilter(new MyRequest(request), response);
	}
	
	class MyRequest extends HttpServletRequestWrapper{
		private HttpServletRequest request;
		public MyRequest(HttpServletRequest request){
			super(request);
			this.request=request;
		}
		@Override
		public String getParameter(String name) {//使得放行之后到servletDemo1中获得数据是高亮之后的数据
			// TODO Auto-generated method stub
			String data=request.getParameter("resume");//首先先获得没有高亮之前的数据
			//检查这个数据中有没有审核词，有的话，高亮处理
			if(data==null){
				return null;
			}
			
			for(String regex:auditWords){
				Pattern pattern=Pattern.compile(regex);//java里的Pattern对象代表一个正则表达式
				Matcher m=pattern.matcher(data);//返回一个表示匹配结果的匹配器
				if(m.find()){//表示数据中存在审核词汇，比如说数据是“我有一把仿真手枪，你要电鸡吗？”,这个仿真手枪是审核词
					String value=m.group();//表示找到客户机提交的数据中匹配正则表达式的数据，即是仿真手枪 等
					//将原始数据替换成高亮数据,然后用data再将每次替换结果之后的数据记住
					data=data.replaceAll(regex, "<font color='red'>"+value+"</font>");
				}		
			}	
			
			//检查是否有替换词
			for(String regex:replaceWords){
				Pattern pattern=Pattern.compile(regex);//java里的Pattern对象代表一个正则表达式
				Matcher m=pattern.matcher(data);//返回一个表示匹配结果的匹配器
				if(m.find()){//表示数据中存在替换
					//将将包含替换词的数据用****替换掉
					data=data.replaceAll(regex, "****");
				}		
			}	
			//我有一把仿真手枪，你要电鸡和****吗？，增强之后的结果是，仿真手枪和电鸡等审核词高亮成红色，四大舰队替换词，替换词****
			return data;
		}	
	}
	public void destroy() {
		// TODO Auto-generated method stub
	}
}
