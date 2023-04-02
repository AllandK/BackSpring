package com.example.demo.services;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.models.UsuarioModel;
import com.example.demo.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    
@Autowired
UsuarioRepository usuarioRepository;



   public ArrayList<UsuarioModel> obtenerUsuario(){
     return (ArrayList<UsuarioModel>)  usuarioRepository.findAll();


   }

    

   public UsuarioModel guardarUsuario(UsuarioModel usuario){
     return usuarioRepository.save(usuario);

   }

   public Optional<UsuarioModel> obtenerPorId(Long id){

     return usuarioRepository.findById(id);
   }

  

   public ArrayList<UsuarioModel> obtenerPorPrioridad(Integer prioridad){

     return usuarioRepository.findByPrioridad(prioridad);

   }

   public UsuarioModel actualizar(UsuarioModel usuario ,Long id){

        return  usuarioRepository.findById(id)
                       .map(
                               user ->{
                                   user.setEmail(usuario.getEmail());
                                   user.setNombre(usuario.getNombre());
                                   return usuarioRepository.save(user);
                               }


                       ).get();
         


  }


   public boolean eliminarUsuario(Long id){
   try {
    usuarioRepository.deleteById(id);
    return true;
   } catch (Exception err) {
   return false;
   }
  }



 
}
