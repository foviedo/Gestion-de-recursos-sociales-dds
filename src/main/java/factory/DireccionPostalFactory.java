package factory;

import domain.DireccionPostal;
import dto.Data;
import service.MercadoLibreService;

public class DireccionPostalFactory {
    private final MercadoLibreService mercadoLibreService;

    public DireccionPostalFactory(MercadoLibreService mercadoLibreService) {
        this.mercadoLibreService = mercadoLibreService;
    }

    public DireccionPostal createDireccionPostal(String calle,
                                                 String altura,
                                                 String piso,
                                                 String departamento,
                                                 String codigoPostal) {

        Data data = mercadoLibreService.getData(codigoPostal);
        return new DireccionPostal(calle, altura, piso, departamento, codigoPostal, data.getCountry().getName(), data.getState().getName(), data.getCity().getName());
    }
}
