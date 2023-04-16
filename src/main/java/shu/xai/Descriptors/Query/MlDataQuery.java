package shu.xai.Descriptors.Query;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


public class MlDataQuery {

    private Integer ICSD;
    private String ChemicalFormula;

//    对激活能BVSEEnergyBarrier的查询设置上限
    private Double upperLimitMaxEnergy;
//    对激活能BVSEEnergyBarrier的查询设置上限
    private Double lowerLimitEnergy;

    public Integer getICSD() {
        return ICSD;
    }

    public void setICSD(Integer ICSD) {
        this.ICSD = ICSD;
    }

    public String getChemicalFormula() {
        return ChemicalFormula;
    }

    public void setChemicalFormula(String chemicalFormula) {
        ChemicalFormula = chemicalFormula;
    }

    public Double getUpperLimitMaxEnergy() {
        return upperLimitMaxEnergy;
    }

    public void setUpperLimitMaxEnergy(Double upperLimitMaxEnergy) {
        this.upperLimitMaxEnergy = upperLimitMaxEnergy;
    }

    public Double getLowerLimitEnergy() {
        return lowerLimitEnergy;
    }

    public void setLowerLimitEnergy(Double lowerLimitEnergy) {
        this.lowerLimitEnergy = lowerLimitEnergy;
    }
}
