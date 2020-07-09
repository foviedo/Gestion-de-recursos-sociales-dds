package service;

import connector.MercadoLibreConnector;
import domain.Moneda;
import dto.Data;
import mapper.JsonMapper;

public class MercadoLibreService {
    private final MercadoLibreConnector mercadoLibreConnector;
    private final JsonMapper jsonMapper;

    public MercadoLibreService(MercadoLibreConnector mercadoLibreConnector, JsonMapper jsonMapper) {
        this.mercadoLibreConnector = mercadoLibreConnector;
        this.jsonMapper = jsonMapper;
    }

    public Data getData(String codigoPostal) {
        String json = mercadoLibreConnector.getData(codigoPostal).getEntity(String.class);
        return jsonMapper.fromJson(json, Data.class);
    }

    public Moneda getMoneda() {
        String json = mercadoLibreConnector.getMoneda("ARS").getEntity(String.class);
        return jsonMapper.fromJson(json, Moneda.class);
    }
}
