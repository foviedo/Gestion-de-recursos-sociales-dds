package domain;

public class Main {
public static void main(String[] args) {
	
	ValidadorDeEgresos.getInstance().validarEgresoPendientes();
	System.out.println("ANDUVO");
	}
}
