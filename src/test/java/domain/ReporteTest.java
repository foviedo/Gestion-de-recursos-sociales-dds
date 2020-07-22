package domain;

import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class ReporteTest {
    private Egreso egresoConEtiquetaAmoblamiento;
    private Egreso egresoConIndumentaria;
    private Egreso otroEgresoConIndumentaria;
    private Entidad entidad;
    private static final String AMOBLAMIENTO = "AMOBLAMIENTO";
    private static final String INDUMENTARIA = "INDUMENTARIA";

    @Before
    public void setUp() {
        Documento documento = mock(Documento.class);
        MedioDePago medioDePago = mock(MedioDePago.class);
        Proveedor proveedor = mock(Proveedor.class);
        Validacion validacion = mock(Validacion.class);
        List usuarios = mock(List.class);
        LocalDate fechaDeOperacion = LocalDate.of(2020,1,1);
        Item itemA = new Item("itemA", mock(Moneda.class), 100.0);
        Item itemB = new Item("itemB", mock(Moneda.class), 200.0);
        Item itemC = new Item("itemC", mock(Moneda.class), 300.0);
        List<Item> itemsA = Collections.singletonList(itemA);
        List<Item> itemsB = Collections.singletonList(itemB);
        List<Item> itemsC = Collections.singletonList(itemC);
        List<String> etiquetasAmoblamiento = Collections.singletonList(AMOBLAMIENTO);
        List<String> etiquetasIndumentaria = Collections.singletonList(INDUMENTARIA);
        entidad = new Base("un nombre ficticio", "una descripción");

        egresoConEtiquetaAmoblamiento = new Egreso(documento, medioDePago, proveedor, fechaDeOperacion, itemsA, usuarios, validacion, etiquetasAmoblamiento);
        egresoConIndumentaria = new Egreso(documento, medioDePago, proveedor, fechaDeOperacion, itemsB, usuarios, validacion, etiquetasIndumentaria);
        otroEgresoConIndumentaria = new Egreso(documento, medioDePago, proveedor, fechaDeOperacion, itemsC, usuarios, validacion, etiquetasIndumentaria);
    }

    @Test
    public void cuandoNoTengoEgresosQueMatcheenDeberiaDevolverUnReporteSinEgresos() {
        entidad.agregarEgreso(egresoConEtiquetaAmoblamiento);
        Reporte reporte = entidad.generarReporte(INDUMENTARIA);
        assertEquals(INDUMENTARIA, reporte.getEtiqueta());
        assertTrue(reporte.getEgresos().isEmpty());
    }

    @Test
    public void cuandoTengoEgresosQueMatcheenDeberiaDevolverUnReporteConEsosEgresos() {
        entidad.agregarEgreso(egresoConIndumentaria);
        entidad.agregarEgreso(otroEgresoConIndumentaria);
        Reporte reporte = entidad.generarReporte(INDUMENTARIA);
        assertEquals(INDUMENTARIA, reporte.getEtiqueta());
        assertEquals(2, reporte.getEgresos().size());
        assertEquals(500.0, reporte.getMontoTotal(), 0.1);
    }
}
