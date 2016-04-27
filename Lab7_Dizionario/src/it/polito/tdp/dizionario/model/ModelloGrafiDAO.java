package it.polito.tdp.dizionario.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModelloGrafiDAO {
	
	public List<String> parole(int lunghezza){
			List<String> parole= new ArrayList<String>();
			String parola="";
			for(int i=0;i<lunghezza;i++)
				parola+="_";
			// si connette a Db e prende le parole
			Connessione c= new Connessione("jdbc:mysql://localhost/dizionario?user=root");
			Connection conn= c.connetti();
			String sql="";
			try {
					sql="SELECT nome FROM parola WHERE nome LIKE ?";
					PreparedStatement s= conn.prepareStatement(sql);
					s.setString(0, parola);
					ResultSet rs= s.executeQuery();
					while(rs.next()){
						parole.add(rs.getString("nome"));
					}
					
				conn.close();
				rs.close();
				return parole;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return null;
	}
	public boolean esiste(String parola){
		// si connette a Db e verifica se parola esista gia o meno
		Connessione c= new Connessione("jdbc:mysql://localhost/dizionario?user=root");
		Connection conn= c.connetti();
		String sql="";
		boolean esiste;
		try {
				sql="SELECT nome FROM parola WHERE nome = ?";
				PreparedStatement s= conn.prepareStatement(sql);
				s.setString(1, parola);
				ResultSet rs= s.executeQuery();
				if(rs.next())
					esiste=true;
				else
					esiste=false;
			conn.close();
			rs.close();
			return esiste;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
}
