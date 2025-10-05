package unoeste.fipp.mercadofipp.entities.abstratas;

import unoeste.fipp.mercadofipp.entities.Anuncio;

public abstract class Comercio {

    public final void gravar(Anuncio anuncio, int qtde)
    {
        atualizarEstoque(anuncio, qtde);
    }
    public abstract void atualizarEstoque(Anuncio anuncio, int qtde);
}
