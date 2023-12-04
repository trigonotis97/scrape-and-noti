package com.trigo.scrapeandnoti.noti;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JandiDto {
    String message;
    String connectColor;
    String title;
    String description;

    public String toString(){
        return " { \"body\" : \"" + message + "\"}";
    }

    public JandiDto() {
        this.message = "";
        this.connectColor = "";
        this.title = "";
        this.description = "";
    }
}

/*
 """
 {
"body" : " You have a new Pizza order.",
"connectColor" : "#4D7A97",
"connectInfo" : [{
"title" : "Topping",
"description" : "Pepperoni"
},
{
"title": "Location",
"description": "Empire State Building, 5th Ave, New York",
"imageUrl": ""
}]
}
"""
 */