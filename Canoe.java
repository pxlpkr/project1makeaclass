import java.util.ArrayList;
import java.util.List;

/**
 * A class for representing a Canoe as a simulated object.
 * Is primarily defined by its dimensions {@code length}, {@code width}, and {@code height},
 * as well as the base material it is constructed out of {@code material}.
 * Can store passengers inside, represented in implementation as an {@code ArrayList}
 * of {@code Person} instances. The primary function is to simulate crashes and their
 * effects on the well-being of the boat and its passengers.
 */
public class Canoe {
    /**
     * The length (longer horizontal component) of the canoe, typically in meters
     */
    private double length;

    /**
     * The width (shorter horizontal component) of the canoe, typically in meters
     */
    private double width;

    /**
     * The depth (vertical component) of the canoe, typically in meters
     */
    private double depth;

    /**
     * The material that the canoe is made out of
     */
    private String material;

    /**
     * Amount of damage sustained by the boat. Increases in increments of 0.25
     */
    private double damage;

    private List<Person> passengers = new ArrayList<Person>();

    /**
     * Constructs a canoe with the specified dimensions & material
     *
     * @param  length  the length (longer horizontal component) of the canoe
     * @param  width  the width (shorter horizontal component) of the canoe
     * @param  depth  the depth (vertical component) of the canoe
     * @param  material  the material that the canoe is made out of.
     *                   Functional options include
     *                   {@code "Wood"}, {@code "Aluminium"}, {@code "Plastic"}, 
     *                   {@code "Fiberglass"}, {@code "Kevlar"}
     * @throws IllegalArgumentException if any dimension value is zero or negative
     */
    public Canoe(double length, double width, double depth, String material) throws IllegalArgumentException {
        new ArrayList<Integer>();
        if (length <= 0 || width <= 0 || depth <= 0) {
            throw new IllegalArgumentException("Canoe dimensions must be positive and non-zero");
        }
        this.length = length;
        this.width = width;
        this.depth = depth;
        this.material = material;
        this.damage = 0.0d;
    }

    /**
     * Adds a passenger to the canoe
     * 
     * @param person The target person to add
     */
    public void embark(Person person) {
        this.passengers.add(person);
    }

    /**
     * Removes a passenger from the canoe
     * 
     * @param person The target person to remove
     * 
     * @return {@code true} if this list contained the specified element
     */
    public boolean disembark(Person person) {
        return this.passengers.remove(person);
    }
    
    /**
     * Returns list of all passengers in the canoe
     * 
     * @return list of all passengers in the canoe
     */
    public List<Person> getPassengers() {
        return this.passengers;
    }

    /**
     * Returns the length (longer horizontal dimension component) of the canoe
     * 
     * @return the length
     */
    public double getLength() {
        return this.length;
    }

    /**
     * Returns the width (shorter horizontal dimension component) of the canoe
     * 
     * @return the width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns the depth (vertical dimension component) of the canoe
     * 
     * @return the depth
     */
    public double getDepth() {
        return this.depth;
    }

    /**
     * Returns the material of the canoe
     * 
     * @return the material
     */
    public String getMaterial() {
        return this.material;
    }

    /**
     * Matches the material of the canoe to a somewhat realistic durability value
     * 
     * @return a double value representing the coefficient
     */
    public double getDamage() {
        return this.damage;
    }

    /**
     * Matches the material of the canoe into a somewhat realistic durability value
     * 
     * @return durabilityCoefficient
     */
    private double getDurabilityCoefficient() {
        switch (this.material) {
            case "Wood":
                return 1.5d;
            case "Aluminium":
            case "Plastic":
                return 4.0d;
            case "Fiberglass":
            case "Kevlar":
                return 0.75d;
        }

        return 1.0d;
    }

    /**
     * Gets a random passenger in the boat based on their weights.
     * A passenger that weighs twice as much is twice as likely to
     * be chosen.
     * 
     * @return the randomly chosen passenger
     */
    private Person getRandomPassengerWeighted() {
        //Can't return a passenger if there are no passengers
        if (this.passengers.size() == 0) {
            return null;
        }

        //Calculate the total weight of all passengers
        int max_weight = 0;
        for (Person person : this.passengers) {
            max_weight += person.getWeight();
        }

        /* Randomly chose a weight threshold between 1 and the total weight.
           Then, iterate through passengers until the threshold is met. 
           This will ultimately skew the results based on weight. */
        int target_weight = (int) (Math.random() * max_weight) + 1;
        for (Person person : this.passengers) {
            target_weight -= person.getWeight();
            if (target_weight <= 0) {
                return person;
            }
        }

        return this.passengers.get(this.passengers.size() - 1);
    }

    /**
     * Simulates the canoe crashing. Depending on the state of the canoe,
     * this has a chance of damaging it and killing passengers.
     * Prints a message whenever someone is killed.
     */
    public void crash() {
        double coeff = getDurabilityCoefficient() - this.damage;
        double volume = this.width * this.length * this.depth;

        int dc = (int) (5 + 2 * volume / (0.5 * coeff));
        while (this.passengers.size() > 0 && (Math.random() * 20) + 1 > dc) {
            Person chosen = getRandomPassengerWeighted();
            this.passengers.remove(chosen);
            System.out.printf("[ ! ] %s has died\n", chosen.getName());
            chosen.kill();
            this.damage += 0.25d;
        }
    }

    /**
     * Prints information about the state of the canoe, including:
     * material, dimensions, damage, and passengers.
     */
    public void printStat() {
        System.out.printf("\n%s Canoe:\n", this.material);
        System.out.printf("  %s x %s x %s\n", this.length, this.width, this.depth);
        System.out.printf("  Damage: %s\n  Passengers:\n", this.damage);
        for (Person person : this.passengers) {
            System.out.printf("    %s\n", person.getName());
        }
        System.out.println();
    }
}
