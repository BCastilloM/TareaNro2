package AVLTree;
import java.util.ArrayList;
import java.util.List;

public class ArbolAVL {
    private NodoAVL root;

    public ArbolAVL() {
        root = null;
    }

    public class NodoAVL {
        private String palabra;
        private List<Integer> paginas;
        private NodoAVL left;
        private NodoAVL right;
        private int altura;

        public NodoAVL(String palabra, int pagina) {
            this.palabra = palabra;
            this.paginas = new ArrayList<>();
            this.paginas.add(pagina);
            this.left = null;
            this.right = null;
            this.altura = 1;
        }

        public String getPalabra() {
            return palabra;
        }

        public void actualizarAltura() {
            int alturaIzquierdo = (left != null) ? left.altura : 0;
            int alturaDerecho = (right != null) ? right.altura : 0;
            altura = Math.max(alturaIzquierdo, alturaDerecho) + 1;
        }

        public int getBalance() {
            int alturaIzquierdo = (left != null) ? left.altura : 0;
            int alturaDerecho = (right != null) ? right.altura : 0;
            return alturaIzquierdo - alturaDerecho;
        }

        public void agregarPagina(int numeroPagina) {
            if (!paginas.contains(numeroPagina)) {
                paginas.add(numeroPagina);
            }
        }
    }

    public void insertar(String palabra, int pagina) {
        root = insertarNodo(root, palabra, pagina);
    }

    private NodoAVL insertarNodo(NodoAVL nodo, String palabra, int pagina) {
        if (nodo == null) {
            return new NodoAVL(palabra, pagina);
        }

        int comparacion = palabra.compareTo(nodo.palabra);

        if (comparacion < 0) {
            nodo.left = insertarNodo(nodo.left, palabra, pagina);
        } else if (comparacion > 0) {
            nodo.right = insertarNodo(nodo.right, palabra, pagina);
        } else {
            nodo.agregarPagina(pagina);
            return nodo;
        }

        nodo.actualizarAltura();
        return balancear(nodo);
    }

    private NodoAVL balancear(NodoAVL nodo) {
        int balance = nodo.getBalance();

        if (balance > 1) {
            if (nodo.left.getBalance() < 0) {
                nodo.left = rotarIzquierda(nodo.left);
            }
            return rotarDerecha(nodo);
        }

        if (balance < -1) {
            if (nodo.right.getBalance() > 0) {
                nodo.right = rotarDerecha(nodo.right);
            }
            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    private NodoAVL rotarDerecha(NodoAVL nodo) {
        NodoAVL nRoot = nodo.left;

        nodo.left = nRoot.right;
        nRoot.right = nodo;

        nodo.actualizarAltura();
        nRoot.actualizarAltura();

        return nRoot;
    }

    private NodoAVL rotarIzquierda(NodoAVL nodo) {
        NodoAVL nRoot = nodo.right;

        nodo.right = nRoot.left;
        nRoot.left = nodo;

        nodo.actualizarAltura();
        nRoot.actualizarAltura();

        return nRoot;
    }

    public NodoAVL buscar(String palabra) {
        return buscarNodo(root, palabra);
    }

    private NodoAVL buscarNodo(NodoAVL nodo, String palabra) {
        if (nodo == null) {
            return null;
        }

        int comparacion = palabra.compareTo(nodo.palabra);

        if (comparacion < 0) {
            return buscarNodo(nodo.left, palabra);
        } else if (comparacion > 0) {
            return buscarNodo(nodo.right, palabra);
        } else {
            return nodo;
        }
    }

    public void recorridoInOrden() {
        recorridoInOrden(root);
    }

    private void recorridoInOrden(NodoAVL nodo) {
        if (nodo != null) {
            recorridoInOrden(nodo.left);
            System.out.println(nodo.palabra + ": " + nodo.paginas);
            recorridoInOrden(nodo.right);
        }
    }
}