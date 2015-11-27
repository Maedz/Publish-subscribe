import java.util.UUID;
import hr.fer.tel.pubsub.artefact.Publication;
import hr.fer.tel.pubsub.common.ReadingWritingXML;
import hr.fer.tel.pubsub.entity.NotificationListener;

public class Listener extends NotificationListener {
	public Listener() {
		float Longitud;
		float Latitude;
	}
	@Override
	public void notify( UUID subscriberId,String subscriberName,Publication notification) {
		// Handle notification from MoPS broker.
		System.out.println("\nsubscriberName " + subscriberName);
		System.out.println(notification.hashCode());
		System.out.println(notification.getClass().toString());
		System.out.println(notification.toString());
		System.out.println(subscriberId);
		System.out.println(notification.getId().toString());
		// I någon av dessa så ska longitud oh det finnas tror jag för detta får vi från broker
	}
}