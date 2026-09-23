package edu.eci.dosw.oficioya.model;

import java.time.LocalDateTime;
import java.util.Timer;

public class Disponibility {
    private boolean active;
    private LocalDateTime date;
    private Timer startHour;
    private Timer finishHour;
}
