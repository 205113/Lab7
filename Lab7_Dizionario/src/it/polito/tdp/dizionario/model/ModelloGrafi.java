package it.polito.tdp.dizionario.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

public class ModelloGrafi {
	private UndirectedGraph<String,DefaultEdge> grafo;
	
	public void generaGrafo(int lunghezza){
		ModelloGrafiDAO m= new ModelloGrafiDAO();
		List<String> parole= m.parole(lunghezza);
		grafo= new SimpleGraph<String,DefaultEdge>(DefaultEdge.class);
		Graphs.addAllVertices(this.grafo, parole);
		for(String parola: parole){
			for(int i=0;i<parole.size();i++){
					if(confronto(parola,parole.get(i))==1)
						grafo.addEdge(parola, parole.get(i));
					
			}
		}
	}
	public List<String> trovaVicini(String parola){
		ModelloGrafiDAO m=new ModelloGrafiDAO();
		List<String> vicini= new ArrayList<String>();
		if(m.esiste(parola))
			 vicini =Graphs.neighborListOf(this.grafo, parola);
		else
			vicini.add("La parola non esiste");
		return vicini;
	}
	
	public List<String> trovaConnessi(String parola){
		// esplorazione ricorsiva del grafo
		List<String> connessi= new ArrayList<String>();
		ModelloGrafiDAO m= new ModelloGrafiDAO();
		if(!m.esiste(parola))
			connessi.add("Non esiste la parola");
		else{
			GraphIterator<String,DefaultEdge>iteratore= new DepthFirstIterator<String,DefaultEdge>(this.grafo,parola);
			while(iteratore.hasNext()){
				connessi.add(iteratore.next());
			}
		}
		
		return connessi;
	}
	
	private int confronto(String parola, String parola2) {
		int confronto=0;
		for(int i=0,j=0;i<parola.length();i++,j++){
			if(parola.charAt(i)!=parola2.charAt(j))
				confronto++;
		}
		return confronto;
	}
	
}
