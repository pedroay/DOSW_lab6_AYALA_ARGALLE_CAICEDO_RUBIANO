package edu.eci.dosw.oficioya.model;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Worker extends User {
    private int rate;
    private ArrayList<Job> secondaryJobs = new ArrayList<>();
    private int salary;
    private int finishedJobs;
    private ArrayList<URL> finishJobsPictures = new ArrayList<>();
    private boolean state;

    private Job PrincipalJob;
    private List<Request> workRequests = new ArrayList<>();
    private WorkZone workZone;
    private List<Disponibility> disponibilities = new ArrayList<>();
}
