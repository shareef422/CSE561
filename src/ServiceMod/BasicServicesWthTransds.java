	package ServiceMod;
	
	import view.simView.*;
	import model.modeling.*;
	import model.simulation.*;
	import view.modeling.*;
	import java.awt.*;
	import java.io.*;

	import GenCol.*;
	import GenService.*;
	
import java.util.*;
	
	public class BasicServicesWthTransds extends ApplicationComposition{
		
		public final static double observation = 70;
		
		public BasicServicesWthTransds(){
			super("Basic Service");
		}
		
		public void PublisherConstruct(){
			
			ArrayList <Pair> Endpoints = new ArrayList <Pair> ();
	 		
			Endpoints.add(new Pair("doNothing", "Object"));		
			
			BasicService Service1 = new BasicService("BasicSV", "This service does nothing", "Atomic", Endpoints, 1);
			
			Service1.setBackgroundColor(Color.CYAN);
			
			//Construct the publisher list
			PublisherList.add(Service1);
				
		}
		
		public void SubscriberConstruct(){
			//Construct ServiceLookup information: The list of service to subscribe
			ArrayList <ServiceLookupMessage> lookupList = new ArrayList <ServiceLookupMessage> ();
			lookupList.add(new ServiceLookupMessage("Sub1", "doNothing", new Pair("", "packetSize"), 1));
			ServiceClient Subscriber1 = new ServiceClient("Sub1", lookupList, 1);
			
			lookupList = new ArrayList <ServiceLookupMessage> ();
			lookupList.add(new ServiceLookupMessage("Sub2", "doNothing", new Pair("", "packetSize"), 1));
			ServiceClient Subscriber2 = new ServiceClient("Sub2", lookupList, 1);
			
			lookupList = new ArrayList <ServiceLookupMessage> ();
			lookupList.add(new ServiceLookupMessage("Sub3", "doNothing", new Pair("", "packetSize"), 1));
			ServiceClient Subscriber3 = new ServiceClient("Sub3", lookupList, 1);
			
			Subscriber1.setBackgroundColor(Color.GREEN);
			Subscriber2.setBackgroundColor(Color.GREEN);
			Subscriber3.setBackgroundColor(Color.GREEN);
			
			//Construct the subscriber list
			SubscriberList.add(Subscriber1);
			SubscriberList.add(Subscriber2);
			SubscriberList.add(Subscriber3);
		}  
		
		public void TransducerConstruct(){
			BrokerTransd BroTrans  =  new BrokerTransd("BrokerTransd", observation);
			RouterTransd NecTrans  =  new RouterTransd("RouterTransd", observation);
			SubscriberTransd SubTrans1 =  new SubscriberTransd("Sub1Transd", observation);
			SubscriberTransd SubTrans2 =  new SubscriberTransd("Sub2Transd", observation);
			SubscriberTransd SubTrans3 =  new SubscriberTransd("Sub3Transd", observation);
			PublisherTransd PubTrans1 =  new PublisherTransd("BasicSVTransd", observation);
			
			//Always the same order: Broker >> Network >> Subscriber >> Publisher
			TransducerList.add(BroTrans);
			TransducerList.add(NecTrans);
			TransducerList.add(SubTrans1);
			TransducerList.add(SubTrans2);
			TransducerList.add(SubTrans3);
			TransducerList.add(PubTrans1);
		}   
   
   
    /**
     * Automatically generated by the SimView program.
     * Do not edit this manually, as such changes will get overwritten.
     */
    public void layoutForSimView()
    {
        preferredSize = new Dimension(798, 437);
        ((ViewableComponent)withName("Sub1Transd")).setPreferredLocation(new Point(9, 53));
        ((ViewableComponent)withName("Sub1")).setPreferredLocation(new Point(-28, 109));
        ((ViewableComponent)withName("Router Link")).setPreferredLocation(new Point(296, 265));
        ((ViewableComponent)withName("Sub2")).setPreferredLocation(new Point(-26, 230));
        ((ViewableComponent)withName("RouterTransd")).setPreferredLocation(new Point(288, 206));
        ((ViewableComponent)withName("BrokerTransd")).setPreferredLocation(new Point(287, 53));
        ((ViewableComponent)withName("BasicSVTransd")).setPreferredLocation(new Point(551, 147));
        ((ViewableComponent)withName("Sub3")).setPreferredLocation(new Point(-27, 354));
        ((ViewableComponent)withName("Sub2Transd")).setPreferredLocation(new Point(7, 176));
        ((ViewableComponent)withName("Sub3Transd")).setPreferredLocation(new Point(9, 297));
        ((ViewableComponent)withName("BasicSV")).setPreferredLocation(new Point(509, 201));
        ((ViewableComponent)withName("Broker")).setPreferredLocation(new Point(256, 110));
    }
	}