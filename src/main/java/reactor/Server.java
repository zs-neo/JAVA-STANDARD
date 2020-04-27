package reactor;

public class Server {
  
  Selector selector = new Selector();
  Dispatcher eventLooper = new Dispatcher(selector);
  Acceptor acceptor;
  
  public Server(int port) {
    acceptor = new Acceptor(port, selector);
  }
  
  public void start(){
    System.out.println("server start");
    eventLooper.registerEventHandler(EventType.ACCEPT,new AcceptEventHandler(selector));
    new Thread(acceptor,"acceptor-"+acceptor.getPort()).start();
    eventLooper.handleEvents();
  }
  
  public static void main(String[] args) {
    Server server = new Server(3306);
    server.acceptor.addNewConnection(new InputSource("123",1));
    server.acceptor.addNewConnection(new InputSource("1ww223",2));
    server.start();
  }
}
