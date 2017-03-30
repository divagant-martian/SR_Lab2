package edu.uniandes.comit.recommenders.testHybrid;

import java.util.List;

import org.recommender101.data.DataModel;
import org.recommender101.data.Rating;
import org.recommender101.eval.impl.Experiment;
import org.recommender101.eval.interfaces.PredictionEvaluator;
import org.recommender101.recommender.baseline.NearestNeighbors;
import org.recommender101.recommender.extensions.contentbased.ContentBasedRecommender;

public class Tester {
	
	public static void main(String[] args) throws Exception {
		

		
		DataModel model = new DataModel();
		LastFMDataLoader loader= new LastFMDataLoader();
		
		loader.setFilename("data/user_artists.dat");
		loader.loadData(model);
		
		ContentBasedUtilities.createFeatureWeightFile("data/user_taggedartists.dat", "data/tag_weight.txt");
		ContentBasedRecommender recommender= new ContentBasedRecommender();
		ContentBasedRecommender.dataDirectory="data";
		recommender.setDataModel(model);
		recommender.setWordListFile("artists.dat");
		//La implementación crea unos vectores de similitud, que guarda en el archivo cos-sim-vectors.txt, ya fueron calculados
		recommender.setFeatureWeightFile("tag_weight.txt");
		
		recommender.init();
		
		List<Integer> lista = recommender.recommendItems(2);
		int i=1;
		for(Integer a :lista){
			System.out.println("recomendación "+i+": "+a+":"+
					recommender.predictRating(2, a));
			i++;
		}
		
	}

}
