public class Pair
{
    private Cell node;
    private Cell neighbour;

    public Pair(Cell main_item, Cell sub_item)
    {
        this.node = main_item;
        this.neighbour = sub_item;
    }

    public Cell node()
    {
    	return this.node;
    }

    public Cell neighbour()
    {
    	return this.neighbour;
    }
}