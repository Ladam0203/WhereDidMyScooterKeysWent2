package be;

public class Exit {
    private String direction;
    private Area toArea;
    private Key key;

    public Exit(String direction, Area toArea, Key key)
    {
        this.direction = direction;
        this.toArea = toArea;
        this.key = key;
    }

    public String direction() {
        return direction;
    }

    public Area toArea()
    {
        return toArea;
    }

    public Key requiredKey()
    {
        return key;
    }

    public boolean requiresKey()
    {
        return key != null;
    }
}
