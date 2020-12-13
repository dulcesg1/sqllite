package mx.edu.utng.recycleviewgds0241;

public class Kpop {
    private String nombre_grupo;
    private String urlPhoto;
    private float valoracion;
    private String nombre_disco;
    private String nombre_cancion;
//Constructor de la clase con los cuatro parametros

    public Kpop(String nombre_grupo, String urlPhoto, float valoracion, String nombre_disco, String nombre_cancion) {
        this.nombre_grupo = nombre_grupo;
        this.urlPhoto = urlPhoto;
        this.valoracion = valoracion;
        this.nombre_disco = nombre_disco;
        this.nombre_cancion = nombre_cancion;
    }

    public String getNombre_grupo() {
        return nombre_grupo;
    }

    public void setNombre_grupo(String nombre_grupo) {
        this.nombre_grupo = nombre_grupo;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
    }

    public String getNombre_disco() {
        return nombre_disco;
    }

    public void setNombre_disco(String nombre_disco) {
        this.nombre_disco = nombre_disco;
    }

    public String getNombre_cancion() {
        return nombre_cancion;
    }

    public void setNombre_cancion(String nombre_cancion) {
        this.nombre_cancion = nombre_cancion;
    }
}

