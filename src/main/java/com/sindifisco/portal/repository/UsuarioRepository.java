package com.sindifisco.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sindifisco.portal.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
