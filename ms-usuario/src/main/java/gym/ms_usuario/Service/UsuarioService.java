package gym.ms_usuario.Service;

import gym.ms_usuario.Model.Usuario;
import gym.ms_usuario.Repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> obtenerTodos() {
        log.debug("Procesando solicitud de listado completo de usuarios en Servicio.");
        return repository.findAll();
    }

    public Usuario findById(int id) {
        return repository.findById(id).orElseThrow(() -> {
            log.error("ERROR: No se encontró el usuario con ID: {}", id);
            return new RuntimeException("Usuario no encontrado");
        });
    }

    public Usuario guardar(Usuario usuario) {
        log.info("Guardando datos del usuario en la base de datos.");
        return repository.save(usuario);
    }

    public void eliminar(int id) {
        if(!repository.existsById(id)){
            log.error("ERROR: Intento fallido de eliminar un usuario inexistente con ID: {}", id);
            throw new RuntimeException("El usuario no existe");
        }
        repository.deleteById(id);
    }
}