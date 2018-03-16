package com.socket.demo.socketclient.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement(name="User")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Son.class})
public class User<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1592787428922101187L;
	
	private String userName;
	@XmlAnyElement(lax = true)
	private T t;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
	
}
