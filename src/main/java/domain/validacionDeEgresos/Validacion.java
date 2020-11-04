package domain.validacionDeEgresos;

import domain.Egreso;

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
			return unEgreso.getPresupuestos().stream().anyMatch(unPresupuesto -> unEgreso.getListaDeItems().equals(unPresupuesto.getListaItems())); // Se puede delegar
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
		
		

