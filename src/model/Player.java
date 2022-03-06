package model;

import java.io.Serializable;

/**
 * @author Nerea Mañez
 */
public class Player implements Serializable {
    private String name;

    /**
     * Create a new player
     *
     * @param name the name of the player
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the player
     *
     * @return a string, the name
     */
    public String getName() {
        return name;
    }
}
