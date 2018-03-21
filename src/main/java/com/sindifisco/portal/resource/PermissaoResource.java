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
import com.sindifisco.portal.model.Permissao;
import com.sindifisco.portal.service.PermissaoService;

@RestController
@RequestMapping("/permissoes")
public class PermissaoResource {
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private PermissaoService permissaoService;
	
	@GetMapping
	public ResponseEntity<List<Permissao>> listaTodas(){
		List<Permissao> lista = permissaoService.listarTodas();
		return ResponseEntity.ok().body(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Permissao> buscaPorId(@PathVariable Long id){
		Permissao permissao = permissaoService.buscaPorId(id);
		return permissao != null ? ResponseEntity.ok(permissao) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Permissao> criar(@Valid @RequestBody Permissao permissao, HttpServletResponse response){
		Permissao novaPermissao = permissaoService.salvar(permissao);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, novaPermissao.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(novaPermissao);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Permissao> atualizar(@Valid @PathVariable Long id, @RequestBody Permissao permissao){
		Permissao permissaoAtualizada = permissaoService.atualizar(id, permissao);
		return ResponseEntity.ok(permissaoAtualizada);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id){
		permissaoService.delete(id);
		
	}

}
