package model;

/**
 * Created by Dawid on 2015-07-16.
 */
public enum AgeCategory {
    CHILD("Child"), ADULT("Adult"), SENIOR("Senior");

    private String category;

    private AgeCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return category;
    }
}
