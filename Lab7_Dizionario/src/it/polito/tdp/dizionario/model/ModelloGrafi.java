package it.polito.tdp.dizionario.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class ModelloGrafi {
	private UndirectedGraph<String,DefaultEdge> grafo;
	
	public void generaGrafo(){
		ModelloGrafiDAO m= new ModelloGrafiDAO();
		List<String> parole= m.parole();
		grafo= new SimpleGraph<String,DefaultEdge>(DefaultEdge.class);
		Graphs.addAllVertices(this.grafo, parole);
		for(String parola: parole){
			for(int i=0;i<parole.size();i++){
				if(parola.length()==parole.get(i).length()){
					if(confronto(parola,parole.get(i))==1)
						grafo.addEdge(parola, parole.get(i));
				}
					
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
		return null;
	}
	
	private int confronto(String parola, String parola2) {
		int confronto=0;
		for(int i=0,j=0;i<parola.length();i++,j++){
			if(parola.charAt(i)==parola2.charAt(j))
				confronto++;
		}
		return confronto;
	}
	
}
