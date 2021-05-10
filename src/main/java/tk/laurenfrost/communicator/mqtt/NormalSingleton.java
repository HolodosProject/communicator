package tk.laurenfrost.communicator.mqtt;

public class NormalSingleton {
    
    private NormalSingleton(){}

    private final static NormalSingleton instance = new NormalSingleton();

    public static NormalSingleton getInstance() {
        return instance;
    }

}
