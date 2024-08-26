package filo.mamdouh.kershhelper.models;



public class Client {
    private static Client client= null;
    private User user;
    private Client(User user) {
        this.user = user;
    }
    public static Client getInstance(User user) {
        if (client == null) {
            client = new Client(user);
        }
        return client;
    }
    public Client getInstance() {
        return client;
    }
}
