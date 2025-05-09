package unoeste.fipp.mercadofipp.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import unoeste.fipp.mercadofipp.entities.Anuncio;
import unoeste.fipp.mercadofipp.entities.Erro;
import unoeste.fipp.mercadofipp.entities.Foto;
import unoeste.fipp.mercadofipp.entities.Pergunta;
import unoeste.fipp.mercadofipp.services.AnuncioService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("apis/anuncio")
public class AnuncioRestController
{
    @Autowired
    private AnuncioService anuncioService;
    private final Path root = Paths.get("src/main/resources/uploads");
    @GetMapping
    public ResponseEntity<Object> getAll()
    {
        List<Anuncio> anuncioList = anuncioService.getAll();
        if (anuncioList.size() > 0)
            return ResponseEntity.ok().body(anuncioList);
        else
            return ResponseEntity.badRequest().body(new Erro("Nenhum Anuncio Encontrado"));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object>getId(@PathVariable long id)
    {
        Anuncio anuncio = anuncioService.getId(id);
        if (anuncio != null)
            return ResponseEntity.ok().body(anuncio);
        else
            return ResponseEntity.badRequest().body(new Erro("Anuncio Não Encontrado"));
    }
    @GetMapping(value = "/get-anuncio/{filtro}")
    public ResponseEntity<Object> getAnuncioFiltro(@PathVariable(value = "filtro") String filtro)
    {
        List<Anuncio> anuncioList;
        anuncioList = anuncioService.getAnuncioFiltro(filtro);
        if (anuncioList.size() > 0)
            return ResponseEntity.ok(anuncioList);
        else
            return ResponseEntity.badRequest().body(new Erro("Nenhum Anuncio Encontrado Com o Filtro Selecionado"));
    }
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Anuncio anuncio)
    {
        Anuncio novoAnuncio;
        novoAnuncio = anuncioService.add(anuncio);
        if (novoAnuncio != null)
            return ResponseEntity.ok().body(novoAnuncio);
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Gravar Novo Anuncio"));
    }
    @GetMapping("add-pergunta/{id}/{texto}")
    public ResponseEntity<Object> addPergunta(@PathVariable(name = "id") long idAnuncio,@PathVariable(name = "texto") String texto)
    {
        if(anuncioService.addPergunta(texto, idAnuncio))
        {
            return ResponseEntity.noContent().build();
        }
        else
        {
            return ResponseEntity.badRequest().body(new Erro("Erro ao Gravar Pergunta"));
        }
    }
    @GetMapping("add-resposta/{id}/{resposta}")
    public ResponseEntity<Object> addResposta(@PathVariable (name = "id") long idPergunta, @PathVariable (name = "resposta") String resposta)
    {
        if(anuncioService.addResposta(resposta, idPergunta))
        {
            return ResponseEntity.noContent().build();
        }
        else
        {
            return ResponseEntity.badRequest().body(new Erro("Erro ao Gravar Resposta"));
        }
    }
    @GetMapping("/add-foto")
    public ResponseEntity<Object> addFoto(@RequestParam long id, @RequestParam MultipartFile file) {
        try {

            String fileName = file.getOriginalFilename();
            Files.copy(file.getInputStream(), root.resolve(fileName));
            if(anuncioService.addFoto(fileName, id));
            {
                return ResponseEntity.noContent().build();
            }



        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new Erro("Erro ao Gravar Foto"));
        }
    }
    @GetMapping("/get-por-usuario/{id}")
    public ResponseEntity<Object> getIdUsuario (@PathVariable long id)
    {
        List <Anuncio> anuncioList;
        anuncioList = anuncioService.getIdUsuario(id);
        if (anuncioList.size() > 0)
            return ResponseEntity.ok(anuncioList);
        else
            return ResponseEntity.badRequest().body(new Erro("Nenhum anúncio encontrado com o usuário informado"));
    }
    @PutMapping
    public ResponseEntity<Object> update(@RequestBody Anuncio anuncio)
    {
        Anuncio novoAnuncio;
        novoAnuncio = anuncioService.add(anuncio);
        if (novoAnuncio != null)
            return ResponseEntity.ok().body(novoAnuncio);
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Alterar Anuncio"));
    }


}
