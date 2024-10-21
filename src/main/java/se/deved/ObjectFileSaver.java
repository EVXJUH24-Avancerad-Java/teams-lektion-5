package se.deved;

public class ObjectFileSaver<T extends ObjectFileSaver.Serializable> {

    public void save(T value) {
        String string = value.serialize();
        // Save to file
    }

    public interface Serializable {
        String serialize();
    }
}