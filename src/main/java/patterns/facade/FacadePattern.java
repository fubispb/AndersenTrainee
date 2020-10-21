package patterns.facade;


public class FacadePattern {

    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.copy();
    }
}

class Computer {
    private final Power power = new Power();
    private final DVDRom dvd = new DVDRom();
    private final HDD hdd = new HDD();

    public void copy() {
        power.on();
        dvd.load();
        hdd.copyFromDVD(dvd);
        dvd.unload();
        power.off();
    }
}

class Power {
    public void on() {
        System.out.println("Turn on power.");
    }

    public void off() {
        System.out.println("Turn off power.");
    }

}

class DVDRom {
    private boolean data = false;

    public boolean hasData() {
        return data;
    }

    public void load() {
        data = true;
    }

    public void unload() {
        data = false;
    }
}

class HDD {
    public void copyFromDVD(DVDRom dvd) {
        if (dvd.hasData()) {
            System.out.println("Происходит копирование данных...");
        } else {
            System.out.println("Вставьте диск с данными!");
        }
    }

}