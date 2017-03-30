package edu.uniandes.comit.recommenders.testHybrid;

import java.util.List;

import org.recommender101.data.DataModel;
import org.recommender101.recommender.baseline.NearestNeighbors;
import org.recommender101.recommender.extensions.contentbased.ContentBasedRecommender;

public class Tester2 {
	
	public static void main(String[] args) throws Exception {
		

		
		DataModel model = new DataModel();
		LastFMDataLoader loader= new LastFMDataLoader();
		
		loader.setFilename("data/user_artists.dat");
		loader.loadData(model);
		
		NearestNeighbors recommender = new NearestNeighbors();
		recommender.setDataModel(model);
		recommender.setItemBased("true");
		recommender.init();
		
		
		List<Integer> lista = recommender.recommendItems(2);
		int i=1;
		for(Integer a :lista){
			System.out.println("recomendación "+i+": "+a);
			i++;
		}
		
	}

}
