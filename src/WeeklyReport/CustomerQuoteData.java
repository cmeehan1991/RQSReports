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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author cmeehan
 */
public class CustomerQuoteData {

    private final Connection conn = new DBConnection().Connect();
    private int count;
    private ResultSet rs;
    private final Map<Map<String, String>, Map<String, String>> NAMES = new HashMap<>();
    private String name;
    private final String LAST_WEEK = new ReportingDates().lastWeek(), THIS_WEEK = new ReportingDates().firstOfCurrentWeek();

    public int totalQuotes() {
        String SQL = "SELECT COUNT(*) AS 'COUNT' FROM allquotes WHERE IF(DATE_UPDATED = '0000-00-00 00:00', DATE_QUOTED >= '" + new ReportingDates().lastWeek() + "', DATE_UPDATED >= '" + new ReportingDates().lastWeek() + "') AND IF(DATE_UPDATED = '0000-00-00 00:00', DATE_QUOTED <= '" + new ReportingDates().firstOfCurrentWeek() + "', DATE_UPDATED <= '" + new ReportingDates().firstOfCurrentWeek() + "')";
        try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt("COUNT");
            }
        } catch (Exception ex) {
        }
        return count;
    }

    public int totalNAQuotes() {
        String SQL = "SELECT COUNT(*) AS 'COUNT' FROM allquotes INNER JOIN rorocustomers ON allquotes.customerName = rorocustomers.company WHERE (rorocustomers.region='South East' OR  rorocustomers.region='North East' OR rorocustomers.region='Mid West' OR rorocustomers.region='West Coast') AND IF(DATE_UPDATED = '0000-00-00 00:00', DATE_QUOTED >= '" + new ReportingDates().lastWeek() + "', DATE_UPDATED >= '" + new ReportingDates().lastWeek() + "') AND IF(DATE_UPDATED = '0000-00-00 00:00', DATE_QUOTED <= '" + new ReportingDates().firstOfCurrentWeek() + "', DATE_UPDATED <= '" + new ReportingDates().firstOfCurrentWeek() + "')";
        try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt("COUNT");
            }
        } catch (Exception ex) {
        }

        return count;
    }

    public String topNECustomer() {
        String SQL = "SELECT customerName, COUNT(*) AS 'COUNT' FROM allquotes INNER JOIN rorocustomers ON allquotes.customerName = rorocustomers.company WHERE rorocustomers.region=? AND IF(DATE_UPDATED = ?, DATE_QUOTED >= ?, DATE_UPDATED >= ?) AND IF(DATE_UPDATED = ?, DATE_QUOTED <= ?, DATE_UPDATED <= ?) GROUP BY customerName ORDER BY COUNT(*) DESC LIMIT 1";
        try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, "North East");
            ps.setString(2, "0000-00-00 00:00");
            ps.setString(3, LAST_WEEK);
            ps.setString(4, LAST_WEEK);
            ps.setString(5, "0000-00-00 00:00");
            ps.setString(6, THIS_WEEK);
            ps.setString(7, THIS_WEEK);
            rs = ps.executeQuery();
            if (rs.next()) {
                name = rs.getString("customerName");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return name;
    }

    public String topSECustomer() {
                String SQL = "SELECT customerName, COUNT(*) AS 'COUNT' FROM allquotes INNER JOIN rorocustomers ON allquotes.customerName = rorocustomers.company WHERE rorocustomers.region=? AND IF(DATE_UPDATED = ?, DATE_QUOTED >= ?, DATE_UPDATED >= ?) AND IF(DATE_UPDATED = ?, DATE_QUOTED <= ?, DATE_UPDATED <= ?) GROUP BY customerName ORDER BY COUNT(*) DESC LIMIT 1";
        try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, "South East");
            ps.setString(2, "0000-00-00 00:00");
            ps.setString(3, LAST_WEEK);
            ps.setString(4, LAST_WEEK);
            ps.setString(5, "0000-00-00 00:00");
            ps.setString(6, THIS_WEEK);
            ps.setString(7, THIS_WEEK);
            rs = ps.executeQuery();
            if (rs.next()) {
                name = rs.getString("customerName");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return name;
    }

    public String topMWCustomer() {
                String SQL = "SELECT customerName, COUNT(*) AS 'COUNT' FROM allquotes INNER JOIN rorocustomers ON allquotes.customerName = rorocustomers.company WHERE rorocustomers.region=? AND IF(DATE_UPDATED = ?, DATE_QUOTED >= ?, DATE_UPDATED >= ?) AND IF(DATE_UPDATED = ?, DATE_QUOTED <= ?, DATE_UPDATED <= ?) GROUP BY customerName ORDER BY COUNT(*) DESC LIMIT 1";
        try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, "Mid West");
            ps.setString(2, "0000-00-00 00:00");
            ps.setString(3, LAST_WEEK);
            ps.setString(4, LAST_WEEK);
            ps.setString(5, "0000-00-00 00:00");
            ps.setString(6, THIS_WEEK);
            ps.setString(7, THIS_WEEK);
            rs = ps.executeQuery();
            if (rs.next()) {
                name = rs.getString("customerName");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return name;
    }

    public String topWCCustomer() {        
        String SQL = "SELECT customerName, COUNT(*) AS 'COUNT' FROM allquotes INNER JOIN rorocustomers ON allquotes.customerName = rorocustomers.company WHERE rorocustomers.region=? AND IF(DATE_UPDATED = ?, DATE_QUOTED >= ?, DATE_UPDATED >= ?) AND IF(DATE_UPDATED = ?, DATE_QUOTED <= ?, DATE_UPDATED <= ?) GROUP BY customerName ORDER BY COUNT(*) DESC LIMIT 1";
        try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, "West Coast");
            ps.setString(2, "0000-00-00 00:00");
            ps.setString(3, LAST_WEEK);
            ps.setString(4, LAST_WEEK);
            ps.setString(5, "0000-00-00 00:00");
            ps.setString(6, THIS_WEEK);
            ps.setString(7, THIS_WEEK);
            rs = ps.executeQuery();
            if (rs.next()) {
                name = rs.getString("customerName");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return name;
    }

    public Map<Map<String, String>, Map<String, String>> southeastQuotes() {
        String SQL = "SELECT allquotes.tradeLane AS 'Trade Lane', customerName AS 'Customer Name', ROUND(SUM(packinglist.quantity),0) AS 'Total Units', ROUND(SUM(packinglist.cbm),3) AS 'Total CBM' FROM allquotes JOIN packinglist ON packinglist.quoteID = allquotes.ID JOIN rorocustomers ON rorocustomers.company = allquotes.customerName WHERE rorocustomers.region='South East' AND IF(DATE_UPDATED = '0000-00-00 00:00', DATE_QUOTED >= '" + new ReportingDates().lastWeek() + "', DATE_UPDATED >= '" + new ReportingDates().lastWeek() + "') AND IF(DATE_UPDATED = '0000-00-00 00:00', DATE_QUOTED <= '" + new ReportingDates().firstOfCurrentWeek() + "', DATE_UPDATED <= '" + new ReportingDates().firstOfCurrentWeek() + "') GROUP BY customerName ORDER BY SUM(packinglist.cbm) DESC LIMIT 10";
        try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                NAMES.put(new HashMap(){{put(rs.getString("Trade Lane"), rs.getString("Customer Name"));}}, new HashMap(){{put(rs.getString("Total Units"), rs.getString("Total CBM"));}});
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return NAMES;
    }

    public Map<Map<String, String>, Map<String, String>> northEastQuotes() {
        String SQL = "SELECT allquotes.tradeLane AS 'Trade Lane', customerName AS 'Customer Name', ROUND(SUM(packinglist.quantity),0) AS 'Total Units', ROUND(SUM(packinglist.cbm),3) AS 'Total CBM' FROM allquotes JOIN packinglist ON packinglist.quoteID = allquotes.ID JOIN rorocustomers ON rorocustomers.company = allquotes.customerName WHERE rorocustomers.region='North East' AND IF(DATE_UPDATED = '0000-00-00 00:00', DATE_QUOTED >= '" + new ReportingDates().lastWeek() + "', DATE_UPDATED >= '" + new ReportingDates().lastWeek() + "') AND IF(DATE_UPDATED = '0000-00-00 00:00', DATE_QUOTED <= '" + new ReportingDates().firstOfCurrentWeek() + "', DATE_UPDATED <= '" + new ReportingDates().firstOfCurrentWeek() + "') GROUP BY customerName ORDER BY SUM(packinglist.cbm) DESC LIMIT 10";
        try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                NAMES.put(new HashMap(){{put(rs.getString("Trade Lane"), rs.getString("Customer Name"));}}, new HashMap(){{put(rs.getString("Total Units"), rs.getString("Total CBM"));}});
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return NAMES;
    }

    public Map<Map<String, String>, Map<String, String>> midWestQuotes() {
       String SQL = "SELECT allquotes.tradeLane AS 'Trade Lane', customerName AS 'Customer Name', ROUND(SUM(packinglist.quantity),0) AS 'Total Units', ROUND(SUM(packinglist.cbm),3) AS 'Total CBM' FROM allquotes JOIN packinglist ON packinglist.quoteID = allquotes.ID JOIN rorocustomers ON rorocustomers.company = allquotes.customerName WHERE rorocustomers.region='Mid West' AND IF(DATE_UPDATED = '0000-00-00 00:00', DATE_QUOTED >= '" + new ReportingDates().lastWeek() + "', DATE_UPDATED >= '" + new ReportingDates().lastWeek() + "') AND IF(DATE_UPDATED = '0000-00-00 00:00', DATE_QUOTED <= '" + new ReportingDates().firstOfCurrentWeek() + "', DATE_UPDATED <= '" + new ReportingDates().firstOfCurrentWeek() + "') GROUP BY customerName ORDER BY SUM(packinglist.cbm) DESC LIMIT 10";
        try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                NAMES.put(new HashMap(){{put(rs.getString("Trade Lane"), rs.getString("Customer Name"));}}, new HashMap(){{put(rs.getString("Total Units"), rs.getString("Total CBM"));}});
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return NAMES;
    }

    public Map<Map<String, String>, Map<String, String>> westCoastQuotes() {
        String SQL = "SELECT allquotes.tradeLane AS 'Trade Lane', customerName AS 'Customer Name', ROUND(SUM(packinglist.quantity),0) AS 'Total Units', ROUND(SUM(packinglist.cbm),3) AS 'Total CBM' FROM allquotes JOIN packinglist ON packinglist.quoteID = allquotes.ID JOIN rorocustomers ON rorocustomers.company = allquotes.customerName WHERE rorocustomers.region='West Coast' AND IF(DATE_UPDATED = '0000-00-00 00:00', DATE_QUOTED >= '" + new ReportingDates().lastWeek() + "', DATE_UPDATED >= '" + new ReportingDates().lastWeek() + "') AND IF(DATE_UPDATED = '0000-00-00 00:00', DATE_QUOTED <= '" + new ReportingDates().firstOfCurrentWeek() + "', DATE_UPDATED <= '" + new ReportingDates().firstOfCurrentWeek() + "') GROUP BY customerName ORDER BY SUM(packinglist.cbm) DESC LIMIT 10";
        try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                NAMES.put(new HashMap(){{put(rs.getString("Trade Lane"), rs.getString("Customer Name"));}}, new HashMap(){{put(rs.getString("Total Units"), rs.getString("Total CBM"));}});
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return NAMES;
    }

    public int declinedQuotes() {
        String SQL = "SELECT COUNT(*) AS 'COUNT' FROM allquotes INNER JOIN rorocustomers ON allquotes.customerName = rorocustomers.company WHERE (rorocustomers.region='South East' OR  rorocustomers.region='North East' OR rorocustomers.region='Mid West' OR rorocustomers.region='West Coast') AND IF(DATE_UPDATED = '0000-00-00 00:00', DATE_QUOTED >= '" + new ReportingDates().lastWeek() + "', DATE_UPDATED >= '" + new ReportingDates().lastWeek() + "') AND IF(DATE_UPDATED = '0000-00-00 00:00', DATE_QUOTED <= '" + new ReportingDates().firstOfCurrentWeek() + "', DATE_UPDATED <= '" + new ReportingDates().firstOfCurrentWeek() + "') AND deny = true";
        try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("COUNT");
            }
        } catch (Exception ex) {

        }
        return count;
    }

    public int bookedQuotes() {
        String SQL = "SELECT COUNT(*) AS 'COUNT' FROM allquotes INNER JOIN rorocustomers ON allquotes.customerName = rorocustomers.company WHERE (rorocustomers.region='South East' OR  rorocustomers.region='North East' OR rorocustomers.region='Mid West' OR rorocustomers.region='West Coast') AND IF(DATE_UPDATED = '0000-00-00 00:00', DATE_QUOTED >= '" + new ReportingDates().lastWeek() + "', DATE_UPDATED >= '" + new ReportingDates().lastWeek() + "') AND IF(DATE_UPDATED = '0000-00-00 00:00', DATE_QUOTED <= '" + new ReportingDates().firstOfCurrentWeek() + "', DATE_UPDATED <= '" + new ReportingDates().firstOfCurrentWeek() + "') AND booked=true";
        try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("COUNT");
            }
        } catch (Exception ex) {

        }
        return count;
    }

    public int pendingQuotes() {
        String SQL = "SELECT COUNT(*) AS 'COUNT' FROM allquotes INNER JOIN rorocustomers ON allquotes.customerName = rorocustomers.company WHERE (rorocustomers.region='South East' OR  rorocustomers.region='North East' OR rorocustomers.region='Mid West' OR rorocustomers.region='West Coast') AND IF(DATE_UPDATED = '0000-00-00 00:00', DATE_QUOTED >= '" + new ReportingDates().lastWeek() + "', DATE_UPDATED >= '" + new ReportingDates().lastWeek() + "') AND IF(DATE_UPDATED = '0000-00-00 00:00', DATE_QUOTED <= '" + new ReportingDates().firstOfCurrentWeek() + "', DATE_UPDATED <= '" + new ReportingDates().firstOfCurrentWeek() + "') AND deny != true AND booked != true AND feedback != true";
        try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("COUNT");
            }
        } catch (Exception ex) {

        }
        return count;
    }

    public int feedbackQuotes() {
        String SQL = "SELECT COUNT(*) AS 'COUNT' FROM allquotes INNER JOIN rorocustomers ON allquotes.customerName = rorocustomers.company WHERE (rorocustomers.region='South East' OR  rorocustomers.region='North East' OR rorocustomers.region='Mid West' OR rorocustomers.region='West Coast') AND IF(DATE_UPDATED = '0000-00-00 00:00', DATE_QUOTED >= '" + new ReportingDates().lastWeek() + "', DATE_UPDATED >= '" + new ReportingDates().lastWeek() + "') AND IF(DATE_UPDATED = '0000-00-00 00:00', DATE_QUOTED <= '" + new ReportingDates().firstOfCurrentWeek() + "', DATE_UPDATED <= '" + new ReportingDates().firstOfCurrentWeek() + "') AND feedback = true";
        try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("COUNT");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }
    
    public String topNASCustomer(){
        String SQL = "SELECT COUNT(ID), customerName FROM allquotes WHERE tradeLane=? AND (IF(DATE_UPDATED = ?, DATE_QUOTED >= ?, DATE_UPDATED >= ?) AND IF(DATE_UPDATED = ?, DATE_QUOTED <= ?, DATE_UPDATED <= ?)) GROUP BY customerName ORDER BY COUNT(ID) DESC LIMIT 1";
        try{
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, "North Atlantic Shuttle EB");
            ps.setString(2, "0000-00-00 00:00");
            ps.setString(3, new ReportingDates().lastWeek());
            ps.setString(4, new ReportingDates().lastWeek());
            ps.setString(5, "0000-00-00 00:00");
            ps.setString(6, new ReportingDates().firstOfCurrentWeek());
            ps.setString(7, new ReportingDates().firstOfCurrentWeek());
             rs = ps.executeQuery();
            if(rs.next()){
                name = rs.getString("customerName");
            }else{
                name = "N/A";
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());            
        }
        return name;
    }
    
    public String topTALCustomer(){
        String SQL = "SELECT COUNT(ID), customerName FROM allquotes WHERE tradeLane=? AND (IF(DATE_UPDATED = ?, DATE_QUOTED >= ?, DATE_UPDATED >= ?) AND IF(DATE_UPDATED = ?, DATE_QUOTED <= ?, DATE_UPDATED <= ?)) GROUP BY customerName ORDER BY COUNT(ID) DESC LIMIT 1";
        try{
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, "Trans-Atlantic EB");
            ps.setString(2, "0000-00-00 00:00");
            ps.setString(3, new ReportingDates().lastWeek());
            ps.setString(4, new ReportingDates().lastWeek());
            ps.setString(5, "0000-00-00 00:00");
            ps.setString(6, new ReportingDates().firstOfCurrentWeek());
            ps.setString(7, new ReportingDates().firstOfCurrentWeek());
             rs = ps.executeQuery();
            if(rs.next()){
                name = rs.getString("customerName");
            }else{
                name = "N/A";
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());            
        }
        return name;
    }
    
    public String topECAMSCustomer(){
        String SQL = "SELECT COUNT(ID), customerName FROM allquotes WHERE tradeLane=? AND (IF(DATE_UPDATED = ?, DATE_QUOTED >= ?, DATE_UPDATED >= ?) AND IF(DATE_UPDATED = ?, DATE_QUOTED <= ?, DATE_UPDATED <= ?)) GROUP BY customerName ORDER BY COUNT(ID) DESC LIMIT 1";
        try{
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, "ECAMS SB");
            ps.setString(2, "0000-00-00 00:00");
            ps.setString(3, new ReportingDates().lastWeek());
            ps.setString(4, new ReportingDates().lastWeek());
            ps.setString(5, "0000-00-00 00:00");
            ps.setString(6, new ReportingDates().firstOfCurrentWeek());
            ps.setString(7, new ReportingDates().firstOfCurrentWeek());
             rs = ps.executeQuery();
            if(rs.next()){
                name = rs.getString("customerName");
            }else{
                name = "N/A";
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());            
        }
        return name;
    }
    
    public String topNAXCustomer(){
        String SQL = "SELECT COUNT(ID), customerName FROM allquotes WHERE tradeLane=? AND (IF(DATE_UPDATED = ?, DATE_QUOTED >= ?, DATE_UPDATED >= ?) AND IF(DATE_UPDATED = ?, DATE_QUOTED <= ?, DATE_UPDATED <= ?)) GROUP BY customerName ORDER BY COUNT(ID) DESC LIMIT 1";
        try{
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, "NAX WB");
            ps.setString(2, "0000-00-00 00:00");
            ps.setString(3, new ReportingDates().lastWeek());
            ps.setString(4, new ReportingDates().lastWeek());
            ps.setString(5, "0000-00-00 00:00");
            ps.setString(6, new ReportingDates().firstOfCurrentWeek());
            ps.setString(7, new ReportingDates().firstOfCurrentWeek());
             rs = ps.executeQuery();
            if(rs.next()){
                name = rs.getString("customerName");
                System.out.println(name);
            }else{
                name = "N/A";
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return name;
    }
}
