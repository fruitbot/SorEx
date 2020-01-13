import src.Blockchain;
import src.Student;


import java.time.LocalDate;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Blockchain b = new Blockchain();

        // creation nouveau etudiant
        String nom = "John";
        String prenom = "Doe";
        LocalDate l = LocalDate.now();
        String id = "src/documentsStudent/ID.png";
        String japd = "src/documentsStudent/JAPD.png";
        String bac = "src/documentsStudent/BAC.png";
        String[] diplomes = new String[] {"L1", "Geol", "UPMC", "2019", "12.7"};

        b.addBlock(new Student(nom, prenom, l, id, japd, bac, diplomes));

        // ajout d'un nouveau diplôme de l'étudiant
        b.getBlock().get(1).getStudent().addDiplomes(new String[] {"L2", "Geol", "UPMC", "2020", "14.7"});
        b.getBlock().get(1).getStudent().addDiplomes(new String[] {"L3", "Geol", "UPMC", "2020", "14.7"});
        Blockchain copy = b.fork();
        b.addBlock(new Student(nom, prenom, l, id, japd, bac, diplomes));
        copy.addBlock(b.getLastBlock());
        // pour partager l'information, il faut que l'université partage l'infos du Student aux autres pour qu'elles puissent créer le bloc.


        // TODO: rajouter possibilité préfecture de mettre à jour carte d'identité + nom et prénom
        // TODO: ajouter possibilité chiffrer addresse et clef privée --> récupérer adresse étudiant

        // protection de la ressource critique
        final Semaphore mutex = new Semaphore(1);
        try {
            mutex.acquire();
            b.writeBlockchain();
            mutex.release();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }

}