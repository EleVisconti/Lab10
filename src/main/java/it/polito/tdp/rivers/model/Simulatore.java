package it.polito.tdp.rivers.model;

import java.util.ArrayList;

import java.util.List;

import java.util.PriorityQueue;




public class Simulatore {
	// Coda degli eventi
		private PriorityQueue<Flow> queue;

		// Parametri di simulazione
		private double cap;
		private float fOutMin;
		private double Q;
		//inserire anche fin

		// Output della simulazione
		private int nGiorniNo=0; // 
		
		// Stato del mondo simulato
        private double k;
        private River r;
       

		public Simulatore() {
			super();
		}

		public String simula(River r, double k) {
			
			this.cap=(k*converter(r.getFlowAvg())*15);
			this.r = r;
			this.fOutMin=(float) (0.8*converter(r.getFlowAvg()));
			this.Q = (k*converter(r.getFlowAvg())*30);
			this.k=k;
			
			this.queue = new PriorityQueue<Flow>();	
			for(Flow f1:r.getFlows())
			this.queue.add(f1);
			
			List<Double> capacities = new ArrayList<Double>();
			Flow f;
			while((f = this.queue.poll()) != null) {
				System.out.println("Date: " + f.getDay());
				double fOut = fOutMin;
				// Ogni giorno la prob è del 0.05 che fOut=10*fOutMin
				if (Math.random() < 0.05) {
					fOut = 10 * fOutMin;
					System.out.println("flusso per irrigare i campi");
				}
				 
				//all'inizio di ogni giorno la capacità è uguale al flusso che entra
				cap+=converter(f.getFlow());
				//gestisco le diverse casistiche:
				  if(cap<fOut) {
					  nGiorniNo++;
					  cap=0;
					  }
				  if(cap>fOut&&cap<Q)
					  cap-=fOut;
				  if(cap>Q)
					  cap = Q;
				  //fOut+=cap-Q
					  }
			System.out.println(cap + "'\n");

			// Mantengo un lista della capacità giornaliere del bacino
			capacities.add((double) cap);
			double mediaCap = 0;
			for (Double cap : capacities) {
				mediaCap += cap;
			}
			mediaCap = mediaCap / capacities.size();
			return "La media è: "+mediaCap+"\nIl numero di giorni in cui il servizio non è stato erogato è: "+nGiorniNo; 
		}
		
		
		public double converter(double d) {
			return d / 60 / 60 / 24;
		}

}
