public class Distance<T> {
    private T prev;
    private float distance;

    public Distance(T p, float d){
        this.prev = p;
        this.distance = d;
    }

    public T getPrev() {
        return prev;
    }

    public float getDistance() {
        return distance;
    }

    public void setPrev(T prev) {
        this.prev = prev;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
}
