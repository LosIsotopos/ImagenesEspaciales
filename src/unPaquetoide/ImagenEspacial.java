package unPaquetoide;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ImagenEspacial {
	private String lineaAComprimir;
	private String lineaComprimida;
	private StringBuilder comprimida = new StringBuilder();
	private StringBuilder descomprimida = new StringBuilder();
	private String path;
	public ImagenEspacial(String path) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(path+".in"));
		lineaAComprimir = new String(sc.next());
		lineaComprimida = new String(sc.next());
		this.path = path;
		sc.close();
	}
	
	public void resolver() {
		comprimir();
		descomprimir();
	}

	private void descomprimir() {
		int i = 0;
		int pos = 0;
		int offset = 3;
		if(lineaComprimida.indexOf('(') != -1 ) {
			while (i < lineaComprimida.length()) {
				if (lineaComprimida.charAt(i) == '(') {
					i++;
					pos = lineaComprimida.indexOf(')',i);
					if (pos == i+2) {
						pos = Character.getNumericValue(lineaComprimida.charAt(i+1));
					} else {
						offset = lineaComprimida.substring(i+1, pos).length() + 2;
						pos = Integer.valueOf(lineaComprimida.substring(i+1, pos));
					}
					for (int j = 0; j < pos; j++) {
						descomprimida.append(lineaComprimida.charAt(i));
					}
					i += offset;
					offset = 3;
				} else {
					descomprimida.append(lineaComprimida.charAt(i));
					i++;
				}
			}
		} else {
			descomprimida = new StringBuilder(lineaComprimida);
		}
		
	}

	private void comprimir() {
		
		int cantidadOcurrencias = 1;
		for (int i = 0; i < lineaAComprimir.length(); i++) {
			if(i+1 < lineaAComprimir.length() && lineaAComprimir.charAt(i) == lineaAComprimir.charAt(i+1)) {
				cantidadOcurrencias++;
			} else if (cantidadOcurrencias > 4) {
				comprimida.append("(" + lineaAComprimir.charAt(i) + cantidadOcurrencias + ")");
				cantidadOcurrencias = 1;
			} else {
				for (int j = 0; j < cantidadOcurrencias; j++) {
					comprimida.append(lineaAComprimir.charAt(i));
				}
				cantidadOcurrencias = 1;
			}
		}
	}
	
	public void mostrarSolucion() {
		System.out.println(comprimida);
		System.out.println(descomprimida);
	}
	
	public void imprimirSolucion() throws IOException {
		PrintWriter pr = new PrintWriter(new FileWriter(path+".out"));
		pr.println(comprimida);
		pr.println(descomprimida);
		pr.close();
	}
	
	
}
