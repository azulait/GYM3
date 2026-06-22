package gym.ms_entrenador.service;

import gym.ms_entrenador.model.Entrenador;
import gym.ms_entrenador.repository.EntrenadorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EntrenadorService {

    @Autowired
    private EntrenadorRepository repository;

    public Entrenador findById(int id){
        return repository.findById(id).orElseThrow(() -> {
            log.error("ERROR: No se encontró el entrenador con ID: {}", id);
            return new RuntimeException("Entrenador no encontrado");
        });
    }

    public List<Entrenador> findAll() {
        log.debug("DEBUG: Obteniendo la lista completa de entrenadores.");
        return repository.findAll();
    }

    public Entrenador guardar(Entrenador entrenador) {
        log.info("INFORMACION: Guardando datos del entrenador: {}", entrenador.getNombre());
        return repository.save(entrenador);
    }

    public void eliminar(int id) {
        if(!repository.existsById(id)){
            log.error("ERROR: Intento fallido de eliminar un entrenador inexistente con ID: {}", id);
            throw new RuntimeException("El entrenador no existe");
        }
        log.warn("ADVERTENCIA: Eliminando permanentemente al entrenador con ID: {}", id);
        repository.deleteById(id);
    }
}