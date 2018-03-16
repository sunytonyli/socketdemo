package com.socket.demo.socketclient.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="User")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserSon implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8318507779185196224L;

	private String userName;
	private Son son;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Son getSon() {
		return son;
	}
	public void setSon(Son son) {
		this.son = son;
	}
}
