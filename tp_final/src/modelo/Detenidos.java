package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Detenidos {

	private ArrayList<CCDTyE> centrosEnLosQueEstuvo = new ArrayList<CCDTyE>();
	private Testigo testigoDelDetenido;
	
	public abstract long tiempoCautiverio();
	public abstract boolean sobrevivio();
	public LocalDate fechaCierre() {
		LocalDate fechaCierre = null;
		for (CCDTyE cc : centrosEnLosQueEstuvo) {
			fechaCierre = cc.getFecha_fin(); // fechaCierre puede usar el formato
					
		}
		
		return fechaCierre;
	}
	public LocalDate fechaApertura() {
		LocalDate fechaApertura = null;
		fechaApertura = centrosEnLosQueEstuvo.get(0).getFecha_inicio();
		 // fechaApertura puede usar el formato
		
		return fechaApertura;
	}
	
	
	public void añadirCentro(modelo.CCDTyE CCDTyE) {
		this.centrosEnLosQueEstuvo.add(CCDTyE);
	}
	public Testigo getTestigoDelDetenido() {
		return testigoDelDetenido;
	}
	public void setTestigoDelDetenido(Testigo testigoDelDetenido) {
		this.testigoDelDetenido = testigoDelDetenido;
	}
	public ArrayList<CCDTyE> getCentrosEnLosQueEstuvo() {
		return centrosEnLosQueEstuvo;
	}
}
