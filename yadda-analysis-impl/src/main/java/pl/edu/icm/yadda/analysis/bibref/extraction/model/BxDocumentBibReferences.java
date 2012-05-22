package pl.edu.icm.yadda.analysis.bibref.extraction.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pl.edu.icm.yadda.analysis.textr.model.BxLine;
import pl.edu.icm.yadda.analysis.textr.model.BxZone;

/**
 * Stores BxDocument's bibliographic references lines with their labels.
 *
 * @author Dominika Tkaczyk (d.tkaczyk@icm.edu.pl)
 */
public class BxDocumentBibReferences {

    /** A list of references' lines */
    private List<BxLine> lines = new ArrayList<BxLine>();

    /** A map associating references' line with their zones */
    private Map<BxLine, BxZone> lineZones = new HashMap<BxLine, BxZone>();

    /** A map associating references' lines with their labels */
    private Map<BxLine, BibReferenceLineLabel> lineLabels = new HashMap<BxLine, BibReferenceLineLabel>();

    public void addZone(BxZone zone) {
        for (BxLine line : zone.getLines()) {
            lines.add(line);
            lineZones.put(line, zone);
        }
    }

    public List<BxLine> getLines() {
        return lines;
    }

    public BxZone getZone(BxLine line) {
        return lineZones.get(line);
    }

    public BibReferenceLineLabel getLabel(BxLine line) {
        return lineLabels.get(line);
    }

    public void setLabel(BxLine line, BibReferenceLineLabel label) {
        lineLabels.put(line, label);
    }

}