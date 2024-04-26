package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Detenidos {

	private ArrayList<CCDTyE> centrosEnLosQueEstuvo = new ArrayList<>();
	private Testigo testigoDelDetenido;

	public abstract long tiempoCautiverio();

	public abstract boolean sobrevivio();

	public LocalDate fechaCierre() {
		String fechaCierre = null;
		for (CCDTyE x : centrosEnLosQueEstuvo) {
			fechaCierre = x.getFecha_fin().toString(); // aca toma el valor
		}
		LocalDate f_cierre = LocalDate.parse(fechaCierre);
		return f_cierre;
	}

	public LocalDate fechaApertura() {
		String fechaApertura = null;
		for (CCDTyE x : centrosEnLosQueEstuvo) {
			fechaApertura = x.getFecha_inicio().toString(); // aca toma el valor
		}
		LocalDate f_apertura = LocalDate.parse(fechaApertura);
		return f_apertura;
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
	public void setCentrosEnLosQueEstuvo(ArrayList<CCDTyE> centros) {
		this.centrosEnLosQueEstuvo = centros;
	}
}
