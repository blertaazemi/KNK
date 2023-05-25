package models;

public class Bursat {
    private int id;
    private String name;
    private double nota_mesatare;
    private String description;

    private double amount;


    public Bursat(int id, String name, double nota_mesatare, String description, double amount) {
        this.id = id;
        this.name = name;
        this.nota_mesatare = nota_mesatare;
        this.description = description;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public Double getnota_mesatare() {
        return nota_mesatare;
    }

    public void setnota_mesatare(Double nota_mesatare) {
        this.nota_mesatare = nota_mesatare;
    }

    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    public Double getamount() {
        return amount;
    }

    public void setamount(Double amount) {
        this.amount = amount;
    }


}