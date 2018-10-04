package com.zaid.green.tmdbmovieutil2;

import com.google.gson.JsonObject;

import java.io.Serializable;

public class TMDBActor implements Serializable {
    private int actorId;
    private String profilePath;
    private String name;
    private String role;


    public TMDBActor(int actorId, String profilePath, String name, String role) {
        this.actorId = actorId;
        this.profilePath = profilePath;
        this.name = name;
        this.role = role;
    }


    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static TMDBActor createActorFromJson(JsonObject actorInfo) {
        TMDBActor TMDBActor = new TMDBActor(0, "", "", "");
        if (actorInfo.getAsJsonObject() != null) {
            TMDBActor.actorId = actorInfo.getAsJsonObject().get("id").getAsInt();
            TMDBActor.name = fixStr(actorInfo.getAsJsonObject().get("name").getAsString());
            TMDBActor.role = fixStr(actorInfo.getAsJsonObject().get("character").getAsString());
            if (actorInfo.getAsJsonObject().get("profile_path") != null) {
                fixStr(TMDBActor.profilePath = actorInfo.getAsJsonObject().get("profile_path").toString());
            }
        }
        return TMDBActor;
    }

    private static String fixStr(String str) {
        if (str.length() > 0) {
            if (str.charAt(0) == '"')
                str = str.substring(1, str.length() - 1);
            return str;
        }
        else str = "";
        return str;
    }
}
