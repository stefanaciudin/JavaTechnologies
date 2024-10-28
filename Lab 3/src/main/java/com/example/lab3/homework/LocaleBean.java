package com.example.lab3.homework;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Locale;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class LocaleBean {
    private String language = "en"; // default language

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(language));
    }
}