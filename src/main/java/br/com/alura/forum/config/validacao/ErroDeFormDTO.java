package br.com.alura.forum.config.validacao;

public class ErroDeFormDTO {

	private String erro;
	private String campo;
	
	public ErroDeFormDTO(String erro, String campo) {
		super();
		this.erro = erro;
		this.campo = campo;
	}

	public String getErro() {
		return erro;
	}

	public String getCampo() {
		return campo;
	}
	
}
