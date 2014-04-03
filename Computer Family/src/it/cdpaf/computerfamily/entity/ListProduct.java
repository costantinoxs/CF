package it.cdpaf.computerfamily.entity;


import it.cdpaf.computerfamily.helper.GenericFunctions;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class ListProduct extends ArrayList<Product> {
	int count=0;
	
	
	public ListProduct(JSONArray array){
		super();
		for(int i=0;i<array.length();i++){
			try {
				Product temp;
				JSONObject jTemp = (JSONObject) array.get(i);
				temp= new Product(
						jTemp.getString("id"),
						jTemp.getString("codice"),
						jTemp.getString("immagine"),
						jTemp.getDouble("prezzo"),
						jTemp.getInt("quantita"),
						jTemp.getString("produttore"),
						jTemp.getString("nome"),
						jTemp.getString("descrizione"),
						jTemp.getString("icecat"),
						jTemp.getString("id_categoria"),
						jTemp.getString("id_sottocategoria"),
						jTemp.getString("nome_categoria"),
						jTemp.getString("nome_sotto_categoria"));
				this.add(temp);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		this.print("Stampa Prodotti");
		
	}	

	public boolean add(Product prod){
		super.add(prod);
		for (int i=0;i<prod.getQuantita();i++){
			count++;
		}
		return true;
		
	}
	
	public Product remove(int position){
		int q=super.get(position).getQuantita();
		for (int i=0;i<q;i++){
			count--;
		}
		super.remove(position);
		return null;
	}

	public void print(String g) {
		// TODO Auto-generated method stub
		Log.i(g, " ");
		for(int i=0;i<this.size();i++){
			Log.i(g, "PRODOTTO NUMERO "+i+" : "	+this.get(i).getNome());
		}
	}
	
}
