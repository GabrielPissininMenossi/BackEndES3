package unoeste.fipp.mercadofipp.entities;

import unoeste.fipp.mercadofipp.entities.abstratas.Comercio;

public class CCompra extends Comercio {
    @Override
    public void atualizarEstoque(Anuncio anuncio, int qtde)
    {
        anuncio.setEstoque(anuncio.getEstoque() + qtde);
    }
}
