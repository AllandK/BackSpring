package com.example.demo.controllers;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.models.UsuarioModel;
import com.example.demo.repositories.UsuarioRepository;
import com.example.demo.services.UsuarioService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {


@Autowired
UsuarioService usuarioService;

@Autowired
UsuarioRepository usuarioRepository;

@GetMapping()
public ArrayList<UsuarioModel> obtenerUsuarios(){
return usuarioService.obtenerUsuario();

}

@PostMapping()
public UsuarioModel guardarUsuario(@RequestBody UsuarioModel usuario){

return this.usuarioService.guardarUsuario(usuario);

}

@PostMapping("/guardar")
ResponseEntity <UsuarioModel> guardarUsuario2(@RequestBody UsuarioModel usuario){

return new ResponseEntity<>(this.usuarioService.guardarUsuario(usuario),  HttpStatus.CREATED);

}


@GetMapping( path = "/{id}")
public Optional<UsuarioModel> obtenerUsuariosPorId(@PathVariable("id") Long id){
return usuarioService.obtenerPorId(id);

}

//// validar funcionamiento con un solo usuario , actualmente genera error por el findID
@PostMapping( path = "/actualizar/{id}")
public UsuarioModel modificarUsuariosPorId(@RequestBody UsuarioModel usuario,@PathVariable("id") Long id){


return usuarioService.actualizar(usuario ,id);

}


@GetMapping( "/query")
public ArrayList<UsuarioModel> obtenerUsuariosPorPrioridad(@RequestParam("prioridad")  Integer prioridad){
return usuarioService.obtenerPorPrioridad(prioridad);

}

@DeleteMapping( path = "/{id}")
public String eliminarPorId(@PathVariable("id") Long id){
    boolean ok = usuarioService.eliminarUsuario(id);
    if(ok){
       return  "Se elimino el usuario con id" + id;
    }
    else{
        return  "No se pudo eliminar el usuario con id" + id;
    }


}




    
}
