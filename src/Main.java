import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        System.out.println("1. TEST GRAFO ORIENTATO ");

        GraphOriented<String> go = new GraphOriented<>();

        go.addNode("A");
        go.addNode("B");
        go.addNode("C");
        go.addNode("D");
        go.addNode("E");
        go.addNode("F");

        go.addEdge("A", "B", 2);
        go.addEdge("B", "C", 7);
        go.addEdge("A", "D", 9);
        go.addEdge("A", "F", 20);
        go.addEdge("B","D", 6);
        go.addEdge("B","C", 3);
        go.addEdge("C","D", 1);
        go.addEdge("C","E", 8);
        go.addEdge("D","E", 4);
        go.addEdge("D","F", 3);
        go.addEdge("E","F", 2);
        go.addEdge("E","C", 1);
        go.addEdge("F","D", 3);



        System.out.println("\n Struttura del Grafo Orientato ");
        System.out.print(go);

        System.out.println(" Esecuzione Dijkstra (Sorgente: A) ");


        HashMap<String, Distance<String>> resDijkstra = go.dijkstra("A");
        stampaRisultatiDistanze(resDijkstra);

        System.out.println(" Esecuzione Bellman-Ford (Sorgente: A) ");
        HashMap<String, Distance<String>> resBellman = go.bellman_ford("A");
        stampaRisultatiDistanze(resBellman);


        System.out.println("2. TEST GRAFO NON ORIENTATO (Algoritmo di Prim)");

        GraphNotOriented<String> gno = new GraphNotOriented<>();

        gno.addNode("A");
        gno.addNode("B");
        gno.addNode("C");
        gno.addNode("D");
        gno.addNode("E");
        gno.addNode("F");


        gno.addEdge("A", "B", 1);
        gno.addEdge("A", "C", 3);
        gno.addEdge("B", "C", 3);
        gno.addEdge("B", "D", 6);
        gno.addEdge("D", "C", 2);
        gno.addEdge("D", "E", 4);
        gno.addEdge("D", "F", 5);
        gno.addEdge("C", "E", 4);
        gno.addEdge("F", "E", 2);

        System.out.println("\n Struttura del Grafo Non Orientato Originale ");
        System.out.print(gno);

        System.out.println(" Calcolo del Minimum Spanning Tree Prim");
        GraphNotOriented<String> mstPrim = gno.prim("A");

        System.out.println("\n Struttura del MST");
        System.out.print(mstPrim);

        System.out.println(" Calcolo del Minimum Spanning Tree Kruskal");
        GraphNotOriented<String> mstKru = gno.kruskal();

        System.out.println("\n Struttura del MST");
        System.out.print(mstKru);

    }

    private static void stampaRisultatiDistanze(HashMap<String, Distance<String>> mappaRisultati) {
        if (mappaRisultati == null) {
            System.out.println("Nessun risultato disponibile");
            return;
        }

        mappaRisultati.forEach((nodoDestinazione, infoDistanza) -> {
            String distStr = (infoDistanza.getDistance() == Float.MAX_VALUE) ? "Infinito" : String.valueOf(infoDistanza.getDistance());
            String prevStr = (infoDistanza.getPrev() == null) ? "Nessuno" : infoDistanza.getPrev();
            System.out.println("Nodo [" + nodoDestinazione + "] -> Distanza Totale: " + distStr + ", Nodo Precedente: " + prevStr);
        });
        System.out.println();
    }
}
