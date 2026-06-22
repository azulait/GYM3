package gym.ms_ejercicios.Service;

import gym.ms_ejercicios.Model.Ejercicio;
import gym.ms_ejercicios.Repository.EjercicioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EjercicioService {

    @Autowired
    private EjercicioRepository repository;

    public Ejercicio findById(int id){
        return repository.findById(id).orElseThrow(() -> {
            log.error("ERROR: No se encontró la rutina de ejercicios con ID: {}", id);
            return new RuntimeException("Ejercicio no encontrado");
        });
    }

    public List<Ejercicio> findAll() {
        log.debug("DEBUG: Obteniendo la lista completa de ejercicios.");
        return repository.findAll();
    }

    public Ejercicio guardar(Ejercicio ejercicio) {
        log.info("INFORMACION: Guardando grupo muscular: {}", ejercicio.getGrupoMuscular());
        return repository.save(ejercicio);
    }

    public void eliminar(int id) {
        if(!repository.existsById(id)){
            log.error("ERROR: Intento fallido de eliminar un registro inexistente con ID: {}", id);
            throw new RuntimeException("El ejercicio no existe");
        }
        log.warn("ADVERTENCIA: Eliminando permanentemente el registro con ID: {}", id);
        repository.deleteById(id);
    }
}