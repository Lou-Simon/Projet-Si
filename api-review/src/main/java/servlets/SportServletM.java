package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import mongoPojo.Sportif;

@WebServlet("/listeSportifs")
public class SportServletM extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Connexion à MongoDB
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), 
                                                          fromProviders(pojoCodecProvider));
        
//        ConnectionString connectionString = new ConnectionString("mongodb://obiwan.univ-brest.fr:27017");
		ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");	

        MongoClient mongoClient = MongoClients.create(connectionString);
        MongoDatabase database = mongoClient.getDatabase("22204198").withCodecRegistry(pojoCodecRegistry);
        
        // Récupération des sportifs
        MongoCollection<Sportif> sportifsCollection = database.getCollection("sportifs", Sportif.class);
        ArrayList<Sportif> sportifs = new ArrayList<>();
        
        for(Sportif sportif : sportifsCollection.find()) {
            sportifs.add(sportif);
        }
        
        mongoClient.close();
        
        // Passage des données à la JSP
        request.setAttribute("sportifs", sportifs);
        request.getRequestDispatcher("/WEB-INF/listeSportifs.jsp").forward(request, response);
    }
}