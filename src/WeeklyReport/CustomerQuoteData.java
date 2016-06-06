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
import java.util.Map;

/**
 *
 * @author cmeehan
 */
public class CustomerQuoteData {

    private Connection conn = new DBConnection().Connect();
    private int count;
    private ResultSet rs;
    private Map<String, String> names = new HashMap<>();

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

    
    
    protected Map<String, String> southeastQuotes() {
        String SQL = "SELECT customerName, COUNT(*) AS 'COUNT' FROM allquotes INNER JOIN rorocustomers ON allquotes.customerName = rorocustomers.company WHERE rorocustomers.region='South East' AND IF(DATE_UPDATED = '0000-00-00 00:00', DATE_QUOTED >= '" + new ReportingDates().lastWeek() + "', DATE_UPDATED >= '" + new ReportingDates().lastWeek() + "') AND IF(DATE_UPDATED = '0000-00-00 00:00', DATE_QUOTED <= '" + new ReportingDates().firstOfCurrentWeek() + "', DATE_UPDATED <= '" + new ReportingDates().firstOfCurrentWeek() + "') GROUP BY customerName";
        try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt("COUNT");
                names.put(rs.getString("customerName"), String.valueOf(count));
            }
        } catch (Exception ex) {
        }

        return names;
    }

    protected Map<String, String> northEastQuotes() {
        String SQL = "SELECT customerName, COUNT(*) AS 'COUNT' FROM allquotes INNER JOIN rorocustomers ON allquotes.customerName = rorocustomers.company WHERE rorocustomers.region='North East' AND IF(DATE_UPDATED = '0000-00-00 00:00', DATE_QUOTED >= '" + new ReportingDates().lastWeek() + "', DATE_UPDATED >= '" + new ReportingDates().lastWeek() + "') AND IF(DATE_UPDATED = '0000-00-00 00:00', DATE_QUOTED <= '" + new ReportingDates().firstOfCurrentWeek() + "', DATE_UPDATED <= '" + new ReportingDates().firstOfCurrentWeek() + "') GROUP BY customerName";
        try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt("COUNT");
                names.put(rs.getString("customerName"), String.valueOf(count));
            }
        } catch (Exception ex) {
        }

        return names;
    }

    protected Map<String, String> midWestQuotes() {
        String SQL = "SELECT customerName, COUNT(*) AS 'COUNT' FROM allquotes INNER JOIN rorocustomers ON allquotes.customerName = rorocustomers.company WHERE rorocustomers.region='Mid West' AND IF(DATE_UPDATED = '0000-00-00 00:00', DATE_QUOTED >= '" + new ReportingDates().lastWeek() + "', DATE_UPDATED >= '" + new ReportingDates().lastWeek() + "') AND IF(DATE_UPDATED = '0000-00-00 00:00', DATE_QUOTED <= '" + new ReportingDates().firstOfCurrentWeek() + "', DATE_UPDATED <= '" + new ReportingDates().firstOfCurrentWeek() + "') GROUP BY customerName";
        try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt("COUNT");
                names.put(rs.getString("customerName"), String.valueOf(count));
            }
        } catch (Exception ex) {
        }

        return names;
    }

    protected Map<String, String> westCoastQuotes() {
        String SQL = "SELECT customerName, COUNT(*) AS 'COUNT' FROM allquotes INNER JOIN rorocustomers ON allquotes.customerName = rorocustomers.company WHERE rorocustomers.region='West Coast' AND IF(DATE_UPDATED = '0000-00-00 00:00', DATE_QUOTED >= '" + new ReportingDates().lastWeek() + "', DATE_UPDATED >= '" + new ReportingDates().lastWeek() + "') AND IF(DATE_UPDATED = '0000-00-00 00:00', DATE_QUOTED <= '" + new ReportingDates().firstOfCurrentWeek() + "', DATE_UPDATED <= '" + new ReportingDates().firstOfCurrentWeek() + "') GROUP BY customerName";
        try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt("COUNT");
                names.put(rs.getString("customerName"), String.valueOf(count));
            }
        } catch (Exception ex) {
        }

        return names;
    }

    protected int declinedQuotes() {
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

    protected int bookedQuotes() {
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

    protected int pendingQuotes() {
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

    protected int feedbackQuotes() {
        String SQL = "SELECT COUNT(*) AS 'COUNT' FROM allquotes INNER JOIN rorocustomers ON allquotes.customerName = rorocustomers.company WHERE (rorocustomers.region='South East' OR  rorocustomers.region='North East' OR rorocustomers.region='Mid West' OR rorocustomers.region='West Coast') AND IF(DATE_UPDATED = '0000-00-00 00:00', DATE_QUOTED >= '" + new ReportingDates().lastWeek() + "', DATE_UPDATED >= '" + new ReportingDates().lastWeek() + "') AND IF(DATE_UPDATED = '0000-00-00 00:00', DATE_QUOTED <= '" + new ReportingDates().firstOfCurrentWeek() + "', DATE_UPDATED <= '" + new ReportingDates().firstOfCurrentWeek() + "') AND feedback = true";
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

}
