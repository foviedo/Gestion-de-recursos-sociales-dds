package domain;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;

public class DireccionPostal {
	
	String calle;
	String altura;
	String piso;
	String departamento;
	ClientResponse cliente;
	
	void pais() {
		cliente =  Client.create()
	       .resource("https://api.mercadolibre.com/")
	       .path("classified_locations/countries")
	       .accept(MediaType.APPLICATION_JSON) 
	       .get(ClientResponse.class);

		
	}
	 
}
