package rmiClient;

import javax.naming.Context;
import javax.naming.InitialContext;

import java.util.Hashtable;

import metier.Compte;
import rmiService.IBanque;

public class BanqueClient {

    public static void main(String[] args) {

        try {
            // Configure JNDI environment
            Hashtable<String, String> env = new Hashtable<>();

            env.put(Context.INITIAL_CONTEXT_FACTORY,
                    "com.sun.jndi.rmi.registry.RegistryContextFactory");

            env.put(Context.PROVIDER_URL, "rmi://localhost:1099");

            // Create JNDI context
            Context context = new InitialContext(env);

            // Search for the remote banking service
            IBanque banque = (IBanque) context.lookup("BANQUE");

            System.out.println("Connexion au serveur réussie avec JNDI !");

            // Create a bank account
            Compte c1 = new Compte(1002, 7500);

            String resultat = banque.creerCompte(c1);
            System.out.println(resultat);

            // Retrieve account information
            String info = banque.getInfoCompte(1002);

            System.out.println("Informations du compte :");
            System.out.println(info);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
