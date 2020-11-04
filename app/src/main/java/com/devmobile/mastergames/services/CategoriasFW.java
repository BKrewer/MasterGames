package com.devmobile.mastergames.services;

import com.devmobile.mastergames.model.Categoria;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class CategoriasFW {
    private static CategoriasFW instancia;
    private Map<Integer, Categoria> categorias;
    private CategoriasFW() {
        this.categorias = new HashMap();
    }
    public static CategoriasFW getInstance() {
        if (instancia == null) {
            instancia = new CategoriasFW();
        }
        return instancia;
    }
    public void addCategoria(Categoria categoria) {
        categorias.put(categoria.getId(),categoria);

    }
    public Collection<Categoria> getCategorias() {
       return categorias.values();
    }
    public Categoria getCategoriaById(int id){
        return categorias.get(id);
    }

    public int getQtdCategorias() {
        return categorias.size();
    }
}
