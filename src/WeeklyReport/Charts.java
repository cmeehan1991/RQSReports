/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WeeklyReport;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author cmeehan
 */
public class Charts {
    protected JFreeChart pieChart(){
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Automobile/POVs (New & Used)", new Double(new CargoTypeData().automobiles()));
        dataset.setValue("Construction Equipment", new Double(new CargoTypeData().construction()));
        dataset.setValue("Trucks, Busses, Vans, & Chassis", new Double(new CargoTypeData().trucks()));
        dataset.setValue("Agricultural Equipment & Tractors", new Double(new CargoTypeData().agricultural()));
        dataset.setValue("Boats on Trailer", new Double(new CargoTypeData().BT()));
        dataset.setValue("Boats on Cradle", new Double(new CargoTypeData().BC()));
        dataset.setValue("Static Cargo (Forklift & MAFI)", new Double(new CargoTypeData().staticCargo()));
        
        JFreeChart chart = ChartFactory.createPieChart("Quotes by Commodity Class",dataset,true,true,false);
        return chart;
    }
}
