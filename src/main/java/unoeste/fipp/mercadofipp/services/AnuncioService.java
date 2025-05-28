package unoeste.fipp.mercadofipp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
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
    public Anuncio save(Anuncio anuncio, MultipartFile[] fotos) {
        Anuncio novoAnuncio = anuncioRepository.save(anuncio);
        if (novoAnuncio != null)
            addFoto(fotos, novoAnuncio.getId());
        return novoAnuncio;
    }

    public boolean addFoto(MultipartFile[] fotos, long id_anuncio) {
        try {
            for (MultipartFile foto : fotos) {
                byte[] bytes = foto.getBytes();
                String nomeArq = foto.getOriginalFilename();
                String extensao;
                int pos = nomeArq.lastIndexOf(".");
                extensao = nomeArq.substring(pos + 1);
                anuncioRepository.addFoto(bytes, id_anuncio, extensao);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
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

    public boolean addResposta(String resposta, long id_pergunta)
    {
        try
        {
            anuncioRepository.addResposta(resposta, id_pergunta);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }


    public boolean deleteAnuncio(long id)
    {
        Anuncio anuncio = anuncioRepository.findById(id).orElse(null);
        if (anuncio != null)
        {
            anuncioRepository.deleteFoto(id);
            anuncioRepository.deletePergunta(id);
            anuncioRepository.delete(anuncio);
            return true;
        }
        else
            return false;
    }
}
