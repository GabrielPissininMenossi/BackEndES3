package unoeste.fipp.mercadofipp.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unoeste.fipp.mercadofipp.entities.Categoria;
import unoeste.fipp.mercadofipp.entities.Erro;
import unoeste.fipp.mercadofipp.entities.Usuario;
import unoeste.fipp.mercadofipp.services.CategoriaService;
import unoeste.fipp.mercadofipp.services.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("apis/usuario")
@CrossOrigin
public class UsuarioRestController {

        @Autowired
        private UsuarioService usuarioService;

        @GetMapping
        public ResponseEntity<Object> getAll()
        {
            List<Usuario> usuarioList = usuarioService.getAll();
            if (usuarioList.size() > 0)
                return ResponseEntity.ok(usuarioList);
            else
                return ResponseEntity.badRequest().body(new Erro("Nenhum Usuario Encontrado"));

        }
        @GetMapping(value = "/{id}")
        public ResponseEntity<Object> getId(@PathVariable(value = "id") long id)
        {
            Usuario usuario = usuarioService.getId(id);
            if (usuario != null)
                return ResponseEntity.ok(usuario);
            else
                return ResponseEntity.badRequest().body(new Erro("Usuario Não Encontrado"));

        }
        @PostMapping
        public ResponseEntity<Object> addUsuario(@RequestBody Usuario usuario)
        {
            Usuario novoUsuario = usuarioService.save(usuario);
            if(novoUsuario != null)
                return ResponseEntity.ok(novoUsuario);
            else
                return ResponseEntity.badRequest().body(new Erro("Erro ao Cadastrar Novo Usuario"));
        }
        @PutMapping
        public ResponseEntity<Object> updateUsuario(@RequestBody Usuario usuario)
        {
            Usuario novoUsuario;
            novoUsuario = usuarioService.save(usuario);
            if (novoUsuario != null)
                return ResponseEntity.ok(novoUsuario);
            else
                return ResponseEntity.badRequest().body(new Erro("Erro ao Alterar Usuario"));

        }
        @DeleteMapping("/{id}")
        public ResponseEntity<Object> deleteUsuario(@PathVariable("id") Long id)
        {

            if(usuarioService.delete(id))
            {
                return ResponseEntity.noContent().build();
            }
            else
                return ResponseEntity.badRequest().body(new Erro("Erro ao Apagar Usuario"));
        }
}
