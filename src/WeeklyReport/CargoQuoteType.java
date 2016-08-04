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
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author cmeehan
 */
public class CargoQuoteType {
    private final Connection CONN = new DBConnection().Connect();
    private final Map<Double, String> RESULT_MAP = new HashMap<>();
    private final Map<Double , Map<String, String>> DOUBLE_MAP = new HashMap<>();
  
    public Map<Double, Map<String, String>> bookingsByTradeLane(){
        String SQL = "SELECT ROUND(SUM(packinglist.cbm)*packinglist.quantity,3) AS 'Total CBM', allquotes.tradeLane AS 'Trade Lane', allquotes.customerName  AS 'Customer Name' FROM allquotes JOIN packinglist ON packinglist.quoteID = allquotes.ID JOIN rorocustomers ON rorocustomers.company = allquotes.customerName WHERE allquotes.booked = ? AND (IF(DATE_UPDATED = ?, DATE_QUOTED >=?, DATE_UPDATED >= ?) AND IF(DATE_UPDATED = ?, DATE_QUOTED <= ?, DATE_UPDATED <= ?)) AND (rorocustomers.region = ? OR rorocustomers.region = ? OR rorocustomers.region = ? OR rorocustomers.region = ?) GROUP BY allquotes.customerName";
        try{
            PreparedStatement ps = CONN.prepareStatement(SQL);
            ps.setBoolean(1, true);
            ps.setString(2, "0000-00-00 00:00");
            ps.setString(3, new ReportingDates().lastWeek());
            ps.setString(4, new ReportingDates().lastWeek());
            ps.setString(5, "0000-00-00 00:00");
            ps.setString(6, new ReportingDates().firstOfCurrentWeek());
            ps.setString(7, new ReportingDates().firstOfCurrentWeek());
            ps.setString(8, "North East");
            ps.setString(9, "South East");
            ps.setString(10, "Mid West");
            ps.setString(11, "West");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                DOUBLE_MAP.put(rs.getDouble("Total CBM"),new HashMap(){{put(rs.getString("Trade Lane"), rs.getString("Customer Name"));}});
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return DOUBLE_MAP;
    }    
    
    public Map<Double, Map<String, String>> bookingsByPOD(){
        String SQL = "SELECT ROUND(SUM(packinglist.cbm)*packinglist.quantity,3) AS 'Total CBM', allquotes.pod AS 'POD', allquotes.customerName  AS 'Customer Name' FROM allquotes JOIN packinglist ON packinglist.quoteID = allquotes.ID JOIN rorocustomers ON rorocustomers.company = allquotes.customerName WHERE allquotes.booked = ? AND (IF(DATE_UPDATED = ?, DATE_QUOTED >=?, DATE_UPDATED >= ?) AND IF(DATE_UPDATED = ?, DATE_QUOTED <= ?, DATE_UPDATED <= ?)) AND (rorocustomers.region = ? OR rorocustomers.region = ? OR rorocustomers.region = ? OR rorocustomers.region = ?) GROUP BY allquotes.pod";
        try{
            PreparedStatement ps = CONN.prepareStatement(SQL);
            ps.setBoolean(1, true);
            ps.setString(2, "0000-00-00 00:00");
            ps.setString(3, new ReportingDates().lastWeek());
            ps.setString(4, new ReportingDates().lastWeek());
            ps.setString(5, "0000-00-00 00:00");
            ps.setString(6, new ReportingDates().firstOfCurrentWeek());
            ps.setString(7, new ReportingDates().firstOfCurrentWeek());
            ps.setString(8, "North East");
            ps.setString(9, "South East");
            ps.setString(10, "Mid West");
            ps.setString(11, "West");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                DOUBLE_MAP.put(rs.getDouble("Total CBM"),new HashMap(){{put(rs.getString("POD"), rs.getString("Customer Name"));}});
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return DOUBLE_MAP;
    }
    
    public Map<Double, String> declinesByReason(){
        String SQL = "SELECT (SUM(packinglist.cbm) * packinglist.quantity) AS 'Total CBM', allquotes.reason_for_decline AS 'Reason For Decline' FROM allquotes JOIN packinglist ON packinglist.quoteID = allquotes.ID WHERE allquotes.deny = ? AND (IF(DATE_UPDATED = ?, DATE_QUOTED >=?, DATE_UPDATED >= ?) AND IF(DATE_UPDATED = ?, DATE_QUOTED <= ?, DATE_UPDATED <= ?)) AND allquotes.tradeLane != ? GROUP BY allquotes.reason_for_decline";
        try{
            PreparedStatement ps = CONN.prepareStatement(SQL);
            ps.setBoolean(1, true);
            ps.setString(2, "0000-00-00 00:00");
            ps.setString(3, new ReportingDates().lastWeek());
            ps.setString(4, new ReportingDates().lastWeek());
            ps.setString(5, "0000-00-00 00:00");
            ps.setString(6, new ReportingDates().firstOfCurrentWeek());
            ps.setString(7, new ReportingDates().firstOfCurrentWeek());
            ps.setString(8, "N/A");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                RESULT_MAP.put(rs.getDouble("Total CBM"), rs.getString("Reason For Decline"));
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return RESULT_MAP;
    }
    
    public Map<Double,Map<String, String>> declinesByCommClass(){
        String SQL = "SELECT (SUM(packinglist.cbm) * packinglist.quantity) AS 'Total CBM',allquotes.tradeLane AS 'Trade Lane', allquotes.comm_class AS 'Commodity Class' FROM allquotes JOIN packinglist ON packinglist.quoteID = allquotes.ID WHERE allquotes.deny = ? AND (IF(DATE_UPDATED = ?, DATE_QUOTED >=?, DATE_UPDATED >= ?) AND IF(DATE_UPDATED = ?, DATE_QUOTED <= ?, DATE_UPDATED <= ?))GROUP BY allquotes.reason_for_decline";
        try{
            PreparedStatement ps = CONN.prepareStatement(SQL);
            ps.setBoolean(1, true);
            ps.setString(2, "0000-00-00 00:00");
            ps.setString(3, new ReportingDates().lastWeek());
            ps.setString(4, new ReportingDates().lastWeek());
            ps.setString(5, "0000-00-00 00:00");
            ps.setString(6, new ReportingDates().firstOfCurrentWeek());
            ps.setString(7, new ReportingDates().firstOfCurrentWeek());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                DOUBLE_MAP.put(rs.getDouble("Total CBM"), new HashMap(){{put(rs.getString("Trade Lane"),rs.getString("Commodity Class"));}});
            }
        }catch(Exception ex){
            System.out.println("Error: "+ex.getMessage());
        }
        return DOUBLE_MAP;        
    }
    
}
