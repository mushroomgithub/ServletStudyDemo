package cn.lnu.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlUtils {
	
	private static String filepath;
	static{//静态代码块，只执行一次
		//获得文件users.xml的位置
		filepath=XmlUtils.class.getClassLoader().getResource("users.xml").getPath();
	}
	//获得xml文档
	public static Document getDocument() throws Exception{
		SAXReader reader = new SAXReader();
        Document document = reader.read(new File(filepath));
        return document;
	}
	//将内存中修改之后的数据写回到xml文档中
	public static void writeToXml(Document document) throws IOException{
		 // Pretty print the document to System.out
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        XMLWriter writer = new XMLWriter( new FileOutputStream(filepath), format );
        writer.write( document );
        writer.close();
	}
}
