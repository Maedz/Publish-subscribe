import java.util.UUID;
import hr.fer.tel.pubsub.artefact.Publication;
import hr.fer.tel.pubsub.common.ReadingWritingXML;
import hr.fer.tel.pubsub.entity.NotificationListener;

public class Listener extends NotificationListener {
	public Listener() {
		
	}
	@Override
	public void notify( UUID subscriberId,String subscriberName,Publication notification) {
		// Handle notification from MoPS broker.
	}
}