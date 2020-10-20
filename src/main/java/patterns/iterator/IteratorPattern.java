package patterns.iterator;

public class IteratorPattern {

    public static void main(String[] args) {

        ConcreteAggregate c = new ConcreteAggregate();
        Iterator it = c.getIterator();

        while (it.hasNext()) {
            System.out.println((String) it.next());
        }

    }
}

interface Iterator {

    boolean hasNext();

    Object next();
}

interface Aggregate {
    Iterator getIterator();
}

class ConcreteAggregate implements Aggregate {
    String[] tasks = {"Построить дом", "Родить сына", "Посадить дерево"};

    @Override
    public Iterator getIterator() {
        return new TaskIterator();
    }

    private class TaskIterator implements Iterator {

        int index = 0;

        @Override
        public boolean hasNext() {
            if (index < tasks.length) {
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            return tasks[index++];
        }
    }
}
