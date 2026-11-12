import java.util.HashMap;
import java.util.PriorityQueue;

public class GraphOriented<T> extends Graph<T> {

    @Override
    public void addEdge(T source, T dest, float weight) {
        Node<T> startNode = nodes.get(source);
        Node<T> destNode = nodes.get(dest);

        if (startNode != null && destNode != null) {
            startNode.addAdjacency(destNode, weight);
        } else {
            System.out.println("One or both nodes do not exist.");
        }
    }

    public HashMap<T, Distance<T>> dijkstra(T source) {
        HashMap<T, Distance<T>> risultati = new HashMap<>();

        for (T key : nodes.keySet()) {
            risultati.put(key, new Distance<>(null, Float.MAX_VALUE));
        }
        if (risultati.containsKey(source)) {
            risultati.get(source).setDistance(0);
        }

        PriorityQueue<T> coda = new PriorityQueue<>((a, b) ->
                Float.compare(risultati.get(a).getDistance(), risultati.get(b).getDistance())
        );

        coda.add(source);

        while (!coda.isEmpty()) {
            T corrente = coda.poll();
            Node<T> nodoCorrente = nodes.get(corrente);
            float distCorrente = risultati.get(corrente).getDistance();

            if (distCorrente == Float.MAX_VALUE) break;

            for (var entry : nodoCorrente.getAdjacencies().entrySet()) {
                Node<T> vicino = entry.getKey();
                float pesoArco = entry.getValue();
                T valoreVicino = vicino.getValue();

                float nuovaDistanza = distCorrente + pesoArco;

                if (nuovaDistanza < risultati.get(valoreVicino).getDistance()) {
                    risultati.get(valoreVicino).setDistance(nuovaDistanza);
                    risultati.get(valoreVicino).setPrev(corrente);

                    coda.remove(valoreVicino);
                    coda.add(valoreVicino);
                }
            }
        }
        return risultati;
    }

    public HashMap<T, Distance<T>> bellman_ford(T source) {
        HashMap<T, Distance<T>> risultati = new HashMap<>();

        for (T key : nodes.keySet()) {
            risultati.put(key, new Distance<>(null, Float.MAX_VALUE));
        }
        if (risultati.containsKey(source)) {
            risultati.get(source).setDistance(0);
        }

        int numeroNodi = nodes.size();

        for (int i = 0; i < numeroNodi - 1; i++) {
            for (Node<T> u : nodes.values()) {
                float distU = risultati.get(u.getValue()).getDistance();
                if (distU == Float.MAX_VALUE) continue;

                for (var entry : u.getAdjacencies().entrySet()) {
                    Node<T> v = entry.getKey();
                    float peso = entry.getValue();
                    float distV = risultati.get(v.getValue()).getDistance();

                    if (distU + peso < distV) {
                        risultati.get(v.getValue()).setDistance(distU + peso);
                        risultati.get(v.getValue()).setPrev(u.getValue());
                    }
                }
            }
        }
        return risultati;
    }
}
