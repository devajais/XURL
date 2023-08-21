package com.crio.shorturl;

import java.util.HashMap;


public class XUrlImpl implements XUrl{

    String shortUrl = "http://short.url/";
    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";
    private StringBuilder s = new StringBuilder(9);
    HashMap<String, String> srtUrlHM = new HashMap<>();
    HashMap<String, String> srtUrlHMReverse = new HashMap<>();
    HashMap<String, Integer> hitcount = new HashMap<>();

    public String registerNewUrl(String longUrl){
        if(srtUrlHM.containsKey(longUrl)){
            return srtUrlHM.get(longUrl);
        }
        s.setLength(0);
        for(int i=0;i<9;i++){
            int idx = (int)(alphabet.length()*Math.random());
            s.append(alphabet.charAt(idx));
        }
        srtUrlHM.put(longUrl,shortUrl+s.toString());
        srtUrlHMReverse.put(shortUrl+s.toString(),longUrl);
        return shortUrl+s.toString();
    }

    public String registerNewUrl(String longUrl, String shortUrl){
        if(srtUrlHMReverse.containsKey(shortUrl)){
            return null;
        }
        srtUrlHM.put(longUrl,shortUrl);
        srtUrlHMReverse.put(shortUrl,longUrl);
        return shortUrl;
    }
    /* 
[longurl : shorturl]
[shorturl : longurl]
  del(longurl)
  del(shorturl)
geturl(short) = null
  

*/
    public String getUrl(String shortUrl){
        if(!srtUrlHMReverse.containsKey(shortUrl)) return null;
        if(!hitcount.containsKey(srtUrlHMReverse.get(shortUrl))){
            hitcount.put(srtUrlHMReverse.get(shortUrl), 1);
        }
        else
            hitcount.put(srtUrlHMReverse.get(shortUrl), hitcount.get(srtUrlHMReverse.get(shortUrl))+1);
        return srtUrlHMReverse.get(shortUrl);
    }

    public Integer getHitCount(String longUrl){
        if(!hitcount.containsKey(longUrl))
            return 0;
        return hitcount.get(longUrl);
    }

    public String delete(String longUrl){
        srtUrlHMReverse.remove(srtUrlHM.get(longUrl));
        srtUrlHM.remove(longUrl);
        return "Deleted.";
    }
}