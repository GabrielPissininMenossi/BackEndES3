package unoeste.fipp.mercadofipp.services;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import unoeste.fipp.mercadofipp.entities.Categoria;
import unoeste.fipp.mercadofipp.entities.Erro;
import unoeste.fipp.mercadofipp.repositories.CategoriaRepository;

import java.util.List;
@Service
public class CategoriaService
{
    @Autowired
    private CategoriaRepository categoriaRepository;
    public List<Categoria> getAll()
    {
        return categoriaRepository.findAll();
    }

    public Categoria getId(long id)
    {
        return categoriaRepository.findById(id).orElse(null);
    }
    public Categoria save(Categoria categoria)
    {
        try{
            Categoria novaCategoria = categoriaRepository.save(categoria);
            return categoriaRepository.save(novaCategoria);

        }
        catch (Exception e){
            return null;
        }

    }

    public boolean delete(Long id)
    {
        Categoria categoria = categoriaRepository.findById(id).orElse(null);
        if (categoria != null)
        {
            categoriaRepository.delete(categoria);
            return true;
        }
        else
            return false;

    }
    //getId
    //inserir
    //alterar
    //excluir
}

