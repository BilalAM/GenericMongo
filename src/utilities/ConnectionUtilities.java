package utilities;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ConnectionUtilities {
	private static final MongoClient myClient = new MongoClient("localhost:27017");
	private static final MongoDatabase database = myClient.getDatabase("MonitorSet");
	private static final MongoCollection<Document> collection = database.getCollection("testCollection");

	public static MongoCollection<Document> getConnectionToColletion() {
		return collection;
	}
}
