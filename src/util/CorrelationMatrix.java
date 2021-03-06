package util;

public class CorrelationMatrix{
  /**
  * Calculates the correlation between each hashtag.
  * 
  * @param timeseries
  * This is the parameter that contains the timeseries for each entity.
  */
  public static Matrix makeCorrelationMatrix(double[][] timeseries){
    Matrix m = new Matrix(timeseries[0].length, timeseries[0].length);

    //set diagonal to 1.
    for(int i = 0; i < m.getColumnDimension(); i++){
      m.set(i,i,1.0);
    }

    for(int i = 0; i < m.getColumnDimension(); i++){
      for(int j = i + 1; j < m.getColumnDimension(); j++){
        double correl = calcCorrelation(
          getColumn(timeseries, i),
          getColumn(timeseries, j)
        );
        m.set(i, j, correl);
        m.set(j, i, correl);
      }
    }

    return m;
  }

  private static double[] getColumn(double[][] mtx, int idx){
    double[] col = new double[mtx.length];

    for(int i = 0; i < mtx.length; i++){
      col[i] = mtx[i][idx];
    }
    return col;
  }

  private static double calcCorrelation(double[] ts1, double[] ts2){
    //find Sxx
    double sxx = Sxx(ts1),
      syy = Sxx(ts2),
      mux = mu(ts1),
      muy = mu(ts2),
      sxy = 0.0;

    for(int i = 0; i < ts1.length; i++){
      sxy += (ts1[i] - mux) * (ts2[i] - muy);
    }

    return sxy / Math.sqrt(sxx * syy);
  }

  private static double Sxx(double[] x){
    double mu = mu(x),
      sxx = 0.0;

    for(double i : x){
      sxx += (i - mu) * (i - mu);
    }

    return sxx;
  }

  private static double mu(double[] x){
    double s = 0.0;
    for(int i = 0; i < x.length; i++){
      s += x[i];
    }
    return s / x.length;
  }
}