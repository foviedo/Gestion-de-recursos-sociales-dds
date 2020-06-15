package factory;

import domain.DireccionPostal;
import dto.Data;
import service.MercadoLibreService;

public class DireccionPostalFactory {
    private MercadoLibreService service;

    public DireccionPostalFactory(MercadoLibreService service) {
        this.service = service;
    }

    public DireccionPostal createDireccionPostal(String calle,
                                                 String altura,
                                                 String piso,
                                                 String departamento,
                                                 String codigoPostal,
                                                 String paisId) {

        Data data = service.getData(paisId, codigoPostal);
        return new DireccionPostal(calle, altura, piso, departamento, codigoPostal, data.getCountry().getName(), data.getState().getName(), data.getCity().getName());
    }
}
