package it.wsda.converters;

import it.wsda.dto.FacilityDTO;
import org.json.JSONObject;

import java.beans.PropertyEditorSupport;

public class FacilityDTOEditor extends PropertyEditorSupport {
    @Override
    public String getAsText() {
        var facilityDTO = (FacilityDTO) getValue();
        return facilityDTO.toJSON().toString();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        var json = new JSONObject(text);
        setValue(FacilityDTO.fromJSON(json));
    }
}
