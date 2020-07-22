package domain;
import java.util.Timer;

public class Main {
public static void main(String[] args) {
		long tiempoEnMilisegundos = 10000;
		Timer t = new Timer();
		TareaAEjecutarse miTarea = new TareaAEjecutarse();
	    t.scheduleAtFixedRate(miTarea, 0, tiempoEnMilisegundos);

	}
}
