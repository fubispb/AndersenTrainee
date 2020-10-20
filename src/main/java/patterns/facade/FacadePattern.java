package patterns.facade;


public class FacadePattern {

    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.copy();
    }
}

class Computer {
    Power power = new Power();
    DVDRom dvd = new DVDRom();
    HDD hdd = new HDD();

    void copy() {
        power.on();
        dvd.load();
        hdd.copyFromDVD(dvd);
        dvd.unload();
        power.off();
    }
}

class Power {
    void on() {
        System.out.println("Включение питания.");
    }

    void off() {
        System.out.println("Выключение питания.");
    }

}

class DVDRom {
    private boolean data = false;

    public boolean hasData() {
        return data;
    }

    void load() {
        data = true;
    }

    void unload() {
        data = false;
    }
}

class HDD {
    void copyFromDVD(DVDRom dvd) {
        if (dvd.hasData()) {
            System.out.println("Происходит копирование данных...");
        } else {
            System.out.println("Вставьте диск с данными!");
        }
    }

}