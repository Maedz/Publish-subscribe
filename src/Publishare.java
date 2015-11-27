// Publisher For Programming Assignment 2
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import hr.fer.tel.pubsub.artefact.HashtablePublication;
import hr.fer.tel.pubsub.entity.Publisher;
import google.transit.realtime.GtfsRealtime;
import java.io.*;



public class Publishare {

	//////////////////////////////////////////////////////////////////////////////////////
	////   		CLASS THAT CONNECTS, UPLOAD AND DISCONNECTS FROM THE BROOKER   	  ////
	//////////////////////////////////////////////////////////////////////////////////////

	private static void publish(VehiclePos send)
	{	
		System.out.println("\nPublishing ...\n");
		Publisher thePublisher = new Publisher("Publisher1","193.10.227.204",6237);
		// Don't log publications.
		thePublisher.setLogWriting(false);
		// Don't print publications to screen.
		thePublisher.setTesting(false);
		thePublisher.connect();
		// To	publish	the	position	of	bus	#10,	Publisher1	issues	the	following	commands:
		// New publication.
		HashtablePublication thePublication = new HashtablePublication();
		// Publication time.
		thePublication.setStartTime(System.currentTimeMillis());
		// Time of validity: 30 seconds.
		thePublication.setValidity(System.currentTimeMillis() + 30000);
		// Set buss ID and its coordinates as name-value pairs.
		thePublication.setProperty( "BusId", "#10" );
		thePublication.setProperty( "Latitude", "42.35" );
		thePublication.setProperty( "Longitude", "-71.12" );
		// Send publication to MoPS broker.
		thePublisher.publish(thePublication);
		// Disconnect from broker
		thePublisher.disconnectFromBroker();
	}
	
	//////////////////////////////////////////////////////////////////////////////////////
	////  	CLASS THAT FETCHES THE INFORMATION FROM THE GIVEN IP IN ASSIGNMENT        ////
	//////////////////////////////////////////////////////////////////////////////////////
	
	private static List<VehiclePos> fetch()
	{
		URL theVehUrl;
		List<VehiclePos> Information = new ArrayList<VehiclePos>();
		try 
		{
			theVehUrl = new URL( "http://developer.mbta.com/lib/gtrtfs/Vehicles.pb" );
			GtfsRealtime.FeedMessage theFeed = GtfsRealtime.FeedMessage.parseFrom( ( InputStream )theVehUrl.openStream() );
			for (GtfsRealtime.FeedEntity anEntity : theFeed.getEntityList()) 
			{
				if (!anEntity.hasVehicle()) 
				{
					continue;
				}
				GtfsRealtime.VehiclePosition aVehicle = anEntity.getVehicle();
				if (!aVehicle.hasPosition())
				{
					continue;
				}
				GtfsRealtime.Position aPosition = aVehicle.getPosition();
				VehiclePos temp = new VehiclePos();
				temp.Latitude = aPosition.getLatitude();
				temp.Longitude = aPosition.getLongitude();
				Information.add(temp);
			}
			} catch ( MalformedURLException ex ) {
				return null;
			} 
			catch ( IOException ex ) {
				return null;
			}
		return Information;
	}	
	
	//////////////////////////////////////////////////////////////////////////////////////
	//// 								Main Function				  				  ////
	//////////////////////////////////////////////////////////////////////////////////////
	
	public static void main(String args[]) throws IOException 
	{
		try
		{
			while(true)
			{
				List<VehiclePos> info = new ArrayList<VehiclePos>();
				if((info = fetch()) == null)
				{
					System.out.println("Error Could Not Fetch Bus Information");
					System.exit(0);
				}
				publish(info.get(10));
				Thread.sleep(30000);
			}
		}
		catch(Exception e)
		{
			System.out.println("Some Error Occured Exiting...");
			System.exit(0);
		}
	}
}