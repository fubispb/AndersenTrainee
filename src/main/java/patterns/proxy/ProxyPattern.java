package patterns.proxy;

public class ProxyPattern {

    public static void main(String[] args) {

        Image image = new ProxyImage("D:/image/my.jpg");
        image.display();
    }

}

interface Image {
    void display();
}

class RealImage implements Image {

    private final String file;

    public RealImage(String file) {
        this.file = file;
        load();
    }

    public void load() {
        System.out.println("Loading " + file);
    }

    @Override
    public void display() {
        System.out.println("Viewing " + file);
    }
}

class ProxyImage implements Image {

    private final String file;
    private RealImage image;

    public ProxyImage(String file){
        this.file = file;
    }

    @Override
    public void display() {
        if(image == null) {
            image = new RealImage(file);
        }
        image.display();
    }
}