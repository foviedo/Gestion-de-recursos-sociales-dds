package service;

import connector.MercadoLibreConnector;
import domain.Moneda;
import dto.CountryByCurrency;
import dto.Data;
import mapper.JsonMapper;

public class MercadoLibreService {
    private MercadoLibreConnector connector;
    private JsonMapper mapper;

    public MercadoLibreService(MercadoLibreConnector connector, JsonMapper mapper) {
        this.connector = connector;
        this.mapper = mapper;
    }

    public Data getData(String paisId, String codigoPostal) {
        String json = connector.getData(paisId, codigoPostal).getEntity(String.class);
        return mapper.fromJson(json, Data.class);
    }

    private String getCurrencyId(String paisId) {
        String json = connector.getCurrencyId(paisId).getEntity(String.class);
        return mapper.fromJson(json, CountryByCurrency.class).getCurrencyId();
    }

    public Moneda getMoneda(String paisId) {
        String currencyId = this.getCurrencyId(paisId);
        String json = connector.getMoneda(currencyId).getEntity(String.class);
        return mapper.fromJson(json, Moneda.class);
    }
}
