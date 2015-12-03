import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import hr.fer.tel.pubsub.artefact.Publication;
import hr.fer.tel.pubsub.artefact.HashtablePublication;
import hr.fer.tel.pubsub.common.ReadingWritingXML;
import hr.fer.tel.pubsub.entity.NotificationListener;


public class Listener extends NotificationListener {
	public Listener() {	}
	public String 	BusID;
	public float 	Longitude = 9999;
	public float	Latitude = 9999;
	HashtablePublication retrievedPublication = new HashtablePublication();
	@Override
	public void notify( UUID subscriberId,String subscriberName,Publication notification) {
	// Handle notification from MoPS broker.
	ReadingWritingXML test = new ReadingWritingXML("test", "test");
	String teststring = test.writeXML(notification);
	String[] testarray;
	testarray = teststring.split("type=\"string\">");
	this.BusID 		= testarray[3].split("</attribute>")[0];
	this.Latitude 	= Float.parseFloat(testarray[1].split("</attribute>")[0]);
	this.Longitude 	= Float.parseFloat(testarray[2].split("</attribute>")[0]);
	}
}