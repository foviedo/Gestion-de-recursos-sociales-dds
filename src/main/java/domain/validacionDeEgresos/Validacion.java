package domain.validacionDeEgresos;

import java.util.List;

import domain.Egreso;
import domain.Item;

public enum Validacion {
	VALIDAR_CANTIDAD_PRESUPUESTOS{
		@Override
		public boolean esValido(Egreso unEgreso) {
			return unEgreso.tenesLosPresupuestosSuficientes();
		}	
	},
	VALIDAR_COMPRA_EN_BASE_A_PRESUPUESTO{
		@Override
		public boolean esValido(Egreso unEgreso) {
			//return unEgreso.getPresupuestos().stream().anyMatch(unPresupuesto -> unEgreso.getListaDeItems().equals(unPresupuesto.getListaItems())); // Se puede delegar
			return unEgreso.getPresupuestos().stream().anyMatch(unPresupuesto -> this.coinciden(unEgreso.getListaDeItems(),unPresupuesto.getListaItems()));
		}
		public boolean coinciden(List<Item> listaEgreso, List<Item> listaPresupuesto) {
			boolean primerCond = listaEgreso.stream().allMatch(unItemDeEgreso -> listaPresupuesto.stream()
					.anyMatch(unItemDePresupuesto -> unItemDePresupuesto.getDescripcion() == unItemDeEgreso.getDescripcion() 
					&& unItemDePresupuesto.getMonto() == unItemDePresupuesto.getMonto()));
			return primerCond  && listaEgreso.size() == listaPresupuesto.size();
		}
	},
	VALIDAR_CRITERIO_PROVEEDOR{
		@Override
		public boolean esValido(Egreso unEgreso) {
			double minimoPresupuesto = unEgreso.getPresupuestos().stream()
					.mapToDouble(unPresupuesto -> unPresupuesto.total()).min().getAsDouble();
			return minimoPresupuesto == unEgreso.getMontoTotal();
		}
	};
	public abstract boolean esValido(Egreso unEgreso);
}
		
		

