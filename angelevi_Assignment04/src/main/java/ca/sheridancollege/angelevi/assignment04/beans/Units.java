package ca.sheridancollege.angelevi.assignment04.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
/**
 * Units for measuring cheese.
 * 
 * @author Wendi Jollymore
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Units {
    
    /**
     * The unique ID for this unit.
     * 
     * @param the ID for this unit
     * @return the unique ID for this unit
     */
    private int id;  // unique ID aka primary key
    
    /**
     * The name or description of this unit of measurement.
     * 
     * @param the new units description
     * @return this unit's description
     */
    private String description; // description of the units e.g. Lb. Wheel
    
    //toString for the Units so it will look better inside the dropbox
    public String toString() {
    	return " " + description;
    }
}