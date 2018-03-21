package com.sindifisco.portal.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sindifisco.portal.event.RecursoCriadoEvent;
import com.sindifisco.portal.model.Grupo;
import com.sindifisco.portal.service.GrupoService;

@RestController
@RequestMapping("/grupos")
public class GrupoResource {
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private GrupoService grupoService;
	
	@GetMapping
	public ResponseEntity<List<Grupo>> listarTodos(){
		List<Grupo> lista = grupoService.listarTodos();
		return ResponseEntity.ok().body(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Grupo> buscaPorId(@PathVariable Long id){
		Grupo grupo = grupoService.buscaPorId(id);
		return grupo != null ? ResponseEntity.ok(grupo) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Grupo> criar(@Valid @RequestBody Grupo grupo, HttpServletResponse response){
		Grupo novoGrupo = grupoService.salvar(grupo);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, novoGrupo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(novoGrupo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Grupo> atualizar(@Valid @PathVariable Long id, @RequestBody Grupo grupo){
		Grupo grupoAtualizado = grupoService.atualizar(id, grupo);
		return ResponseEntity.ok(grupoAtualizado);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		grupoService.delete(id);
	}

}
