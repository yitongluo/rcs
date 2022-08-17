package cn.lhsearch.rcs.security.jwt;

import java.io.Serializable;

public class JwtRequest implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;
	
	private String code;
	
	//need default constructor for JSON Parsing
	public JwtRequest()
	{
	}

	public JwtRequest(String code) {
		this.setCode(code);
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}