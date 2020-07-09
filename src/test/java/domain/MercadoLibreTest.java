package domain;

import com.sun.jersey.api.client.ClientResponse;
import connector.MercadoLibreConnector;
import factory.DireccionPostalFactory;
import factory.ItemFactory;
import mapper.JsonMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import service.MercadoLibreService;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MercadoLibreTest {
    private MercadoLibreConnector connector;
    private ClientResponse response;
    private MercadoLibreService service;
    private JsonMapper mapper;

    @Before
    public void setUp() {
        mapper = new JsonMapper();
        connector = mock(MercadoLibreConnector.class);
        response = mock(ClientResponse.class);
        service = new MercadoLibreService(connector, mapper);
    }

    @Test
    public void crearDireccionPostalUsandoMercadoLibreAPI() {
        InputStream inputStream = mapper.loadJSonFromFile("mocks/data.json");
        String jsonData = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8)).lines()
                .collect(Collectors.joining("\n"));
        when(connector.getData(anyString())).thenReturn(response);
        when(response.getEntity((Class<Object>) anyObject())).thenReturn(jsonData);

        DireccionPostalFactory direccionPostalFactory = new DireccionPostalFactory(service);
        DireccionPostal direccionPostal = direccionPostalFactory.createDireccionPostal("falsa", "123", "A", "2do", "5000");

        assertEquals("falsa", direccionPostal.getCalle());
        assertEquals("123", direccionPostal.getAltura());
        assertEquals("A", direccionPostal.getPiso());
        assertEquals("2do", direccionPostal.getDepartamento());
        assertEquals("5000", direccionPostal.getCodigoPostal());
        assertEquals("Argentina", direccionPostal.getPais());
        assertEquals("Cordoba", direccionPostal.getProvincia());
        assertNull(direccionPostal.getCiudad());
    }

    @Test
    public void completarDatosDelItemUsandoMercadoLibreAPI() {
        InputStream inputStreamMoneda = mapper.loadJSonFromFile("mocks/moneda.json");
        String jsonMoneda = new BufferedReader(new InputStreamReader(inputStreamMoneda, StandardCharsets.UTF_8)).lines()
                .collect(Collectors.joining("\n"));
        when(connector.getMoneda(anyString())).thenReturn(response);
        when(response.getEntity((Class<Object>) anyObject())).thenReturn(jsonMoneda);

        ItemFactory factory = new ItemFactory(service);
        Item item = factory.createItem("Otros", 200.0);

        assertEquals("Otros", item.getDescripcion());
        assertEquals(200.0, item.getMonto(), 0.1);
        Moneda moneda = item.getMoneda();
        assertEquals("ARS", moneda.getId());
        assertEquals("Peso argentino", moneda.getDescription());
        assertEquals("$", moneda.getSymbol());
    }
}
