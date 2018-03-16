package com.socket.demo.socketclient.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="son")
@XmlAccessorType(XmlAccessType.FIELD)
public class Son implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2219529087643477360L;

	private String sonName;

	public String getSonName() {
		return sonName;
	}

	public void setSonName(String sonName) {
		this.sonName = sonName;
	}
	
}
