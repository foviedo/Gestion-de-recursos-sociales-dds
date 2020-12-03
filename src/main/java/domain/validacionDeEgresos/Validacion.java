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
			System.out.println(unEgreso.getPresupuestos().get(0).getListaItems().get(0).getDescripcion());
			//return unEgreso.getPresupuestos().stream().anyMatch(unPresupuesto -> unEgreso.getListaDeItems().equals(unPresupuesto.getListaItems())); // Se puede delegar
			boolean resultado = unEgreso.getPresupuestos().stream().anyMatch(unPresupuesto -> this.coinciden(unEgreso.getListaDeItems(),
					unPresupuesto.getListaItems()));
			if(!resultado) {
				System.out.println("fallo en 2");
			}
			return resultado;
		}
		public boolean coinciden(List<Item> listaEgreso, List<Item> listaPresupuesto) {
			boolean primerCond = listaEgreso.stream().allMatch(unItemDeEgreso -> listaPresupuesto.stream()
					.anyMatch(unItemDePresupuesto -> unItemDePresupuesto.getDescripcion().equals(unItemDeEgreso.getDescripcion()) 
					&& unItemDePresupuesto.getMonto() == unItemDeEgreso.getMonto()));
			if(listaEgreso.size() != listaPresupuesto.size()) 
				System.out.println("los tamanios son distintos");
			System.out.println(listaEgreso.size());
			System.out.println(listaPresupuesto.size());
			System.out.println(primerCond);

			System.out.println(listaEgreso.get(0).getDescripcion());
			System.out.println(listaPresupuesto.get(0).getDescripcion());
			return primerCond  && listaEgreso.size() == listaPresupuesto.size();
		}
	},
	VALIDAR_CRITERIO_PROVEEDOR{
		@Override
		public boolean esValido(Egreso unEgreso) {
			double minimoPresupuesto = unEgreso.getPresupuestos().stream()
					.mapToDouble(unPresupuesto -> unPresupuesto.total()).min().getAsDouble();
			boolean resultado =  minimoPresupuesto == unEgreso.getMontoTotal();
			if (!resultado) {
				System.out.println("fallo en 3");
			}
			return resultado;
		}
	};
	public abstract boolean esValido(Egreso unEgreso);
}
		
		

