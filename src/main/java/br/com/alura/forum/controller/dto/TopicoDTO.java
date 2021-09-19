package br.com.alura.forum.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.forum.modelo.Topico;

public class TopicoDTO {

	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao;

	public TopicoDTO(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public static List<TopicoDTO> converter(List<Topico> topicos) {
//		List<TopicoDTO> topicosDTO = new ArrayList<>();
//		for (Topico topico : topicos) {
//			topicosDTO.add(new TopicoDTO(topico));
//		}
//		return topicosDTO;

//		return topicos.stream().map(topico -> new TopicoDTO(topico)).collect(Collectors.toList());

		return topicos.stream().map(TopicoDTO::new).collect(Collectors.toList());

	}

}
