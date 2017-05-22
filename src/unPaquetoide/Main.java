package unPaquetoide;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		ImagenEspacial im = new ImagenEspacial("01");
		im.resolver();
		im.mostrarSolucion();
		im.imprimirSolucion();

	}

}
