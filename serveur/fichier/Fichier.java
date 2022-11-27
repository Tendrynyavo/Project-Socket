package serveur.fichier;

import java.sql.*;

public class Fichier {
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

    public Fichier(String nom) throws Exception {
        setIdFile(createPrimaryKey());
        setNom(nom);
    }

    public Fichier(String idFile, String nom) {
        setIdFile(idFile);
        setNom(nom);
    }

    public String createPrimaryKey() throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/file?user=postgres&password=postgres");
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT nextval('seqFile')");
        result.next();
        return "FILE" + result.getString(1);
    }

    public void save() throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/file?user=postgres&password=postgres");
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO file (idFile, nom) VALUES ('" + this.getIdFile() + "', '" + this.getNom() + "')");
    }
}
