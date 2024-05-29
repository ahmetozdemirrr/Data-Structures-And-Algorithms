import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a social network graph where each person is a node and each friendship is an edge.
 */
public class SocialNetworkGraph 
{
    Map<String, Person> people = new HashMap<>();
    Map<Person, List<Person>> friendships = new HashMap<>();

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Adds a person to the social network.
     *
     * @param name the name of the person
     * @param age the age of the person
     * @param hobbies the list of hobbies of the person
     * @param timestamp the timestamp when the person joined the network
     */
    public void addPerson(String name, int age, List<String> hobbies, LocalDateTime timestamp) 
    {
        Person person = new Person(name, age, hobbies, timestamp);
        people.put(name, person);
        friendships.put(person, new ArrayList<>());
        System.out.println("Person added: " + person.getName() + " (Timestamp: " + person.getTimestamp().format(formatter) + ")");
    }

    /**
     * Adds a friendship between two people in the social network.
     *
     * @param firstFriend the name of the first friend
     * @param timestamp1 the timestamp when the first friend joined the network
     * @param secondFriend the name of the second friend
     * @param timestamp2 the timestamp when the second friend joined the network
     */
    public void addFriendship(String firstFriend, LocalDateTime timestamp1, String secondFriend, LocalDateTime timestamp2) 
    {
        Person person1 = people.get(firstFriend);
        Person person2 = people.get(secondFriend);

        if (person1 != null && person2 != null) 
        {
            friendships.get(person1).add(person2);
            friendships.get(person2).add(person1);
            System.out.println("Friendship added between " + person1.getName() + " and " + person2.getName());
        } 

        else 
        {
            System.out.println("One or both persons not found in the network.");
        }
    }

    /**
     * Finds a person by name in the social network.
     *
     * @param name the name of the person to find
     * @return the person object if found, otherwise null
     */
    private Person findPerson(String name) 
    {
        return people.get(name);
    }

    /**
     * Finds the shortest path between two people in the social network using BFS.
     *
     * @param startName the name of the start person
     * @param endName the name of the end person
     */
    public void findShortestPath(String startName, String endName) 
    {
        Person start = people.get(startName);
        Person end = people.get(endName);

        if (start == null || end == null) 
        {
            System.out.println("One or both persons not found in the network.");
            return;
        }

        Map<Person, Person> previous = new HashMap<>();
        Queue<Person> queue = new LinkedList<>();
        Set<Person> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        /* Perform BFS to find the shorest path */
        while (!queue.isEmpty()) 
        {
            Person current = queue.poll();

            if (current.equals(end)) 
            {
                printPath(start, end, previous);
                return;
            }

            for (Person neighbor : friendships.get(current)) 
            {
                if (!visited.contains(neighbor)) 
                {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    previous.put(neighbor, current);
                }
            }
        }
        System.out.println("No path found between " + startName + " and " + endName);
    }

