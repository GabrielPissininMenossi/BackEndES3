package unoeste.fipp.mercadofipp.entities;

import unoeste.fipp.mercadofipp.entities.abstratas.Comercio;

public class CVenda extends Comercio {
    @Override
    protected void atualizarEstoque(Anuncio anuncio, int qtde)
    {
        anuncio.setEstoque(anuncio.getEstoque() - qtde);
    }
}
