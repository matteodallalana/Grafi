import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        System.out.println("1. TEST GRAFO ORIENTATO (Dijkstra & Bellman-Ford)");

        GraphOriented<String> go = new GraphOriented<>();

        go.addNode("A");
        go.addNode("B");
        go.addNode("C");
        go.addNode("D");

        go.addEdge("A", "B", 4.0f);
        go.addEdge("B", "C", 2.0f);
        go.addEdge("A", "C", 9.0f);
        go.addEdge("C", "D", 1.5f);

        System.out.println("\n--- Struttura del Grafo Orientato ---");
        System.out.print(go);

        System.out.println("--- Esecuzione Dijkstra (Sorgente: A) ---");
        HashMap<String, Distance<String>> resDijkstra = go.dijkstra("A");
        stampaRisultatiDistanze(resDijkstra);

        System.out.println("--- Esecuzione Bellman-Ford (Sorgente: A) ---");
        HashMap<String, Distance<String>> resBellman = go.bellman_ford("A");
        stampaRisultatiDistanze(resBellman);


        System.out.println("2. TEST GRAFO NON ORIENTATO (Algoritmo di Prim)");

        GraphNotOriented<String> gno = new GraphNotOriented<>();

        gno.addNode("X");
        gno.addNode("Y");
        gno.addNode("Z");

        gno.addEdge("X", "Y", 3.0f);
        gno.addEdge("Y", "Z", 4.0f);
        gno.addEdge("X", "Z", 15.0f);

        System.out.println("\n--- Struttura del Grafo Non Orientato Originale ---");
        System.out.print(gno);

        // Calcolo del Minimum Spanning Tree tramite Prim partendo da "X"
        System.out.println("--- Calcolo del Minimum Spanning Tree (Prim da X) ---");
        GraphNotOriented<String> mst = gno.prim("X");

        System.out.println("\n--- Struttura del MST Risultante (Arco pesante X-Z rimosso) ---");
        System.out.print(mst);
    }

    private static void stampaRisultatiDistanze(HashMap<String, Distance<String>> mappaRisultati) {
        if (mappaRisultati == null) {
            System.out.println("Nessun risultato disponibile (possibile ciclo negativo rilevato).");
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
