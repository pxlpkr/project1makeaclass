/**
 * Auxilliary class for storing information about a representation of a person.
 * Primarily used in the {@code Canoe} class
 */
public class Person {
    /**
     * The cosmetic name given to the person
     */
    private String name;

    /**
     * The weight of the person, typically in pounds
     */
    private double weight;

    /**
     * Whether the person is currently deceased
     */
    private boolean dead;

    /**
     * Constructs a person with the specified name & weight
     *
     * @param  name  the name to be given to the person
     * @param  weight  the weight (typically pounds) of the person
     * @throws IllegalArgumentException if weight is zero or negative
     */
    public Person(String name, double weight) throws IllegalArgumentException {
        if (weight <= 0) {
            throw new IllegalArgumentException("Person weight must be positive and non-zero");
        }
        this.name = name;
        this.weight = weight;
        dead = false;
    }

    /**
     * Returns the weight of the person
     * 
     * @return the weight of the person
     */
    public double getWeight() {
        return this.weight;
    }

    /**
     * Returns the name of the person
     * 
     * @return the name of the person
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns whether the person is dead
     * 
     * @return whether the person is dead
     */
    public boolean isDead() {
        return this.dead;
    }

    /**
     * Changes the dead state of the person to true
     */
    public void kill() {
        this.dead = true;
    }
}
