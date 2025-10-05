package unoeste.fipp.mercadofipp.entities.abstratas;

import unoeste.fipp.mercadofipp.entities.Anuncio;
import unoeste.fipp.mercadofipp.repositories.AnuncioRepository;

public abstract class Comercio {

    private AnuncioRepository anuncioRepository;

    public final void gravar(Anuncio anuncio, int qtde)
    {
        atualizarEstoque(anuncio, qtde);
        registrarEstoque(anuncio);
    }
    public abstract void atualizarEstoque(Anuncio anuncio, int qtde);
}
