package com.velas.candil.models;

public enum EmailTemplate {

    ACTIVATE_ACCOUNT("activate_account");

    public final String template;

    EmailTemplate(String template) {
        this.template = template;
    }
}