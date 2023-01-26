package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {
    Map<String,Movie> dbMovie=new HashMap<>();
    Map<String,Director> dbDirector=new HashMap<>();
    Map<String, List<String>> dbPair=new HashMap<>();

    public String addMovie(Movie movie){
          dbMovie.put(movie.getName(),movie);
          return "Movie added successfully";
    }
    public String addDirector(Director director){
        dbDirector.put(director.getName(),director);
        return "Director added successfully";
    }

    public String addMovieDirectorPair(String movieName,String director){
        if(!dbMovie.containsKey(movieName) || !dbDirector.containsKey(director))
             return "Movie/Director not found";
        if(dbPair.containsKey(director)){
            dbPair.get(director).add(movieName);
        }
        else{
            dbPair.put(director,new ArrayList<>());
            dbPair.get(director).add(movieName);
        }
        return "pair added successfully";
    }
    public Movie getMovieByName(String movieName){
        if(!dbMovie.containsKey(movieName)) return  null;
       return dbMovie.get(movieName);

    }
    public Director getDirectorByName(String directorName){
        if(!dbDirector.containsKey(directorName))  return  null;
        return dbDirector.get(directorName);
    }
    public List<String> getMoviesByDirectorName(String directorName){
        if(!dbPair.containsKey(directorName)) return new ArrayList<>();
        return dbPair.get(directorName);

    }
    public List<String>  findAllMovies(){
       return new ArrayList<>(dbMovie.keySet());
    }
    public String deleteDirectorByName(String directorName){
        if(!dbDirector.containsKey(directorName)) return "Invalid Request";
        dbDirector.remove(directorName);
        List<String> temp=dbPair.get(directorName);
        for(String s:temp){
            dbMovie.remove(s);
        }
        dbPair.remove(directorName);
        return "removed director and its movies successfully";
    }
    public String deleteAllDirectors(){
        for(String  key:dbDirector.keySet()){
            List<String> temp=new ArrayList<>();
            if(dbPair.containsKey(key))
                     temp=dbPair.get(key);
            for(String m:temp){
                dbMovie.remove(m);
            }
        }
        dbPair.clear();
        dbDirector.clear();
        return "all directors and its movie deleted successfully";
    }

}
