// packages
package ie.atu.sw;

/**
 * This class contains methods to compare 2 double vector arrays for a similarity score
 */

public class ComparingVectorHandler {
	// class methods
	/**
	 * This function returns the similarity Score score calculated by Dot Product
	 */
	static public double DotProduct(double[] value1, double[] value2)
	{
		// this function (O)n , 1 loop with the iterations n, the length of the vector array
		double similarityScore = 0;
		
		for (int i = 0; i < 50; i++)
		{
			similarityScore += value1[i] * value2[i];
		}
		
		return similarityScore;
	}
	
	/**
	 * This function returns the similarity Score score calculated by Euclidean Distance
	 */
	static public double EuclideanDistance(double[] value1, double[] value2)
	{
		double similarityScore = 0;
		
		// this function (O)n , 1 loop with the iterations n, the length of the vector array
		for (int i = 0; i < 50; i++)
		{
			similarityScore += Math.pow((value1[i] - value2[i]), 2);
		}
		
		similarityScore = Math.sqrt(similarityScore);
		
		return similarityScore;
	}
	
	/**
	 * This function returns the similarity Score score calculated by Cosine Distance
	 */
	static public double CosineDistance(double[] value1, double[] value2)
	{
		double similarityScore = 0;
		double dotProduct = DotProduct(value1 , value2);
		double valS1 = 0, valS2 = 0;
		
		// this function (O)n , 1 loop with the iterations n, the length of the vector array
		for (int i = 0; i < 50; i++)
		{
			valS1 += Math.pow(value1[i], 2);
			valS2 += Math.pow(value2[i], 2);
		}
		
		similarityScore = dotProduct / (Math.sqrt(valS1) * Math.sqrt(valS2));
		
		return similarityScore;
	}
}
