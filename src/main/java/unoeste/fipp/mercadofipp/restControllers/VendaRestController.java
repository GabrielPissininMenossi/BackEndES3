package unoeste.fipp.mercadofipp.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import unoeste.fipp.mercadofipp.entities.Erro;
import unoeste.fipp.mercadofipp.entities.Item;

import unoeste.fipp.mercadofipp.entities.Venda;
import unoeste.fipp.mercadofipp.services.VendaService;

import java.util.List;

@RestController
@RequestMapping("apis/item")
@CrossOrigin
public class VendaRestController
{
    @Autowired
    private VendaService vendaService;

    @GetMapping
    public ResponseEntity<Object> getAll()
    {
        List<Venda> itemList = vendaService.getAll();
        if (itemList.size() > 0)
            return ResponseEntity.ok().body(itemList);
        else
            return ResponseEntity.badRequest().body(new Erro("Nenhum Item Encontrado"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getId(@PathVariable long id)
    {
        Venda venda = vendaService.getId(id);
        if (venda != null)
            return ResponseEntity.ok().body(venda);
        else
            return ResponseEntity.badRequest().body(new Erro("Venda Não Encontrada"));
    }

    @PostMapping("/venda")
    public ResponseEntity<Object> addVenda(@RequestBody Venda venda)
    {
        Venda novaVenda = vendaService.saveVenda(venda);
        if(novaVenda != null)
        {
            return ResponseEntity.ok(venda);
        }
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Cadastrar Nova Venda"));
    }
    @PostMapping("/compra")
    public ResponseEntity<Object> addCompra(@RequestBody Venda compra)
    {
        Venda novaCompra = vendaService.saveCompra(compra);
        if(novaCompra != null)
        {
            return ResponseEntity.ok(compra);
        }
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Cadastrar Nova Venda"));
    }
    @PutMapping
    public ResponseEntity<Object> updateVenda(@RequestBody Venda venda)
    {
        Venda novaVenda = vendaService.save(venda);
        if (novaVenda != null)
            return ResponseEntity.ok(novaVenda);
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Alterar Venda"));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteItem(@PathVariable("id") Long id)
    {
        if(vendaService.delete(id))
        {
            return ResponseEntity.noContent().build();
        }
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Apagar Venda"));
    }
}
