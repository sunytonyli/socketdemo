package com.socket.demo.util;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


/**
 * @author songyu.li
 * 
 */
public class XMLUtil {

		/**
		 * 将String类型的xml转换成对象
		 * @param clazz
		 * @param xmlStr
		 * @return
		 */
		public static Object convertXmlStrToObject(Class clazz, String xmlStr) {
			Object xmlObject = null;
			try {
				JAXBContext context = JAXBContext.newInstance(clazz);
				//进行将Xml转成对象的核心接口
				Unmarshaller unmarshaller = context.createUnmarshaller();
				StringReader sr = new StringReader(xmlStr);
				xmlObject = unmarshaller.unmarshal(sr);
			} catch (JAXBException e) {
				e.printStackTrace();
			}
			return xmlObject;
		}
		
		/**
		 * 将String类型的xml转换成对象
		 * 
		 * @param clazz
		 * @param xmlStr
		 * @return
		 */
		public static <T> T convertXmlStrToObjectForT(Class<T> clazz, String xmlStr) {
			Object xmlObject = null;
			try {
				JAXBContext context = JAXBContext.newInstance(clazz);
				// 进行将Xml转成对象的核心接口
				Unmarshaller unmarshaller = context.createUnmarshaller();
				StringReader sr = new StringReader(xmlStr);
				xmlObject = unmarshaller.unmarshal(sr);
			} catch (JAXBException e) {
				e.printStackTrace();
			}
			return (T)xmlObject;
		}
	
}
