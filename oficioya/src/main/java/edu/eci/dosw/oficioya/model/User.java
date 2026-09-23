package edu.eci.dosw.oficioya.model;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String nombre;
    private URL foto;
    protected String correo;
    protected int telefono;
    private String passwd;
    
    private List<Notification> notifications = new ArrayList<>();
}
