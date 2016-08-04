/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WeeklyReport;

import Connections.DBConnection;
import Dates.ReportingDates;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author cmeehan
 */
public class CargoTypeData {

    private double commodityType;
    private final Connection conn = new DBConnection().Connect();
    private final Map<String, String> names = new HashMap<>();
    private int count;
    private ResultSet rs;
    private String topCommodity;
    private final String LAST_WEEK = new ReportingDates().lastWeek(), THIS_WEEK = new ReportingDates().firstOfCurrentWeek();

    private String firstOfCurrentWeek() {
        SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd 00:00");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        String firstOfWeek = df.format(cal.getTime());
        return firstOfWeek;
    }

    /*
    N/A, Automobile/POV, Automobile/POV (up to 14 cubic meters), Automobile/POV (14 - 17 cubic meters), Automobile/POV (17 - 25 cubic meters), Automobile/POV (up to 16 cubic meters), Automobile/POV (16 - 20 cubic meters), Automobile/POV (20 - 25 cubic meters), New Automobile, New Automobile (up to 14 cubic meters), New Automobile (14 - 17 cubic meters), New Automobile (17 - 25 cubic meters), New Automobile (up to 16 cubic meters), New Automobile (16 - 20 cubic meters), New Automobile (20 - 25 cubic meters), Motorhome(s), Truck(s), Bus(es), Van(s), Chassis, Travel trailer(s), Utility trailer(s), 5th wheel trailer(s), Agriculture Equipment, Construction Equipment, Forestry Equipment, Mining Equipment, Handling Equipment, Crane(s), Tractor(s), Boat(s) on Trailer, Boat(s) on Cradle, Static, Static Machinery, Special Purpose Vehicles, Motorcycle(s), All-Terrain Vehicle(s), Jet Ski(s) (up to 2 cbm), Aircraft
     */
    public String topNASCommodity() {
        String SQL = "SELECT comm_class, COUNT(comm_class) AS 'COUNT' FROM allquotes WHERE tradeLane = ? AND IF(DATE_UPDATED = ?, DATE_QUOTED >= ?, DATE_UPDATED >= ?) AND IF(DATE_UPDATED = ?, DATE_QUOTED <= ?, DATE_UPDATED <= ?) group by comm_class order by COUNT(comm_class) desc LIMIT ?";
        try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, "North Atlantic Shuttle EB");
            ps.setString(2, "0000-00-00 00:00");
            ps.setString(3, new ReportingDates().lastWeek());
            ps.setString(4, new ReportingDates().lastWeek());
            ps.setString(5, "0000-00-00 00:00");
            ps.setString(6, new ReportingDates().firstOfCurrentWeek());
            ps.setString(7, new ReportingDates().firstOfCurrentWeek());
            ps.setInt(8, 1);
            rs = ps.executeQuery();
            while (rs.next()) {
                topCommodity = rs.getString("comm_class");
            }

        } catch (Exception ex) {

        }
        return topCommodity;
    }

    public String topTalCargo() {
        String SQL = "SELECT comm_class, COUNT(comm_class) AS 'COUNT' FROM allquotes WHERE tradeLane = ? AND IF(DATE_UPDATED = ?, DATE_QUOTED >= ?, DATE_UPDATED >= ?) AND IF(DATE_UPDATED = ?, DATE_QUOTED <= ?, DATE_UPDATED <= ?) group by comm_class order by COUNT(comm_class) desc LIMIT ?";
        try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, "Trans-Atlantic EB");
            ps.setString(2, "0000-00-00 00:00");
            ps.setString(3, new ReportingDates().lastWeek());
            ps.setString(4, new ReportingDates().lastWeek());
            ps.setString(5, "0000-00-00 00:00");
            ps.setString(6, new ReportingDates().firstOfCurrentWeek());
            ps.setString(7, new ReportingDates().firstOfCurrentWeek());
            ps.setInt(8, 1);
            rs = ps.executeQuery();
            while (rs.next()) {
                topCommodity = rs.getString("comm_class");
            }

        } catch (Exception ex) {

        }
        return topCommodity;
    }

    public String topEcamsCargo() {
        String SQL = "SELECT comm_class, COUNT(comm_class) AS 'COUNT' FROM allquotes WHERE tradeLane = ? AND IF(DATE_UPDATED = ?, DATE_QUOTED >= ?, DATE_UPDATED >= ?) AND IF(DATE_UPDATED = ?, DATE_QUOTED <= ?, DATE_UPDATED <= ?) group by comm_class order by COUNT(comm_class) desc LIMIT ?";
        try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, "ECAMS SB");
            ps.setString(2, "0000-00-00 00:00");
            ps.setString(3, new ReportingDates().lastWeek());
            ps.setString(4, new ReportingDates().lastWeek());
            ps.setString(5, "0000-00-00 00:00");
            ps.setString(6, new ReportingDates().firstOfCurrentWeek());
            ps.setString(7, new ReportingDates().firstOfCurrentWeek());
            ps.setInt(8, 1);
            rs = ps.executeQuery();
            while (rs.next()) {
                topCommodity = rs.getString("comm_class");
            }

        } catch (Exception ex) {

        }
        return topCommodity;
    }

    public String topNaxCargo() {
        String SQL = "SELECT comm_class, COUNT(comm_class) AS 'COUNT' FROM allquotes WHERE tradeLane = ? AND IF(DATE_UPDATED = ?, DATE_QUOTED >= ?, DATE_UPDATED >= ?) AND IF(DATE_UPDATED = ?, DATE_QUOTED <= ?, DATE_UPDATED <= ?) group by comm_class order by COUNT(comm_class) desc LIMIT ?";
        try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, "NAX WB");
            ps.setString(2, "0000-00-00 00:00");
            ps.setString(3, new ReportingDates().lastWeek());
            ps.setString(4, new ReportingDates().lastWeek());
            ps.setString(5, "0000-00-00 00:00");
            ps.setString(6, new ReportingDates().firstOfCurrentWeek());
            ps.setString(7, new ReportingDates().firstOfCurrentWeek());
            ps.setInt(8, 1);
            rs = ps.executeQuery();
            while (rs.next()) {
                topCommodity = rs.getString("comm_class");
            }

        } catch (Exception ex) {

        }
        return topCommodity;
    }

    public Map<String, String> topTwoCommodities() {
        String SQL = "SELECT comm_class, COUNT(comm_class) AS 'COUNT' FROM allquotes INNER JOIN rorocustomers ON allquotes.customerName = rorocustomers.company WHERE (rorocustomers.region=? OR  rorocustomers.region=? OR  rorocustomers.region=? OR  rorocustomers.region=?) AND IF(DATE_UPDATED = ?, DATE_QUOTED >= ?, DATE_UPDATED >= ?) AND IF(DATE_UPDATED = ?, DATE_QUOTED <= ?, DATE_UPDATED <= ?) group by comm_class order by COUNT(comm_class) desc LIMIT ?";
        try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, "South East");
            ps.setString(2, "North East");
            ps.setString(3, "Mid West");
            ps.setString(4, "West Coast");
            ps.setString(5, "0000-00-00 00:00");
            ps.setString(6, new ReportingDates().lastWeek());
            ps.setString(7, new ReportingDates().lastWeek());
            ps.setString(8, "0000-00-00 00:00");
            ps.setString(9, new ReportingDates().firstOfCurrentWeek());
            ps.setString(10, new ReportingDates().firstOfCurrentWeek());
            ps.setInt(11, 2);

            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt("COUNT");
                names.put(rs.getString("comm_class"), String.valueOf(count));
            }
        } catch (Exception ex) {
        }

        return names;
    }

    public Map<Double, String> quotesByCommodityCBM() {
        Map<Double,String>quotesByCBM = new HashMap<>();
        String SQL = "SELECT comm_class, ROUND(SUM(packinglist.cbm) * packinglist.quantity,3) AS 'Total CBM' FROM allquotes JOIN packinglist ON packinglist.quoteID = allquotes.ID JOIN rorocustomers ON allquotes.customerName = rorocustomers.company WHERE (rorocustomers.region=? OR  rorocustomers.region=? OR  rorocustomers.region=? OR  rorocustomers.region=?) AND IF(DATE_UPDATED = ?, DATE_QUOTED >= ?, DATE_UPDATED >= ?) AND IF(DATE_UPDATED = ?, DATE_QUOTED <= ?, DATE_UPDATED <= ?) group by comm_class LIMIT ?";
        try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, "South East");
            ps.setString(2, "North East");
            ps.setString(3, "Mid West");
            ps.setString(4, "West Coast");
            ps.setString(5, "0000-00-00 00:00");
            ps.setString(6, new ReportingDates().lastWeek());
            ps.setString(7, new ReportingDates().lastWeek());
            ps.setString(8, "0000-00-00 00:00");
            ps.setString(9, new ReportingDates().firstOfCurrentWeek());
            ps.setString(10, new ReportingDates().firstOfCurrentWeek());
            ps.setInt(11, 10);

            rs = ps.executeQuery();
            while (rs.next()) {
                quotesByCBM.put(rs.getDouble("Total CBM"), rs.getString("comm_class"));
            }
        } catch (Exception ex) {
        }

        return quotesByCBM;
    }

    public Map<String, String> quotesByCommodity() {
        String SQL = "SELECT comm_class, COUNT(comm_class) AS 'COUNT' FROM allquotes INNER JOIN rorocustomers ON allquotes.customerName = rorocustomers.company WHERE (rorocustomers.region=? OR  rorocustomers.region=? OR  rorocustomers.region=? OR  rorocustomers.region=?) AND IF(DATE_UPDATED = ?, DATE_QUOTED >= ?, DATE_UPDATED >= ?) AND IF(DATE_UPDATED = ?, DATE_QUOTED <= ?, DATE_UPDATED <= ?) group by comm_class order by COUNT(comm_class) desc LIMIT ?";
        try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, "South East");
            ps.setString(2, "North East");
            ps.setString(3, "Mid West");
            ps.setString(4, "West Coast");
            ps.setString(5, "0000-00-00 00:00");
            ps.setString(6, new ReportingDates().lastWeek());
            ps.setString(7, new ReportingDates().lastWeek());
            ps.setString(8, "0000-00-00 00:00");
            ps.setString(9, new ReportingDates().firstOfCurrentWeek());
            ps.setString(10, new ReportingDates().firstOfCurrentWeek());
            ps.setInt(11, 10);

            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt("COUNT");
                names.put(rs.getString("comm_class"), String.valueOf(count));
            }
        } catch (Exception ex) {
        }

        return names;
    }

    protected double automobiles() {
        String SQL = "SELECT COUNT(*) AS 'TOTAL' FROM allquotes WHERE IF(DATE_UPDATED = '0000-00-00 00:00', DATE_QUOTED >='" + firstOfCurrentWeek() + "', DATE_UPDATED>='" + firstOfCurrentWeek() + "') AND comm_class LIKE 'Automobile%'";
        try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            if (rs.next()) {
                commodityType = rs.getInt("TOTAL");
            }
        } catch (Exception ex) {

        }
        System.out.println(SQL);
        System.out.println("Automobiles: " + commodityType);
        return commodityType;
    }

    protected double trucks() {
        String SQL = "SELECT COUNT(*) AS 'TOTAL' FROM allquotes WHERE IF(DATE_UPDATED = '', DATE_QUOTED >='" + firstOfCurrentWeek() + "', DATE_UPDATED>='" + firstOfCurrentWeek() + "') AND (comm_class ='Motorhome(s)' OR comm_class = 'Truck(s)' OR comm_class = 'Bus(es)' OR comm_class = 'Van(s)' OR comm_class = 'Chassis')";
        try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            if (rs.next()) {
                commodityType = rs.getInt("TOTAL");
            }
        } catch (Exception ex) {

        }
        return commodityType;
    }

    protected double construction() {
        String SQL = "SELECT COUNT(*) AS 'TOTAL' FROM allquotes WHERE IF(DATE_UPDATED = '0000-00-00 00:00', DATE_QUOTED >='" + firstOfCurrentWeek() + "', DATE_UPDATED>='" + firstOfCurrentWeek() + "') AND (comm_class ='Construction Equipment' OR comm_class = 'Forestry Equipment' OR comm_class = 'Mining Equipment' OR comm_class = 'Handling Equipment' OR comm_class = 'Crane(s)')";
        try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            if (rs.next()) {
                commodityType = rs.getInt("TOTAL");
            }
        } catch (Exception ex) {

        }
        return commodityType;
    }

    protected double agricultural() {
        String SQL = "SELECT COUNT(*) AS 'TOTAL' FROM allquotes WHERE IF(DATE_UPDATED = '0000-00-00 00:00', DATE_QUOTED >='" + firstOfCurrentWeek() + "', DATE_UPDATED>='" + firstOfCurrentWeek() + "') AND (comm_class ='Agricultural Equipment' OR comm_class = 'Tractor(s)')";
        try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            if (rs.next()) {
                commodityType = rs.getInt("TOTAL");
            }
        } catch (Exception ex) {

        }
        return commodityType;
    }

    protected double BT() {
        String SQL = "SELECT COUNT(*) AS 'TOTAL' FROM allquotes WHERE IF(DATE_UPDATED = '0000-00-00 00:00', DATE_QUOTED >='" + firstOfCurrentWeek() + "', DATE_UPDATED>='" + firstOfCurrentWeek() + "') AND comm_class ='Boat(s) on Trailer(s)'";
        try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            if (rs.next()) {
                commodityType = rs.getInt("TOTAL");
            }
        } catch (Exception ex) {

        }
        return commodityType;
    }

    protected double BC() {
        String SQL = "SELECT COUNT(*) AS 'TOTAL' FROM allquotes WHERE IF(DATE_UPDATED = '', DATE_QUOTED >=" + firstOfCurrentWeek() + ", DATE_UPDATED>=" + firstOfCurrentWeek() + ") AND comm_class ='Boat(s) on Cradle'";
        try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            if (rs.next()) {
                commodityType = rs.getInt("TOTAL");
            }
        } catch (Exception ex) {

        }
        return commodityType;
    }

    protected double staticCargo() {
        String SQL = "SELECT COUNT(*) AS 'TOTAL' FROM allquotes WHERE IF(DATE_UPDATED = '', DATE_QUOTED >=" + firstOfCurrentWeek() + ", DATE_UPDATED>=" + firstOfCurrentWeek() + ") AND (comm_class ='Static' OR comm_class = 'Static Machinery')";
        try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            if (rs.next()) {
                commodityType = rs.getInt("TOTAL");
            }
        } catch (Exception ex) {

        }
        return commodityType;
    }
}
