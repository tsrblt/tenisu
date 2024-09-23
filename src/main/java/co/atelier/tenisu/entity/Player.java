package co.atelier.tenisu.entity;

public class Player {
    private int id;
    private String firstname;
    private String lastname;
    private String shortname;
    private SexEnum sex;
    private String picture;
    private Country country;
    private Data data;

    public Player() {}

    public Player(int id, String firstname, String lastname, String shortname, SexEnum sex, String picture, Country country, Data data) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.shortname = shortname;
        this.sex = sex;
        this.picture = picture;
        this.country = country;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
