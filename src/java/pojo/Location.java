package pojo;

import DAO.locationDAO;  // Updated to use locationDAO
import javax.faces.bean.ManagedBean;
import java.util.List;

@ManagedBean
public class Location implements java.io.Serializable {

    private Integer id;
    private String city;
    private String branchLocation;
    private List<Location> locationList;
    private locationDAO locationDAO = new locationDAO();  // Uses locationDAO with lowercase

    public Location() {
    }

    public Location(String city, String branchLocation) {
        this.city = city;
        this.branchLocation = branchLocation;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBranchLocation() {
        return branchLocation;
    }

    public void setBranchLocation(String branchLocation) {
        this.branchLocation = branchLocation;
    }

    // Method to retrieve all locations
    public List<Location> getLocationList() {
        if (locationList == null) {
            locationList = locationDAO.retrieveTblLocations();
        }
        return locationList;
    }

    // Method to get location by ID
    public String getById() {
        List<Location> listLocation = locationDAO.getById(id);
        if (!listLocation.isEmpty()) {
            city = listLocation.get(0).getCity();
            branchLocation = listLocation.get(0).getBranchLocation();
            return "location"; // Navigate to location detail page
        }
        return "index"; // Navigate to index if not found
    }

    // Method to save a new location
    public String saveLocation() {
        locationDAO.addLocation(this);
        return "adminlocation"; // Navigate back to location admin page
    }

    // Method to edit location
    public String editLocation() {
        locationDAO.editLocation(this);
        return "adminlocation"; // Navigate back to location admin page
    }

    // Method to delete location
    public String deleteLocation() {
        locationDAO.deleteLocation(id);
        return "adminlocation"; // Navigate back to location admin page
    }
}
