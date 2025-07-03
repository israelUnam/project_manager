package com.losung.projectmanager.model;

public enum BitAcciones {
    PORTAFOLIO_CREADO("Portafolio Creado"),
    PORTAFOLIO_MODIFICADO("Portafolio Modificado"),
    PORTAFOLIO_BORRADO("Portafolio Borrado"),
    PROGRAMA_CREADO("Programa Creado"),
    PROGRAMA_MODIFICADO("Programa Modificado"),
    PROGRAMA_BORRADO("Programa Borrado"),
    PROYECTO_CREADO("Proyecto Creado"),
    PROYECTO_MODIFICADO("Proyecto Modificado"), 
    PROYECTO_BORRADO("Proyecto Borrado"),;

    private final String descripcion;

    BitAcciones(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
