package unoeste.fipp.mercadofipp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.mercadofipp.entities.Venda;
import unoeste.fipp.mercadofipp.entities.abstratas.Comercio;
import unoeste.fipp.mercadofipp.repositories.AnuncioRepository;
import unoeste.fipp.mercadofipp.repositories.VendaRepository;

import java.util.List;

@Service
public class VendaService
{

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private AnuncioRepository anuncioRepository;

    public VendaService(VendaRepository vendaRepository) {
        this.vendaRepository = vendaRepository;
    }

    public List<Venda> getAll()
    {
        return vendaRepository.findAll();
    }

    public Venda getId(long id) {
        return vendaRepository.findById(id).orElse(null);
    }
    
    public Venda save(Venda venda)
    {
        try{
            Venda novaVenda = vendaRepository.save(venda);
            return vendaRepository.save(novaVenda);
        }catch (Exception e){
            return null;
        }
    }

    public boolean delete(Long id) {
        Venda venda = vendaRepository.findById(id).orElse(null);
        if(venda != null)
        {
            vendaRepository.delete(venda);
            return true;
        }
        return false;
    }

}
