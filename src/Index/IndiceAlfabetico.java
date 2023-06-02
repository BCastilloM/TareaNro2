package Index;
import AVLTree.ArbolAVL;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class IndiceAlfabetico {
    private ArbolAVL indice;

    public IndiceAlfabetico() {
        indice = new ArbolAVL();
    }

    public void creandoIndice(String archivo) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(archivo));
        int pagina=1;
        while (scan.hasNext()) {
            String linea = scan.next();
            String[] palabras = linea.split("\\\\");
            for (String palabra: palabras) {
                if (!palabra.isEmpty()) {
                    indice.insertar(palabra, pagina);
                }
            }


            pagina++;
        }
        scan.close();
    }

    public void mostrarIndice() {
        indice.recorridoInOrden();
    }

    public void buscarPaginas(String palabraBusca) {
        ArbolAVL.NodoAVL nodo = indice.buscar(palabraBusca); // Búsqueda de la palabra en el índice alfabético

        if (nodo != null) {
            System.out.println("Páginas en las que aparece la palabra " + palabraBusca + ": " + nodo.getPalabra());
        } else {
            System.out.println("La palabra " + palabraBusca + " no se encontró en el índice.");
        }
    }
}