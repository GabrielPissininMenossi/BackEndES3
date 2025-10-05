package unoeste.fipp.mercadofipp.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unoeste.fipp.mercadofipp.entities.Anuncio_Observer;
import unoeste.fipp.mercadofipp.entities.Erro;
import unoeste.fipp.mercadofipp.services.AnuncioService;
import unoeste.fipp.mercadofipp.services.Anuncio_ObserverService;


import java.util.List;

@RestController
@RequestMapping("apis/observer")
@CrossOrigin
public class Anuncio_ObserverController
{
    @Autowired
    private Anuncio_ObserverService anuncio_observerService;

    @GetMapping
    public ResponseEntity<Object> getAll()
    {
        List<Anuncio_Observer> anuncio_observerList = anuncio_observerService.getAll();
        if (anuncio_observerList.size() > 0)
            return ResponseEntity.ok().body(anuncio_observerList);
        else
            return ResponseEntity.badRequest().body(new Erro("Nenhum Observer Encontrado no Anuncio "));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getID(@PathVariable long id)
    {
        Anuncio_Observer anuncio_observer = anuncio_observerService.getID(id);
        if (anuncio_observer != null)
            return ResponseEntity.ok().body(anuncio_observer);
        return ResponseEntity.badRequest().body(new Erro("Nenhum Observer Encontrado no Anuncio "));
    }

    //adicionar um observer
    @PostMapping
    public ResponseEntity<Object> addObserver(@RequestBody Anuncio_Observer anuncioObserver)
    {
        Anuncio_Observer novoAnuncioObserver = anuncio_observerService.save(anuncioObserver);
        if(novoAnuncioObserver != null)
        {
            return ResponseEntity.noContent().build();
        }
        else
        {
            return ResponseEntity.badRequest().body(new Erro("Erro ao Gravar Observer!!"));
        }
    }

    //já trata se existe as duas chaves no service
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteObserver(@PathVariable(name = "id") long id)
    {
        if(anuncio_observerService.delete(id))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Apagar Observer"));
    }

}
