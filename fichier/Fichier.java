package fichier;

import connection.BddObject;

public class Fichier extends BddObject {
    
    String idFile;
    String nom;

    public void setIdFile(String idFile) {
        this.idFile = idFile;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getIdFile() {
        return idFile;
    }

    public String getNom() {
        return nom;
    }

    public Fichier() {
        this.setCountPK(8);
        this.setFunctionPK("nextval('seqFile')");
        this.setPrefix("FILE");
        this.setTable("file");
    }

    public Fichier(String nom) throws Exception {
        this();
        setIdFile(buildPrimaryKey(getPostgreSQL()));
        setNom(nom);
    }

    public Fichier(String idFile, String nom) {
        setIdFile(idFile);
        setNom(nom);
    }
}