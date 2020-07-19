package factory;

import domain.Item;
import domain.Moneda;
import service.MercadoLibreService;

public class ItemFactory {
    private final MercadoLibreService mercadoLibreService;

    public ItemFactory(MercadoLibreService mercadoLibreService) {
        this.mercadoLibreService = mercadoLibreService;
    }

    public Item createItem(String descripcion, double monto) {
        Moneda moneda = mercadoLibreService.getMoneda();
        return new Item(descripcion, moneda, monto);
    }
}
