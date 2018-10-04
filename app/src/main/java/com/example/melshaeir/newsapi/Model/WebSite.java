package com.example.melshaeir.newsapi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WebSite {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("sources")
    @Expose
    private List<Sources> sources ;
    public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<Sources> getSources() {
            return sources;
        }

        public void setSources(List<Sources> sources) {
            this.sources = sources;
        }


}