package unoeste.fipp.mercadofipp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.mercadofipp.entities.Usuario;
import unoeste.fipp.mercadofipp.repositories.UsuarioRepository;
import unoeste.fipp.mercadofipp.security.JWTTokenProvider;

import java.util.List;
@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    public List<Usuario> getAll()
    {
        List<Usuario> usuarioList = usuarioRepository.findAll();
        return usuarioList;
    }
    public Usuario getId(long id)
    {
        Usuario usuario;
        usuario = usuarioRepository.findById(id).orElse(null);
        return usuario;
    }

    public Usuario save(Usuario usuario)
    {
        try {
            Usuario aux = usuarioRepository.getUsuarioByNome(usuario.getNome());
            if(aux == null)
            {
                Usuario novoUsuario = usuarioRepository.save(usuario);
                return usuarioRepository.save(novoUsuario);
            }
            else
            {
                return null;
            }

        }catch (Exception e){
            return null;
        }
    }

    public boolean delete(Long id)
    {
        Usuario usuario;
        usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null){
             usuarioRepository.delete(usuario);
             return true;
        }
        else
            return false;
    }

    public String logar(String nome, String senha)
    {
        Usuario usuario;
        usuario = usuarioRepository.getUsuarioByNome(nome);
        String token = null;
        if (usuario != null)
        {
            if(usuario.getSenha().equals(senha))
                token = JWTTokenProvider.getToken(nome, usuario.getNivel(), usuario.getId());
        }
        return token;
    }
}
