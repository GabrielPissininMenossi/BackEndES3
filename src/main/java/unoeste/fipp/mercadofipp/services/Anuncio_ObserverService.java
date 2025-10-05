package unoeste.fipp.mercadofipp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.mercadofipp.entities.Anuncio_Observer;
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
    public Anuncio_Observer save(Anuncio_Observer anuncioObserver)
    {
        try{
            Anuncio_Observer anuncioObserver1 = anuncio_observerRepository.save(anuncioObserver);
            return anuncio_observerRepository.save(anuncioObserver1);

        }
        catch (Exception e){
            return null;
        }
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
