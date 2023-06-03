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

            if (linea.contains("|")) {
                linea = linea.replace("|", "");
                pagina++;
            }

            String[] palabras = linea.split("\\\\");
            for (String palabra: palabras) {
                if (!palabra.isEmpty()) {
                    if (palabra.length() > 20){
                        linea = linea.substring(0, 20);
                    }
                    indice.insertar(palabra, pagina);
                }
            }
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

    public static void main(String[] args) {
        IndiceAlfabetico indice = new IndiceAlfabetico();
        String archivo = "Arbol.txt";
        Scanner scan = new Scanner(System.in);
        String palabraBuscada;

        try {
            indice.creandoIndice(archivo);
        } catch (FileNotFoundException e) {
            System.out.println("El archivo: " + archivo + ", no se pudo encontrar");
            return;
        }

        System.out.println("Índice creado correctamente");

        indice.mostrarIndice();

        System.out.print("Ingresa una palabra para buscar en el índice");
        palabraBuscada = scan.nextLine();


        System.out.println("Buscando '" + palabraBuscada + "' en el índice:");
        indice.buscarPaginas(palabraBuscada);
        System.out.println();
    }
}

