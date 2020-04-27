package com.decagon.algorithm;

import com.decagon.algorithm.model.Author;
import com.decagon.algorithm.model.BaseData;
import com.decagon.algorithm.utils.NetworkUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class FirstAlgorithmTest {
    private final static String API_URL = "https://jsonmock.hackerrank.com/api/article_users";

    public static List<String> getUsernames(int threshold) {
        List<Author> authorList = getAllAuthors();
        return getUserNamesFromAuthors(authorList, threshold);
    }

    private static List<Author> getAllAuthors() {
       try {
           List<Author> authorList = new ArrayList<>();
           //First page call
           BaseData baseData = NetworkUtils.apiCall(API_URL);
           if(baseData != null && baseData.data != null){
               authorList.addAll(baseData.data);

               //If total page is more than one, iterate through thr pages starting from page 2
               if(baseData.total_pages > 1){
                   for (int i = 2; i <= baseData.total_pages; i++){
                       BaseData currentPage = NetworkUtils.apiCall(API_URL + "/search?page=" + i);
                       if(currentPage != null && currentPage.data != null)
                            authorList.addAll(currentPage.data);
                   }
               }
           }
           return authorList;
       }catch (Exception e){
           return null;
       }
    }

    private static List<String> getUserNamesFromAuthors(List<Author> data, int threshold) {
        try {
            Collections.sort(data, new Comparator<Author>() {
                @Override
                public int compare(Author author1, Author author2) {
                    return author2.submission_count - author1.submission_count;
                }
            });

            List<String> temp= new ArrayList<>();

            for (int i = 0; i < threshold; i ++){
                //check if the author list is upto the set threshold
                if(data.size() >= i)
                    temp.add(data.get(i).username);
            }
            return temp;

        }catch (Exception e){
            return null;
        }
    }

    public static String getUsernameWithHighestCommentCount(){
        List<Author> authorList = getAllAuthors();
        return getUserWithHigCommFromAuthors(authorList);
    }

    private static String getUserWithHigCommFromAuthors(List<Author> data) {
        try {
            Collections.sort(data, new Comparator<Author>() {
                @Override
                public int compare(Author author1, Author author2) {
                    return author2.comment_count - author1.comment_count;
                }
            });

            return data.get(0).username;

        }catch (Exception e){
            return null;
        }
    }

    public static List<String> getUsernamesSortedByRecordDate(int threshold) {
        List<Author> authorList = getAllAuthors();
        return getUsernamesSortedByRecordDateFromAuthors(authorList, threshold);

    }

    private static List<String> getUsernamesSortedByRecordDateFromAuthors(List<Author> data, int threshold) {
       try {

           Collections.sort(data, new Comparator<Author>() {
               @Override
               public int compare(Author author1, Author author2) {
                   return new Date(author1.created_at).compareTo(new Date(author2.created_at));
               }
           });

           List<String> temp= new ArrayList<>();

           for (int i = 0; i < threshold; i ++){
               //check if the author list is upto the set threshold
               if(data.size() >= i)
                   temp.add(data.get(i).username);
           }
           return temp;
       }catch (Exception e){
           return null;
       }
    }
}
