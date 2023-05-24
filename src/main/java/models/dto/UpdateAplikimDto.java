package models.dto;

public class UpdateAplikimDto {

    private int id;
    private int student_id;
    private int bursa_id;
    private int viti_studimit;
    private double nota_mesatare;


    public UpdateAplikimDto(int id,int student_id,int bursa_id,int viti_studimit,double nota_mesatare){
        this.id=id;
        this.student_id=student_id;
        this.bursa_id=bursa_id;
        this.viti_studimit=viti_studimit;
        this.nota_mesatare=nota_mesatare;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudent_id(){return student_id;}
    public void setStudent_id(int student_id){this.student_id=student_id;}

    public int getBursa_id(){return bursa_id;}
    public void setBursa_id(int bursa_id){this.bursa_id=bursa_id;}

    public int getViti_studimit(){return viti_studimit;}
    public void setViti_studimit(int vitiStudimit){this.viti_studimit=viti_studimit;}

    public double getNota_mesatare(){return nota_mesatare;}
    public void setNota_mesatare(double nota_mesatare){this.nota_mesatare=nota_mesatare;}
}
