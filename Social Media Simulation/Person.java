import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an individual in the social network.
 * Each person has a name, age, hobbies, and a timestamp indicating when they joined the network.
 */
public class Person 
{
    private String name;
    private int age;
    private List<String> hobbies;
    private LocalDateTime timestamp;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Constructs a new Person with the specified name, age, and hobbies.
     * The timestamp is set to the current date and time.
     *
     * @param name the name of the person
     * @param age the age of the person
     * @param hobbies the list of hobbies of the person
     */
    public Person(String name, int age, List<String> hobbies)
    {
        this.name = name;
        this.age = age;
        this.hobbies = hobbies;
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Constructs a new Person with the specified name, age, hobbies, and timestamp.
     *
     * @param name the name of the person
     * @param age the age of the person
     * @param hobbies the list of hobbies of the person
     * @param timestamp the timestamp when the person joined the network
     */
    public Person(String name, int age, List<String> hobbies, LocalDateTime timestamp) 
    {
        this.name = name;
        this.age = age;
        this.hobbies = hobbies;
        this.timestamp = timestamp;
    }

    /**
     * Gets the name of the person.
     *
     * @return the name of the person
     */
    public String getName() 
    {
        return name;
    }

    /**
     * Gets the age of the person.
     *
     * @return the age of the person
     */
    public int getAge() 
    {
        return age;
    }

    /**
     * Gets the hobbies of the person.
     *
     * @return the list of hobbies of the person
     */
    public List<String> getHobbies() 
    {
        return hobbies;
    }

    /**
     * Gets the timestamp when the person joined the network.
     *
     * @return the timestamp when the person joined the network
     */
    public LocalDateTime getTimestamp() 
    {
        return timestamp;
    }

    /**
     * Returns a string representation of the person.
     *
     * @return a string representation of the person
     */
    @Override
    public String toString() 
    {
        return name + " (Timestamp: " + timestamp.format(formatter) + ")";
    }
}
