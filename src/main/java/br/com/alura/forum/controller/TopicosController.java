package br.com.alura.forum.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.controller.dto.DetalhesTopicoDTO;
import br.com.alura.forum.controller.dto.TopicoDTO;
import br.com.alura.forum.controller.form.AtualizacaoTopicoForm;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

	@Autowired
	private TopicoRepository topicoRepository;

	@Autowired
	private CursoRepository cursoRepository;

	// @ResponseBody
	@GetMapping
	public List<TopicoDTO> lista(String nomeCurso) {
		if (nomeCurso != null) {
			return TopicoDTO.converter(this.topicoRepository.findByCursoNome(nomeCurso));
		} else {
			return TopicoDTO.converter(this.topicoRepository.findAll());
		}
	}

//	@GetMapping("/{id}")
//	public TopicoDTO detalhar(@PathVariable Long id) {
//		Topico topico = topicoRepository.getById(id);
//		return new TopicoDTO(topico);
//	}

	@GetMapping("/{id}")
	public ResponseEntity<DetalhesTopicoDTO> detalhar(@PathVariable Long id) {
		//Topico topico = this.topicoRepository.getById(id);
		Optional<Topico> topicoOptional = this.topicoRepository.findById(id);
		
		if (topicoOptional.isPresent()) {
			return ResponseEntity.ok(new DetalhesTopicoDTO(topicoOptional.get()));
		} 	
		return ResponseEntity.notFound().build();
	}

	// Este Metodo retornará 201(created caso criado com sucesso, portanto é
	// necessário retornar o Topico criado)
	// Para iso, utilizaremos o ResponseEntity, do Spring, do tipo Topico. Será
	// necessário também informar a URI.
	@PostMapping
	@Transactional
	public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoForm topicoForm,
			UriComponentsBuilder uriBuilder) {
		Topico topico = topicoForm.converter(cursoRepository);
		this.topicoRepository.save(topico);
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDTO(topico));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id, @Valid @RequestBody AtualizacaoTopicoForm form) {
			Optional<Topico> topicoOptional = this.topicoRepository.findById(id);
			
			if (topicoOptional.isPresent()) {
				Topico topico = form.atualizar(id, this.topicoRepository);
				return ResponseEntity.ok(new TopicoDTO(topico));
			} 	
			return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		Optional<Topico> topicoOptional = this.topicoRepository.findById(id);
		
		if (topicoOptional.isPresent()) {
			this.topicoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		} 	
		return ResponseEntity.notFound().build();
	}

}