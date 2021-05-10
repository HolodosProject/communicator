package tk.laurenfrost.communicator.mqtt;

public class NotSafeSingleton {

    private static NotSafeSingleton instance;

    public static NotSafeSingleton getInstance() {
        if (instance == null) {
            synchronized (NotSafeSingleton.class) {
                instance = new NotSafeSingleton();
            }
        }
        return instance;
    }

}
