package se.ifmo.programming.lab7.data;

/**
 * Класс пещеры дракона
 */

public class DragonCave implements Valid, Comparable<DragonCave> {

    private long depth;
    private Double numberOfTreasures; //Поле не может быть null, Значение поля должно быть больше 0

    public DragonCave(long depth, Double numberOfTreasures) {
        this.depth = depth;
        this.numberOfTreasures = numberOfTreasures;
    }

    public DragonCave() {
    }

    /**
     * Устанавливает значение глубины depth
     */
    public void setDepth(long depth) {
        this.depth = depth;
    }

    /**
     * Устанавливает количество сокровищ numberOfTreasures
     */
    public void setNumberOfTreasures(Double numberOfTreasures) {
        this.numberOfTreasures = numberOfTreasures;
    }

    public long getDepth() {
        return depth;
    }

    public Double getNumberOfTreasures() {
        return numberOfTreasures;
    }

    @Override
    public int compareTo(final DragonCave o) {
        final int depthCompare = Long.compare(depth, o.depth);
        if (depthCompare == 0) {
            return numberOfTreasures.compareTo(o.numberOfTreasures);
        } else {
            return depthCompare;
        }
    }

    @Override
    public boolean isValid() {
        return numberOfTreasures != null && numberOfTreasures > 0;
    }

    @Override
    public String toString() {
        return "depth=" + depth + ", numberOfTreasures=" + numberOfTreasures + " \n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DragonCave cave = (DragonCave) o;
        return depth == cave.depth && numberOfTreasures.equals(cave.numberOfTreasures);
    }
}