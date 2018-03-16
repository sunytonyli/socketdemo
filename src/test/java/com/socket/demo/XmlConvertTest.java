package com.socket.demo;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.Before;
import org.junit.Test;

import com.socket.demo.socketclient.entity.Son;
import com.socket.demo.socketclient.entity.User;
import com.socket.demo.socketclient.entity.UserSon;
import com.socket.demo.util.XMLUtil;

public class XmlConvertTest {

	private User<Son> user;

	@Before
	public void setUp() {
		long l = 10;
		Long longId = new Long(l);
		user = new User<Son>();
		Son son = new Son();
		son.setSonName("son");
		user.setUserName("father");
		user.setT(son);
	}

	@Test
	public void testObjectToXml() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(new User<Son>().getClass());
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(user, System.out);
	}

	@Test
	public void testXmlToObject() {
		String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + 
				"	<User>\n" + 
				"	    <userName>father</userName>\n" + 
				"	    <son>\n" + 
				"	        <sonName>son</sonName>\n" + 
				"	    </son>\n" + 
				"	</User>";
		User<Son> u =  XMLUtil.<User>convertXmlStrToObjectForT(User.class, xmlStr);
		//UserSon userSon = XMLUtil.convertXmlStrToObjectForT(UserSon.class, xmlStr);
		System.out.println(u.getT().getSonName());
	}
	
	

}
