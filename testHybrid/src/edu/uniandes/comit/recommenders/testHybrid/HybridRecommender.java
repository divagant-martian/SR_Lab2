package edu.uniandes.comit.recommenders.testHybrid;

import java.util.LinkedList;
import java.util.List;

import org.recommender101.data.DataModel;
import org.recommender101.recommender.AbstractRecommender;
import org.recommender101.recommender.baseline.NearestNeighbors;
import org.recommender101.recommender.extensions.contentbased.ContentBasedRecommender;

public class HybridRecommender extends AbstractRecommender {

	
	private DataModel model;
	private ContentBasedRecommender contentBasedRecommender;
	private NearestNeighbors itemBasedRecommender;

	@Override
	public float predictRating(int user, int item) {
		return 0;
	}

	@Override
	public List<Integer> recommendItems(int user) {
		return new LinkedList<>();
	}

	@Override
	public void init() throws Exception {
		
		this.model = new DataModel();
		LastFMDataLoader loader= new LastFMDataLoader();
		loader.setFilename("data/user_artists.dat");
		loader.loadData(model);
		
		//Ya fue pre-procesado
		//ContentBasedUtilities.createFeatureWeightFile("data/user_taggedartists.dat", "data/tag_weight.txt");
		this.contentBasedRecommender= new ContentBasedRecommender();
		ContentBasedRecommender.dataDirectory="data";
		contentBasedRecommender.setDataModel(model);
		contentBasedRecommender.setWordListFile("artists.dat");
		//La implementación crea unos vectores de similitud, que guarda en el archivo cos-sim-vectors.txt, ya fueron calculados
		contentBasedRecommender.setFeatureWeightFile("tag_weight.txt");
		contentBasedRecommender.init();
	
		
		this.itemBasedRecommender= new NearestNeighbors();
		itemBasedRecommender.setItemBased("true");
		itemBasedRecommender.setDataModel(model);
		itemBasedRecommender.init();
	
		
		
		
		
	}

}
