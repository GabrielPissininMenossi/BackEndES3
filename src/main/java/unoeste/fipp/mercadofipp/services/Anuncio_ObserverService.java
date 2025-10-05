package unoeste.fipp.mercadofipp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.mercadofipp.entities.Anuncio;
import unoeste.fipp.mercadofipp.entities.Anuncio_Observer;
import unoeste.fipp.mercadofipp.entities.Usuario;
import unoeste.fipp.mercadofipp.repositories.Anuncio_ObserverRepository;

import java.util.List;

@Service
public class Anuncio_ObserverService {
    @Autowired
    private Anuncio_ObserverRepository anuncio_observerRepository;

    public List<Anuncio_Observer> getAll()
    {
        return anuncio_observerRepository.findAll();
    }
    public Anuncio_Observer getID(long id)
    {
        return anuncio_observerRepository.findById(id).orElse(null);
    }
    public Anuncio_Observer save(long anu_id, long usr_id)
    {
        try{
            //verificar se existe o respectivo anuncio e o usuario
            AnuncioService anuncioService = new AnuncioService();
            UsuarioService usuarioService = new UsuarioService();
            Anuncio anuncio = anuncioService.getId(anu_id);
            Usuario usuario = usuarioService.getId(usr_id);
            if(anuncio != null && usuario != null) //existe, posso adicionar o observer
            {
                return anuncio_observerRepository.save(new Anuncio_Observer(0L, anuncio, usuario));
            }
        }
        catch (Exception e){
            return null;
        }
        return null;
    }

    public boolean delete(long id)
    {
        Anuncio_Observer anuncioObserver = anuncio_observerRepository.findById(id).orElse(null);
        if (anuncioObserver != null)
        {
            anuncio_observerRepository.delete(anuncioObserver);
            return true;
        }
        else
            return false;
    }
}
