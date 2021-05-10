package tk.laurenfrost.communicator.mqtt;

public class SafeSingleton {

    private static SafeSingleton instance;

    public static SafeSingleton getInstance() {
        if (instance == null) {
            synchronized (SafeSingleton.class) {
                if (instance == null)
                    instance = new SafeSingleton();
            }
        }
        return instance;
    }

}
