package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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

import donnees.Review;

@WebServlet("/api/reviews/*")
public class ReviewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    private MongoDatabase getDatabase(MongoClient mongoClient) {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        return mongoClient.getDatabase("22204198").withCodecRegistry(pojoCodecRegistry);
    }

    private MongoClient getMongoClient() {
        ConnectionString connectionString = new ConnectionString("mongodb://obiwan.univ-brest.fr:27017");
        return MongoClients.create(connectionString);
    }

    @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String pathInfo = request.getPathInfo();

    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    PrintWriter out = response.getWriter();

    MongoClient mongoClient = getMongoClient();
    MongoDatabase database = getDatabase(mongoClient);
    MongoCollection<Review> reviewsCollection = database.getCollection("reviews", Review.class);

    if (pathInfo == null || pathInfo.equals("/")) {
        List<Review> reviews = new ArrayList<>();
        reviewsCollection.find().into(reviews);
        mongoClient.close();
        out.print(mapper.writeValueAsString(reviews));
    } else {
        String id = pathInfo.substring(1);
        org.bson.conversions.Bson filtre = com.mongodb.client.model.Filters.eq("_id", id);
        Review review = reviewsCollection.find(filtre).first();
        mongoClient.close();

        if (review != null) {
            out.print(mapper.writeValueAsString(review));
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        BufferedReader reader = request.getReader();

        try {
            Review nouvelleReview = mapper.readValue(reader, Review.class);

            if (nouvelleReview != null) {
                MongoClient mongoClient = getMongoClient();
                MongoDatabase database = getDatabase(mongoClient);

                MongoCollection<Review> reviewsCollection = database.getCollection("reviews", Review.class);
                reviewsCollection.insertOne(nouvelleReview);

                mongoClient.close();

                response.setStatus(HttpServletResponse.SC_CREATED);
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = extraireId(request);
        if (id == null || id.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        BufferedReader reader = request.getReader();

        try {
            Review reviewMiseAJour = mapper.readValue(reader, Review.class);

            if (reviewMiseAJour != null) {
                reviewMiseAJour.setId(id);

                MongoClient mongoClient = getMongoClient();
                MongoDatabase database = getDatabase(mongoClient);

                MongoCollection<Review> reviewsCollection = database.getCollection("reviews", Review.class);
                org.bson.conversions.Bson filtre = com.mongodb.client.model.Filters.eq("_id", id);
                com.mongodb.client.result.UpdateResult resultat = reviewsCollection.replaceOne(filtre, reviewMiseAJour);

                mongoClient.close();

                if (resultat.getMatchedCount() > 0) {
                    response.setStatus(HttpServletResponse.SC_OK);
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = extraireId(request);
        if (id == null || id.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        MongoClient mongoClient = getMongoClient();
        MongoDatabase database = getDatabase(mongoClient);

        MongoCollection<Review> reviewsCollection = database.getCollection("reviews", Review.class);
        org.bson.conversions.Bson filtre = com.mongodb.client.model.Filters.eq("_id", id);
        com.mongodb.client.result.DeleteResult resultat = reviewsCollection.deleteOne(filtre);

        mongoClient.close();

        if (resultat.getDeletedCount() > 0) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private String extraireId(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            return null;
        }
        return pathInfo.substring(1);
    }
}