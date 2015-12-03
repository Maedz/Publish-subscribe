// Subscriber For Programming Assignment 2
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import hr.fer.tel.pubsub.artefact.TripletSubscription;
import hr.fer.tel.pubsub.common.Triplet;
import hr.fer.tel.pubsub.entity.Subscriber;
import java.io.*;

public class Subscribe {
	
	public Listener main(String input) throws IOException {
		//Subscriber theSubscriber = new Subscriber("Subscriber1","PNALGORITHM","193.10.227.204",6237);   //Broker number 1
                Subscriber theSubscriber = new Subscriber("Subscriber1","PNALGORITHM","193.10.227.205",6237);   //Broker number 2
        // Don't log subscriptions.
        theSubscriber.setLogWriting(false);
     	// Don't print subscriptions to screen.
     	theSubscriber.setTesting(false);
     	//Connect with MoPS broker.
     	theSubscriber.connect();
     	//Publications are caught by a listener.
     		Listener theListener = new Listener();
         	theSubscriber.setNotificationListener(theListener);
        	//Create a new subscription.
         	TripletSubscription theSubscription = new TripletSubscription();
         	//Start the subscription directly.
         	theSubscription.setStartTime(System.currentTimeMillis());
         	// Time of validity: 30 seconds.
         	theSubscription.setValidity(System.currentTimeMillis() + 30000);
         	theSubscription.setProperty(new Triplet("BusId",input , "="));
         	//Send subscription to MoPS broker.
         	theSubscriber.subscribe(theSubscription);
     	
     	return theListener;
	}

}