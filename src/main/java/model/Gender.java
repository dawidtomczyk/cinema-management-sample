package model;

/**
 * Created by Dawid on 2015-07-16.
 */
public enum Gender {

    MALE("Male"),FEMALE("Female");

    private String gender;

    private Gender(String gender){

        this.gender= gender;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return gender;
    }
}
