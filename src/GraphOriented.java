import java.util.HashMap;
import java.util.PriorityQueue;

public class GraphOriented<T> extends Graph<T> {

    public GraphOriented(String rrcc) {
        super(rrcc);
    }

    @Override
    public void addEdge(T source, T dest, float weight) {
        Node startNode = nodes.get(source);
        Node destNode = nodes.get(dest);

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
            Node nodoCorrente = nodes.get(corrente);
            float distCorrente = risultati.get(corrente).getDistance();

            if (distCorrente == Float.MAX_VALUE) break;

            for (var entry : nodoCorrente.getAdjacencies().entrySet()) {
                Node vicino = entry.getKey();
                float pesoArco = entry.getValue();
                T valoreVicino = vicino.getId();

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
            for (Node u : nodes.values()) {
                float distU = risultati.get(u.getId()).getDistance();
                if (distU == Float.MAX_VALUE) continue;

                for (var entry : u.getAdjacencies().entrySet()) {
                    Node v = entry.getKey();
                    float peso = entry.getValue();
                    float distV = risultati.get(v.getId()).getDistance();

                    if (distU + peso < distV) {
                        risultati.get(v.getId()).setDistance(distU + peso);
                        risultati.get(v.getId()).setPrev(u.getId());
                    }
                }
            }
        }
        return risultati;
    }
}
