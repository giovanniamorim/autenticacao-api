package com.sindifisco.portal.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sindifisco.portal.model.Grupo;
import com.sindifisco.portal.repository.GrupoRepository;

@Service
public class GrupoService {
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	public List<Grupo> listarTodos(){
		return grupoRepository.findAll();
	}

	public Grupo buscaPorId(Long id) {
		return grupoRepository.findOne(id);
	}

	public Grupo salvar(Grupo grupo) {
		return grupoRepository.save(grupo);
	}

	public Grupo atualizar(Long id, Grupo grupo) {
		Grupo grupoAtualizado = buscaPorId(id);
		BeanUtils.copyProperties(grupo, grupoAtualizado, "id");
		return grupoRepository.save(grupoAtualizado);
	}

	public void delete(Long id) {
		grupoRepository.delete(id);
		
	}
	
}
