package br.com.alura.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.forum.modelo.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

	List<Topico> findByCursoNome(String nomeCurso);
	
//	É possível não seguir o padrão de nomenclatura do SpringData como estamos fazendo acima.
//	Porém, será necessário escrever a Query em JPQL, conforme exemplo abaixo:
//	@Query("SELECT t FROM Topico t WHERE t.curso.nome = :nomeCurso")
//	List<Topico> encontreCursoPeloNome(@Param("nomeCurso") String nomeCurso);*/	
}
