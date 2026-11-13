
package rmiServer;

import java.rmi.registry.LocateRegistry;
import javax.naming.Context;
import javax.naming.InitialContext;

import rmiService.BanqueImpl;

import java.util.Hashtable;

public class BanqueServer {

    public static void main(String[] args) {

        try {
            // Start the RMI registry
            LocateRegistry.createRegistry(1099);

            // Create the remote banking service
            BanqueImpl banque = new BanqueImpl();

            // Configure JNDI environment
            Hashtable<String, String> env = new Hashtable<>();

            env.put(Context.INITIAL_CONTEXT_FACTORY,
                    "com.sun.jndi.rmi.registry.RegistryContextFactory");

            env.put(Context.PROVIDER_URL, "rmi://localhost:1099");

            // Create JNDI context
            Context context = new InitialContext(env);

            // Register the remote object using JNDI
            context.rebind("BANQUE", banque);

            System.out.println("Serveur Banque démarré avec JNDI !");
            System.out.println("Service BANQUE enregistré avec succès !");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
