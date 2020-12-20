package domain.validacionDeEgresos;

import java.util.Timer;

public class ValidadorDeEgresosEjecutable {
long tiempoEnMilisegundos = 100000; 
static final ValidadorDeEgresosEjecutable INSTANCE = new ValidadorDeEgresosEjecutable();
private ValidadorDeEgresosEjecutable() {};


public void setTiempo(long tiempoNuevo) {
	this.tiempoEnMilisegundos = tiempoNuevo;
}

public void validarTareasCadaTiempoEnMilisegundos() {
	Timer t = new Timer();
	TareaValidarEgresos unaTareaQueValida = new TareaValidarEgresos();
  //  t.scheduleAtFixedRate(unaTareaQueValida, 0, tiempoEnMilisegundos);
	//DEPRECADO
}

static public ValidadorDeEgresosEjecutable getInstance(){
	return INSTANCE;
}


}
