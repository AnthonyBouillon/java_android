package com.example.myapplication3;

/**
 * Business class (Classe métier)
 */
public class Users {

    private Integer id;
    private String mail;
    private String password;

    public Users(){

    }
    public Users(String mail, String password){
        this.mail = mail;
        this.password = password;
    }

    public Integer getId(){
        return this.id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // OBLIGATOIRE POUR LAFFICHAGE DANS UNE LISTEVIEW
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( mail  + " "  + password +  " " + id);
        return sb.toString();
    }
}
