package com.javaweb.javaweb.repository;

import com.javaweb.javaweb.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /*CONSULTA PARA BUSCAR POR PARTE DO NOME*/
    @Query(value = "select u from Usuario u where upper( trim(u.nome)) like %?1%")

    /*trim pesquisar por espaço; toUpperCase - transformar em maiúsculo*/

    List<Usuario> buscarPorNome(String nome);


}
