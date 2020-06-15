package factory;

import domain.Item;
import domain.Moneda;
import service.MercadoLibreService;

public class ItemFactory {
    private MercadoLibreService service;

    public ItemFactory(MercadoLibreService service) {
        this.service = service;
    }

    public Item createItem(String descripcion, String paisId, double monto) {
        Moneda moneda = service.getMoneda(paisId);
        return new Item(descripcion, moneda, monto);
    }
}
