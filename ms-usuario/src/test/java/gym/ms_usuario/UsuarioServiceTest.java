package gym.ms_usuario;

import gym.ms_usuario.Model.Usuario;
import gym.ms_usuario.Repository.UsuarioRepository;
import gym.ms_usuario.Service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    public void testObtenerTodos() {
        Usuario usuarioMock = new Usuario();
        usuarioMock.setNombre("Juan Pérez");

        when(usuarioRepository.findAll()).thenReturn(List.of(usuarioMock));

        List<Usuario> usuarios = usuarioService.obtenerTodos();

        assertNotNull(usuarios);
        assertEquals(1, usuarios.size());
    }

    @Test
    public void testFindById() {
        int id = 1;
        Usuario usuarioMock = new Usuario();
        usuarioMock.setNombre("Ana Gómez");

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuarioMock));

        Usuario found = usuarioService.findById(id);

        assertNotNull(found);
        assertEquals("Ana Gómez", found.getNombre());
    }

    @Test
    public void testGuardar() {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre("Carlos López");

        when(usuarioRepository.save(any(Usuario.class))).thenReturn(nuevoUsuario);

        Usuario saved = usuarioService.guardar(nuevoUsuario);

        assertNotNull(saved);
        assertEquals("Carlos López", saved.getNombre());
    }

    @Test
    public void testEliminar() {
        int id = 1;
        when(usuarioRepository.existsById(id)).thenReturn(true);
        usuarioService.eliminar(id);
        verify(usuarioRepository, times(1)).deleteById(id);
    }
}