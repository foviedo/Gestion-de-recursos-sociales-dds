package connector;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import javax.ws.rs.core.MediaType;

public class MercadoLibreConnector {
    private static final String API_MERCADO_LIBRE = "https://api.mercadolibre.com";

    public ClientResponse getData(String codigoPostal) {
        String path = String.format("countries/AR/zip_codes/%s", codigoPostal);
        return this.makeRequest(path);
    }

    public ClientResponse getMoneda(String currencyId) {
        String path = String.format("currencies/%s", currencyId);
        return this.makeRequest(path);
    }

    private ClientResponse makeRequest(String path) {
        return Client.create()
                .resource(API_MERCADO_LIBRE)
                .path(path)
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
    }
}
