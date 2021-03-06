package dsoadExample;

import view.modeling.*;
import GenCol.Pair;
import GenService.*;
import ServiceMod.*;
import java.awt.*;
import java.util.ArrayList;

public class VoiceCommunicationSystem extends ViewableDigraph{

	//attributes 
	double available = 60;     //available time for broker
	double brokerStartTime = 0.1; 
 	double bandwidth = 100;      //bandwidth for the network or router
	double requestduration = 60; // how long a client want the data
	double subscriberStartTime =0.1;
	int subCount=1;
	
 	ServiceBroker broker;
	ServiceRouter router;
	ServiceProvider publisher;
	ArrayList <ServiceClient> subscriberList;
	Executive executive;
	Transducer transducer;
	
	public VoiceCommunicationSystem(){
		super("Voice Communication System");
		createStaticInstances();
		// all models must be added prior to init
		initialize();
	}

	public void createStaticInstances(){
		
		subscriberList = new ArrayList <ServiceClient> ();
		ArrayList <Pair> Endpoints = new ArrayList <Pair> ();
 		Endpoints.add(new Pair("qRate", "Double"));		
		publisher = new VoiceComm("VoiceComm","Voice Communication","Atomic",Endpoints,1);
		broker = new ServiceBroker("Broker",available,brokerStartTime);
		router = new ServiceRouter("Router",bandwidth);
		executive = new Executive();				
		transducer = new Transducer();
		
		add(broker);
		add(router);
		add(publisher);
		add(executive);
		add(transducer);
		// must be after all others
		//add(addSubscriber());
		//connectSubToBroker(subCount);
		//connectSubToRouter(subCount);
		//subCount++;
		setColors();
		createCouplings();
		
	}
	
	public void setColors(){
		
		broker.setBackgroundColor(Color.CYAN);
		executive.setBackgroundColor(Color.CYAN);
		
		publisher.setBackgroundColor(Color.GREEN);
		
		router.setBackgroundColor(Color.YELLOW);
		transducer.setBackgroundColor(Color.PINK);
	}
	public void createCouplings(){
		
		// add the transducer
		addCoupling(router,"qRate",transducer,"in");
		addCoupling(publisher,"qRateOut",transducer,"out");
		// connect publisher with broker
		addCoupling(publisher,"publish",broker,"publish");
		
		//connect publisher to router
		ArrayList <Pair> Endpoints = new ArrayList <Pair> ();
		Endpoints = publisher.getEndpoints();
		String portName = Endpoints.get(0).key.toString();
		router.addOutport(portName);
		addCoupling(router,portName,publisher,portName+"In");
		addCoupling(publisher,portName+"Out",router,"in");
		
	}
	
	
	public ServiceClient addSubscriber(double reqDuration){
		//Construct ServiceLookup information: The list of service to subscribe
		ArrayList <ServiceLookupMessage> lookupList = new ArrayList <ServiceLookupMessage> ();
		lookupList.add(new ServiceLookupMessage("VoiceComm", "qRate", new Pair("Hz", 220500), reqDuration));
		
		//Construct Subscriber with the list of service lookup message
		ServiceClient subscriber = new ServiceClient("Subscriber"+subCount, lookupList, subscriberStartTime);			
		//Add the subscriber into the list
		subscriberList.add(subscriber);
		
		subCount++;
		
		return subscriber;
	}
      
	/*
	public void layoutForSimView()
    {
        preferredSize = new Dimension(577, 312);
        ((ViewableComponent)withName("Router")).setPreferredLocation(new Point(188, 124));
        ((ViewableComponent)withName("VoiceComm")).setPreferredLocation(new Point(14, 46));
        ((ViewableComponent)withName("Executor")).setPreferredLocation(new Point(419, 107));
        ((ViewableComponent)withName("ServiceTransd")).setPreferredLocation(new Point(182, 207));
        ((ViewableComponent)withName("Broker")).setPreferredLocation(new Point(348, 48));
    }*/
    /**
     * Automatically generated by the SimView program.
     * Do not edit this manually, as such changes will get overwritten.
     */
    public void layoutForSimView()
    {
        preferredSize = new Dimension(577, 312);
        ((ViewableComponent)withName("Subscriber4")).setPreferredLocation(new Point(50, 50));
        ((ViewableComponent)withName("Executor")).setPreferredLocation(new Point(454, 195));
        ((ViewableComponent)withName("Broker")).setPreferredLocation(new Point(348, 48));
        ((ViewableComponent)withName("ServiceTransd")).setPreferredLocation(new Point(205, 233));
        ((ViewableComponent)withName("VoiceComm")).setPreferredLocation(new Point(8, 188));
        ((ViewableComponent)withName("Subscriber1")).setPreferredLocation(new Point(-3, 122));
        ((ViewableComponent)withName("Router")).setPreferredLocation(new Point(233, 134));
    }
}
