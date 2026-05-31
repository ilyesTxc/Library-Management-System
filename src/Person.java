public abstract class Person {
    private final String id;
    private final String name;

    Person(String id, String name){
        this.id = id;
        this.name = name;
        // this(id,name) that means call another constructor from the same class thats why it doesnt work
    }
    public abstract void role();
}
