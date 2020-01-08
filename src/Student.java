package src;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;

import java.time.LocalDate;
import java.util.ArrayList;

public class Student {
    /* possède les attributs suivants :
    - statut : OK, BANNED
    - nom
    - prenom
    - date naissance
    - hash carte d'identité
    - hash JAPD
    - arrayList de diplômes : [cursus diplôme,
                               nom diplôme,
                               date,
                               université,
                               moyenne]
     */

    private String nom;
    private String prenom;
    private LocalDate dateNaissance;  // https://www.baeldung.com/java-8-date-time-intro
    private String hashID;
    private String hashJAPD;
    private String hashBAC;     // hash tu baccalauréat
    private String[] diplomesDetail = new String[5];
    private ArrayList<String> diplomes;


    // test que ça marche bien avec des valeurs exemples
    public Student() {
        this.nom = "Dupont";
        this.prenom = "Albert";
        this.dateNaissance = LocalDate.of(1994, 2, 20);
        this.hashID = exempleHash("src/documentsStudent/ID.png"); // genere le SHA de l'ID
        this.hashJAPD = exempleHash("src/documentsStudent/JAPD.png"); // genere le SHA de la JAPD
        this.hashBAC = exempleHash("src/documentsStudent/JAPD.png"); // genere le SHA de la JAPD

    }

    private String exempleHash(String file) {
        // image :
        File input = new File(file);
        try {
            // try to load the ID png image
            BufferedImage buffImg = ImageIO.read(input);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(buffImg, "png", outputStream);
            byte[] IDdata = outputStream.toByteArray();

            // try to convert hash the IDdata from image
            return hashingFunction(IDdata);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return file;
    }

    private String hashingFunction(byte[] IDdata) {
        try {
            // getInstance() method is called with algorithm SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            // digest() method is called
            // to calculate message digest of the input png
            byte[] messageDigest = md.digest(IDdata);

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            StringBuilder hashtext = new StringBuilder(no.toString(16));

            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32)
                hashtext.insert(0, "0");

            return hashtext.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getHashID() {
        return hashID;
    }

    public void setHashID(String hashID) {
        this.hashID = hashID;
    }

    public String getHashJAPD() {
        return hashJAPD;
    }

    public void setHashJAPD(String hashJAPD) {
        this.hashJAPD = hashJAPD;
    }

    public String getHashBAC() {
        return hashBAC;
    }

    public void setHashBAC(String hashBAC) {
        this.hashBAC = hashBAC;
    }

    public String[] getDiplomesDetail() {
        return diplomesDetail;
    }

    public void setDiplomesDetail(String[] diplomesDetail) {
        this.diplomesDetail = diplomesDetail;
    }

    public ArrayList<String> getDiplomes() {
        return diplomes;
    }

    public void setDiplomes(ArrayList<String> diplomes) {
        this.diplomes = diplomes;
    }
}
