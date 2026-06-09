package gym.ms_entrenador.service;

import gym.ms_entrenador.model.Entrenador;
import gym.ms_entrenador.repository.EntrenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrenadorService {

    @Autowired
    private EntrenadorRepository repository;

    public Entrenador findById(int id){
        return repository.findById(id).orElse(null);
    }

    public List<Entrenador> findAll() {
        return repository.findAll();
    }

    public Entrenador guardar(Entrenador entrenador) {
        return repository.save(entrenador);
    }

    public void eliminar(int id) {
        repository.deleteById(id);
    }
}