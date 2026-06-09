package gym.ms_ejercicios.Service;

import gym.ms_ejercicios.Model.Ejercicio;
import gym.ms_ejercicios.Repository.EjercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EjercicioService {
    @Autowired
    private EjercicioRepository repository;


    public Ejercicio findById(int id){
        return repository.findById(id).orElse(null);
    }

    public List<Ejercicio> findAll() {
        return repository.findAll();
    }

    public Ejercicio guardar(Ejercicio entrenador) {
        return repository.save(entrenador);
    }

    public void eliminar(int id) {
        repository.deleteById(id);
    }
}