    /**
     * Prints the shortest path between two people.
     *
     * @param start the start person
     * @param end the end person
     * @param prev a map of previous nodes for reconstructing the path
     */
    private void printPath(Person start, Person end, Map<Person, Person> prev) 
    {
        List<Person> path = new ArrayList<>();

        /* Reconstrucrt the path from end to start */
        for (Person at = end; at != null; at = prev.get(at)) 
        {
            path.add(at);
        }
        Collections.reverse(path);
        System.out.print("Shortest path: ");

        for (int i = 0; i < path.size(); i++) 
        {
            System.out.print(path.get(i).getName());

            if (i < path.size() - 1) 
            {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    /**
     * Removes a person from the social network.
     *
     * @param name the name of the person
     * @param timestamp the timestamp when the person joined the network
     */
    public void removePerson(String name, LocalDateTime timestamp) 
    {
        Person person = people.get(name);

        if (person == null) 
        {
            System.out.println("Person not found in the network.");
            return;
        }

        /* Remove the person and their friednships from the network */
        people.remove(name);
        friendships.remove(person);

        for (List<Person> friends : friendships.values()) 
        {
            friends.remove(person);
        }
        System.out.println("Person removed: " + name);
    }

    /**
     * Removes a friendship between two people in the social network.
     *
     * @param firstFriend the name of the first friend
     * @param timestamp1 the timestamp when the first friend joined the network
     * @param secondFriend the name of the second friend
     * @param timestamp2 the timestamp when the second friend joined the network
     */
    public void removeFriendship(String firstFriend, LocalDateTime timestamp1, String secondFriend, LocalDateTime timestamp2) 
    {
        Person person1 = people.get(firstFriend);
        Person person2 = people.get(secondFriend);

        if (person1 == null || person2 == null) 
        {
            System.out.println("One or both persons not found in the network.");
            return;
        }

        /* Remove the friednship between the two persons */
        friendships.get(person1).remove(person2);
        friendships.get(person2).remove(person1);
        System.out.println("Friendship removed between " + person1.getName() + " and " + person2.getName());
    }

    /**
     * Suggests friends for a person in the social network based on mutual friends and common hobbies.
     *
     * @param name the name of the person
     * @param timestamp the timestamp when the person joined the network
     * @param maxSuggestions the maximum number of friends to suggest
     */
    public void suggestFriends(String name, LocalDateTime timestamp, int maxSuggestions) 
    {
        Person person = people.get(name);

        if (person == null) 
        {
            System.out.println("Person not found in the network.");
            return;
        }

        Map<Person, Double> scores = new HashMap<>();
        Map<Person, Integer> mutualFriendsCount = new HashMap<>();
        Map<Person, Integer> commonHobbiesCount = new HashMap<>();

        /* Calculate scores for potential friends */
        for (Person candidate : friendships.keySet()) 
        {
            if (candidate.equals(person) || friendships.get(person).contains(candidate)) 
            {
                continue;
            }

            double score = calculateScore(person, candidate);
            scores.put(candidate, score);
            mutualFriendsCount.put(candidate, countMutualFriends(person, candidate));
            commonHobbiesCount.put(candidate, countCommonHobbies(person, candidate));
        }

        List<Map.Entry<Person, Double>> suggestions = new ArrayList<>(scores.entrySet());
        suggestions.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));

        System.out.println("Suggested friends for " + name + ":");
        for (int i = 0; i < Math.min(maxSuggestions, suggestions.size()); i++) 
        {
            Map.Entry<Person, Double> entry = suggestions.get(i);
            Person suggestedPerson = entry.getKey();
            double score = entry.getValue();
            int mutualFriends = mutualFriendsCount.get(suggestedPerson);
            int commonHobbies = commonHobbiesCount.get(suggestedPerson);
            System.out.println(suggestedPerson.getName() + " (Score: " + score + ", " + mutualFriends + " mutual friends, " + commonHobbies + " common hobbies)");
        }
    }

    /**
     * Calculates a score for suggesting friends based on mutual friends and common hobbies.
     *
     * @param person the person for whom to suggest friends
     * @param candidate the candidate friend
     * @return the calculated score
     */
    private double calculateScore(Person person, Person candidate) 
    {
        double score = 0.0;

        /* Calculate score based on mutual friends */
        Set<Person> mutualFriends = new HashSet<>(friendships.get(person));
        mutualFriends.retainAll(friendships.get(candidate));
        score += mutualFriends.size();

        /* Calculate score based on common hobbies */
        Set<String> commonHobbies = new HashSet<>(person.getHobbies());
        commonHobbies.retainAll(candidate.getHobbies());
        score += commonHobbies.size() * 0.5;

        return score;
    }

    /**
     * Counts the number of mutual friends between two people.
     *
     * @param person the first person
     * @param candidate the second person
     * @return the number of mutual friends
     */
    private int countMutualFriends(Person person, Person candidate) 
    {
        Set<Person> mutualFriends = new HashSet<>(friendships.get(person));
        mutualFriends.retainAll(friendships.get(candidate));
        
        return mutualFriends.size();
    }

    /**
     * Counts the number of common hobbies between two people.
     *
     * @param person the first person
     * @param candidate the second person
     * @return the number of common hobbies
     */
    private int countCommonHobbies(Person person, Person candidate) 
    {
        Set<String> commonHobbies = new HashSet<>(person.getHobbies());
        commonHobbies.retainAll(candidate.getHobbies());

        return commonHobbies.size();
    }

    /**
     * Counts the number of clusters in the social network.
     */
    public void countClusters() 
    {
        System.out.println("Counting clusters in the social network...");

        Set<Person> visited = new HashSet<>();
        int clusterCount = 0;

        /* Traverse all people to count clusters */
        for (Person person : friendships.keySet()) 
        {
            if (!visited.contains(person)) 
            {
                clusterCount++;
                List<Person> cluster = new ArrayList<>();
                bfs(person, visited, cluster);
                System.out.println("Cluster " + clusterCount + ":");

                for (Person p : cluster) 
                {
                    System.out.println(p.getName());
                }
            }
        }
        System.out.println("Number of clusters found: " + clusterCount);
    }

    /**
     * Performs a breadth-first search (BFS) to find all people in the same cluster.
     *
     * @param start the starting person
     * @param visited the set of visited people
     * @param cluster the list to store the people in the cluster
     */
    private void bfs(Person start, Set<Person> visited, List<Person> cluster) 
    {
        Queue<Person> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) 
        {
            Person current = queue.poll();
            cluster.add(current);

            for (Person neighbor : friendships.get(current)) 
            {
                if (!visited.contains(neighbor)) 
                {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }
    }
}
