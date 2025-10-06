package unoeste.fipp.mercadofipp.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import unoeste.fipp.mercadofipp.entities.*;
import unoeste.fipp.mercadofipp.services.AnuncioService;
import unoeste.fipp.mercadofipp.services.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("apis/anuncio")
@CrossOrigin
public class AnuncioRestController {
    @Autowired
    private AnuncioService anuncioService;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<Object> getAll() {
        List<Anuncio> anuncioList = anuncioService.getAll();
        if (anuncioList.size() > 0)
            return ResponseEntity.ok().body(anuncioList);
        else
            return ResponseEntity.badRequest().body(new Erro("Nenhum Anuncio Encontrado"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getId(@PathVariable long id) {
        Anuncio anuncio = anuncioService.getId(id);
        if (anuncio != null)
            return ResponseEntity.ok().body(anuncio);
        else
            return ResponseEntity.badRequest().body(new Erro("Anuncio Não Encontrado"));
    }

    @GetMapping(value = "/get-anuncio/{filtro}")
    public ResponseEntity<Object> getAnuncioFiltro(@PathVariable(value = "filtro") String filtro) {
        List<Anuncio> anuncioList;
        anuncioList = anuncioService.getAnuncioFiltro(filtro);
        if (anuncioList.size() > 0)
            return ResponseEntity.ok(anuncioList);
        else
            return ResponseEntity.badRequest().body(new Erro("Nenhum Anuncio Encontrado Com o Filtro Selecionado"));
    }

    @GetMapping("/get-por-usuario/{id}")
    public ResponseEntity<Object> getIdUsuario(@PathVariable long id) {
        List<Anuncio> anuncioList;
        anuncioList = anuncioService.getIdUsuario(id);
        if (anuncioList.size() > 0)
            return ResponseEntity.ok(anuncioList);
        else
            return ResponseEntity.badRequest().body(new Erro("Nenhum anúncio encontrado com o usuário informado"));
    }

    @PostMapping("add-foto/{id}")
    public ResponseEntity<Object> addFoto(@PathVariable(name = "id") Long idAnuncio, @RequestBody MultipartFile[] fotos) {
        if (anuncioService.addFoto(fotos, idAnuncio))
            return ResponseEntity.noContent().build();
        return ResponseEntity.badRequest().body(new Erro("Erro ao adicionar fotos!"));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> addAnuncio(@RequestPart("anuncio") Anuncio anuncio, @RequestPart("fotos") MultipartFile[] fotos) {
        Anuncio novo = anuncioService.save(anuncio, fotos);
        if (novo != null)
            return ResponseEntity.ok(anuncio);
        return ResponseEntity.badRequest().body(new Erro("Erro ao cadastrar anúncio!"));
    }

    @PostMapping("add-pergunta/{id}/{texto}")
    public ResponseEntity<Object> addPergunta(@PathVariable(name = "id") long idAnuncio, @PathVariable(name = "texto") String texto) {
        if (anuncioService.addPergunta(texto, idAnuncio)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().body(new Erro("Erro ao Gravar Pergunta"));
        }
    }

    @PostMapping("add-resposta/{id}/{resposta}")
    public ResponseEntity<Object> addResposta(@PathVariable(name = "id") long idPergunta, @PathVariable(name = "resposta") String resposta) {
        if (anuncioService.addResposta(resposta, idPergunta)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().body(new Erro("Erro ao Gravar Resposta"));
        }
    }

    //aplicando o template method
    @PostMapping("/compra/{id}/{qtde}")
    public ResponseEntity<Object> addEstoque(@PathVariable Long id, @PathVariable int qtde) {
        //qtde será somada com o estoque atual
        Anuncio anuncio = anuncioService.getId(id);
        if (anuncio != null && qtde > 0) {
            CCompra compra = new CCompra();
            compra.gravar(anuncio, qtde);
            if (anuncioService.save(anuncio, null) != null) {
                return ResponseEntity.ok(anuncio);
            }
        }
        return ResponseEntity.badRequest().body(new Erro("Erro ao Cadastrar Nova Venda"));
    }
    @PostMapping("/venda/{id}/{qtde}")
    public ResponseEntity<Object> tiraEstoque(@PathVariable Long id, @PathVariable int qtde) {
        //qtde será subtraída com o estoque atual
        Anuncio anuncio = anuncioService.getId(id);
        if (anuncio != null && qtde > 0) {
            CVenda venda = new CVenda();
            venda.gravar(anuncio, qtde);
            if (anuncioService.save(anuncio, null) != null) {
                return ResponseEntity.ok(anuncio);
            }
        }
        return ResponseEntity.badRequest().body(new Erro("Erro ao Cadastrar Nova Venda"));
    }

    //adicionar um observer -> Observer Method
    @PostMapping("/addobserver/{anu_id}/{usr_id}")
    public ResponseEntity<Object> addObserver(@PathVariable long anu_id, @PathVariable long usr_id) {
        Anuncio anuncio = anuncioService.getId(anu_id); //pegar o respectivo anuncio
        if (anuncio != null) {
            Usuario usuario = usuarioService.getId(usr_id); //pegar o respectivo usuário
            if (usuario != null) {
                //salvar no banco
                if (!anuncio.getObservers().contains(usuario)) { //se ainda não existe
                    anuncio.addObserver(usuario);
                    anuncioService.save(anuncio, null);
                    return ResponseEntity.ok(anuncio);
                } else //já existe esse usuário observando esse anuncio
                {
                    return ResponseEntity.badRequest().body(new Erro("Esse usuario ja observa esse Anuncio!!"));
                }
            }
        }
        return ResponseEntity.badRequest().body(new Erro("Erro ao Gravar Observer!!"));
    }

    //adicionar um observer -> Observer Method
    @DeleteMapping("/deleteobserver/{anu_id}/{usr_id}")
    public ResponseEntity<Object> deleteObserver(@PathVariable long anu_id, @PathVariable long usr_id) {
        Anuncio anuncio = anuncioService.getId(anu_id); //pegar o respectivo anuncio
        if (anuncio != null) {
            Usuario usuario = usuarioService.getId(usr_id); //pegar o respectivo usuário
            if (usuario != null) {
                //remover do banco
                if (anuncio.getObservers().contains(usuario)) { //se existe
                    anuncio.removeObserver(usuario);
                    anuncioService.save(anuncio, null);
                    return ResponseEntity.ok(anuncio);
                }
                else
                {
                    return ResponseEntity.badRequest().body(new Erro("Esse usuario nao observa esse Anuncio!!"));
                }
            }
        }
        return ResponseEntity.badRequest().body(new Erro("Erro ao Gravar Observer!!"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAnuncio(@PathVariable long id) {
        if (anuncioService.deleteAnuncio(id))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Apagar Anuncio"));
    }
}
