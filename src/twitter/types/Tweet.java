package twitter.types;
import java.util.LinkedList;
import java.util.List;

/**
* This is the Tweet model. Mainly, this is a container for hashtags with annotations for time, user, and tweetID.
*/
public class Tweet implements Comparable<Tweet>{

  private long tweetID;
  private long time;
  private int numberOfRT;
  private int UserID;
  private List<Hashtag> hashtags;

  /**
   * @param tweetID
   */
  public Tweet(long tweetID) {
    this.tweetID = tweetID;
    numberOfRT = 0;
    hashtags = new LinkedList<Hashtag>();
  }

  public Tweet logicalCopy(Tweet twt){
    twt.time = time;
    twt.numberOfRT = 0;
    twt.UserID = UserID;
    twt.hashtags = hashtags;

    return twt;
  }

  /**
   * @return the tweetID
   */
  public long getTweetID() {
    return tweetID;
  }

  /**
   * @param tweetID the tweetID to set
   */
  public void setTweetID(int tweetID) {
    this.tweetID = tweetID;
  }

  /**
   * @return the time
   */
  public long getTime() {
    return time;
  }

  /**
   * @param time the time to set
   */
  public void setTime(long time) {
    this.time = time;
  }
  
  
  /**
   * @return the userID
   */
  public int getUserID() {
    return UserID;
  }

  /**
   * @param userID the userID to set
   */
  public void setUserID(int userID) {
    UserID = userID;
  }

  /**
   * @return the numberOfRT
   */
  public void incrementNumberOfRT() {
    numberOfRT++;
  }
  
  /**
   * @return the numberOfRT
   */
  public int getNumberOfRT() {
    return numberOfRT;
  }

  /**
   * @return the hashtags
   */
  public List<Hashtag> getHashtags() {
    return hashtags;
  }

  /**
   * @param hashtags the hashtags to set
   */
  public void setHashtags(List<Hashtag> hashtags) {
    this.hashtags = hashtags;
  }

  public int compareTo(Tweet otherTweet) {
    return (int) (((Tweet) otherTweet).getTime() - getTime());
  }

  public String toString(){
    String s = "ID: " + tweetID + ", Text: ";

    if(hashtags != null){
      for(Hashtag ht : hashtags){
        s += "" + ht;
      }
    }

    return s + ", Authored at: " + time;
  }
  
  
}
