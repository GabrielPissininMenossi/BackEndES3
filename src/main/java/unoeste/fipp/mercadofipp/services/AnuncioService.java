package unoeste.fipp.mercadofipp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.mercadofipp.entities.Anuncio;
import unoeste.fipp.mercadofipp.entities.Foto;
import unoeste.fipp.mercadofipp.entities.Pergunta;
import unoeste.fipp.mercadofipp.repositories.AnuncioRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnuncioService {
    @Autowired
    private AnuncioRepository anuncioRepository;
    public List<Anuncio> getAll()
    {
        return anuncioRepository.findAll();
    }
    public Anuncio add(Anuncio anuncio)
    {

        Anuncio novoAnuncio;
        novoAnuncio = anuncioRepository.save(anuncio);
        //agora grave as fotos na tabela de fotos
        //for...
        //anuncioRepository.addFoto(...)
        return novoAnuncio;

    }
    public Anuncio getId(long id)
    {
        return anuncioRepository.findById(id).orElse(null);
    }

    public List<Anuncio> getIdUsuario(long id)
    {
        List<Anuncio> anuncioList;
        anuncioList = anuncioRepository.getIdUsuario(id);
        return anuncioList;
    }
    public List<Anuncio> getAnuncioFiltro(String filtro)
    {
        List<Anuncio> anuncioList;
        filtro = filtro + "%";
        anuncioList = anuncioRepository.getAnuncioFiltro(filtro);
        return anuncioList;
    }
    public boolean addPergunta(String texto, long id_anuncio)
    {
        try
        {
            anuncioRepository.addPergunta(texto, id_anuncio);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }

    }
    public boolean addFoto(String nomeArquivo, long id_anuncio)
    {
        try
        {
            anuncioRepository.addFoto(nomeArquivo, id_anuncio);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }


}
