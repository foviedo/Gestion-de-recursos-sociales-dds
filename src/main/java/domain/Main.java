package domain;
import java.util.Timer;

public class Main {
public static void main(String[] args) {
		long tiempoEnMilisegundos = 10000;
		Timer t = new Timer();
		TareaValidarEgresos unaTareaQueValida = new TareaValidarEgresos();
	    t.scheduleAtFixedRate(unaTareaQueValida, 0, tiempoEnMilisegundos);

	}
}
