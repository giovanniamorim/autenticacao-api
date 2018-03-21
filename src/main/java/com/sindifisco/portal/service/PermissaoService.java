package com.sindifisco.portal.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sindifisco.portal.model.Permissao;
import com.sindifisco.portal.repository.PermissaoRepository;

@Service
public class PermissaoService {
	
	@Autowired
	private PermissaoRepository permissaoRepository;
	
	public List<Permissao> listarTodas(){
		return permissaoRepository.findAll();
	}

	public Permissao buscaPorId(Long id) {
		return permissaoRepository.findOne(id);
	}

	public Permissao salvar(Permissao permissao) {
		return permissaoRepository.save(permissao);
	}

	public Permissao atualizar(Long id, Permissao permissao) {
		Permissao permissaoAtualizada = buscaPorId(id);
		BeanUtils.copyProperties(permissao, permissaoAtualizada, "id");
		return permissaoRepository.save(permissaoAtualizada);
	}

	public void delete(Long id) {
		permissaoRepository.delete(id);
	}

}
