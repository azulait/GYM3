package gym.ms_usuario.Controller;

import gym.ms_usuario.Model.Usuario;
import gym.ms_usuario.Service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/usuarios")
@Tag(name = "CRUD Usuarios",description = "gestion de Usuarios")
public class UsuarioController {
    @Autowired

    private UsuarioService service;

    @GetMapping
    @Operation(summary = "Obtener a todos los usuarios",description = "Se da una lista de los usuarios")
    public List<Usuario> listarTodos() {
        log.debug("DEBUG: Se encontraron los siguientes usuarios");
        return service.obtenerTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un usuario", description = "Retorna los datos de un usuario si coincide el ID con la base de datos")
    public Usuario buscarPorId(@PathVariable int id){
        log.info("INFORMACION: Administrador solicita informacion de usuario con id="+id);
        return service.findById(id);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo usuario", description = "Registra un usuario en la base de datos y genera su ID único.")
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        log.info("INFORMACION: Creando nuevo usuario con email: {}", usuario.getEmail());
        return service.guardar(usuario);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar usuario existente", description = "Modifica los datos de un usuario buscando por su ID.")
    public Usuario actualizarUsuario(@RequestBody Usuario usuario, @PathVariable int id) {
        log.info("INFORMACION: Actualizando usuario con ID: {}", id);
        usuario.setId(id);
        return service.guardar(usuario);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un usuario", description = "Borra físicamente el registro del usuario mediante su ID.")
    public void eliminarUsuario(@PathVariable int id) {
        log.warn("ADVERTENCIA: Eliminando usuario con ID: {}", id);
        service.eliminar(id);
    }
}
