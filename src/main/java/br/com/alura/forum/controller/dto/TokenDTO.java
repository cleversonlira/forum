package br.com.alura.forum.controller.dto;

public class TokenDTO {

	private String token;
	private String string;

	public TokenDTO(String token, String tipo) {
		this.token = token;
		this.string = tipo;
	}

	public String getToken() {
		return token;
	}

	public String getString() {
		return string;
	}
	
}
