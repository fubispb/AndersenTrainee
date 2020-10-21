package patterns.delegate;

class DelegatePattern {
    public static void main(String[] args) {
        Servant servant = new Servant();
        servant.setKitchen(new Cocktail());
        servant.cook();
        servant.setKitchen(new Miso());
        servant.cook();
        servant.setKitchen(new Salad());
        servant.cook();
    }
}

interface Kitchen {
    void cook();
}

class Cocktail implements Kitchen {
    @Override
    public void cook() {
        System.out.println("Cocktail cooking....");
    }
}

class Salad implements Kitchen {
    @Override
    public void cook() {
        System.out.println("Salad cooking....");
    }
}

class Miso implements Kitchen {
    @Override
    public void cook() {
        System.out.println("Miso cooking....");
    }
}

class Servant {
    private Kitchen kitchen;

    void setKitchen(Kitchen k) {
        kitchen = k;
    }

    void cook() {
        kitchen.cook();
    }
}
