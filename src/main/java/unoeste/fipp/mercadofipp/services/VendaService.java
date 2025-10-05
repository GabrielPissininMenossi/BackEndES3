package unoeste.fipp.mercadofipp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.mercadofipp.entities.CCompra;
import unoeste.fipp.mercadofipp.entities.CVenda;
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
            Venda teste = vendaRepository.getById(venda.getId());
            if(teste == null) //se não existe eu salvo um novo
                return vendaRepository.save(venda);
            return null;
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

    public Venda saveCompra(Venda compra)
    {
        Comercio comercio = new CCompra();
        comercio.templateMethod();
    }

    public Venda saveVenda(Venda venda) // preciso diminuir o estoque do anuncio
    {
        Comercio comercio = new CVenda();
        comercio.gravar();
    }
}
