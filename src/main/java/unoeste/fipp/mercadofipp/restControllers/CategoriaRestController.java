package unoeste.fipp.mercadofipp.restControllers;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unoeste.fipp.mercadofipp.entities.Categoria;
import unoeste.fipp.mercadofipp.entities.Erro;
import unoeste.fipp.mercadofipp.services.CategoriaService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("apis/categoria")
@CrossOrigin
public class CategoriaRestController
{
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<Object> getAll()
    {
        List<Categoria> categoriaList = categoriaService.getAll();
        if (categoriaList.size() > 0)
            return ResponseEntity.ok(categoriaList);
        else
            return ResponseEntity.badRequest().body(new Erro("Nenhuma Categoria Encontrada"));

    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getId(@PathVariable (value = "id") long id)
    {
        Categoria categoria = categoriaService.getId(id);
        if (categoria != null)
            return ResponseEntity.ok(categoria);
        else
            return ResponseEntity.badRequest().body(new Erro("Categoria Não Encontrada"));

    }
    @PostMapping
    public ResponseEntity<Object> addCategoria(@RequestBody Categoria categoria)
    {
        Categoria novaCategoria = categoriaService.save(categoria);
        if(novaCategoria != null)
            return ResponseEntity.ok(novaCategoria);
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Cadastrar Nova Categoria"));
    }
    @PutMapping
    public ResponseEntity<Object> updateCategoria(@RequestBody Categoria categoria)
    {
        Categoria novaCategoria;
        novaCategoria = categoriaService.save(categoria);
        if (novaCategoria != null)
            return ResponseEntity.ok(novaCategoria);
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Alterar Categoria"));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategoria(@PathVariable("id") Long id)
    {

        if(categoriaService.delete(id))
        {
            return ResponseEntity.noContent().build();
        }
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Apagar Categoria"));
    }
}
