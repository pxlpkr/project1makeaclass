public class CanoeRunner {
    public static void main(String[] args) {
        Canoe canoe = new Canoe(5, 0.85, 0.35, "Wood");
        Person isaac = new Person("Isaac", 250);
        Person axel = new Person("Axel", 90);
        Person micah = new Person("Micah", 170);
        canoe.embark(isaac);
        canoe.embark(axel);
        canoe.embark(micah);
        canoe.crash();
        canoe.printStat();
    }
}