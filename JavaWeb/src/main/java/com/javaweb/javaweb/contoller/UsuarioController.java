package com.javaweb.javaweb.contoller;

import com.javaweb.javaweb.model.Usuario;
import com.javaweb.javaweb.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired  //Injeção de dependência
    private UsuarioRepository usuarioRepository;

    public String greetingText(@PathVariable String name){
        return "Hello " + name + "!";
    }


    /*GET - LISTAR TODOS USUÁRIO*/
    @GetMapping(value = "listatodos") /*Metodo buscar todos*/
    @ResponseBody /*Retorna os dados para o corpo da resposta*/
    public ResponseEntity<List<Usuario>> listaUsuario(){

        List<Usuario> usuarios = usuarioRepository.findAll(); /*Executa a consulta no banco de dados*/

        return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);/*Retorna a lista em Json*/
    }

    /*POST - SALVAR USUÁRIO*/
    @PostMapping(value ="salvar") /*mapeia a url*/
    @ResponseBody /*Descrição da resposta*/

    public ResponseEntity<Usuario> salvar (@RequestBody Usuario usuario ){ /*Recebe os dados para Salvar*/

        Usuario saveuser = usuarioRepository.save(usuario);

        return new ResponseEntity<Usuario>(saveuser, HttpStatus.CREATED);

    }

    /*POST - ATUALIZAR USUÁRIO*/
    @PutMapping(value ="atualizar") /*mapeia a url*/
    @ResponseBody /*Descrição da resposta*/

    public ResponseEntity<?> atualizar (@RequestBody Usuario usuario ){ /*Recebe os dados para Atualizar*/

        /* Resposta caso não seja enviado um ID*/

        if(usuario.getId() == null){
            return new ResponseEntity<String>("Id não foi informado para atualização", HttpStatus.OK);
        }

        Usuario atualizauser = usuarioRepository.saveAndFlush(usuario);

        return new ResponseEntity<Usuario>(atualizauser, HttpStatus.OK);

    }


    /*DELETAR USUÁRIO*/
    @DeleteMapping(value ="delete") /*mapeia a url*/
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<String> delete (@RequestParam Long iduser ){ /*Recebe os dados para Deletar*/

            usuarioRepository.deleteById(iduser);

        return new ResponseEntity<String>("User deletado com sucesso", HttpStatus.OK);
    }

    /* BUSCAR USUÁRIOS POR ID*/
    @GetMapping(value ="buscaruserid") /*mapeia a url*/
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<Usuario> buscaruserid (@RequestParam(name = "iduser") Long iduser ){ /*Recebe os dados para Consulta*/

        Usuario usuario = usuarioRepository.findById(iduser).get();

        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }

    /* BUSCAR USUÁRIOS POR NOME
    * Criado a consulta no Repository*/
    @GetMapping(value ="buscarpornome") /*mapeia a url*/
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<List<Usuario>> buscaruserid (@RequestParam(name = "name") String name ){ /*Recebe os dados para Consulta*/
        List<Usuario> usuario = usuarioRepository.buscarPorNome(name.trim().toUpperCase());
        /*trim pesquisar por espaço; toUpperCase - Pesquisar por maiúsculo - Implementado na consulta*/

        return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
    }


}
