package shu.xai.Descriptors.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@lombok.NoArgsConstructor
@lombok.Data
@Document("MlData")
public class MlData {
    @Id
    @JsonProperty("id")
    private ObjectId id;
    private Integer ICSD;
    private String ChemicalFormula;
    @Field("Occu_6b")
    @JsonProperty("Occu_6b")
    private Double Occu_6b;
    @Field("Occu_18e")
    @JsonProperty("Occu_18e")
    private Double occu18e;
    @Field("Occu_36f")
    @JsonProperty("Occu_36f")
    private Integer occu36f;
    @Field("C_Na")
    @JsonProperty("C_Na")
    private Integer cNa;
    @Field("Occu_M1")
    @JsonProperty("Occu_M1")
    private Double occuM1;
    @Field("Occu_M2")
    @JsonProperty("Occu_M2")
    private Double occuM2;
    @Field("EN_M1")
    @JsonProperty("EN_M1")
    private Double enM1;
    @Field("EN_M2")
    @JsonProperty("EN_M2")
    private Double enM2;
    @Field("avg_EN_M")
    @JsonProperty("avg_EN_M")
    private Double avgEnM;
    @Field("Radius_M1")
    @JsonProperty("Radius_M1")
    private Double radiusM1;
    @Field("Radius_M2")
    @JsonProperty("Radius_M2")
    private Double radiusM2;
    @Field("avg_Radius_M")
    @JsonProperty("avg_Radius_M")
    private Double avgRadiusM;
    @Field("Valence_M1")
    @JsonProperty("Valence_M1")
    private Integer valenceM1;
    @Field("Valence_M2")
    @JsonProperty("Valence_M2")
    private Integer valenceM2;
    @Field("avg_Valence_M")
    @JsonProperty("avg_Valence_M")
    private Double avgValenceM;
    @Field("Occu_X1")
    @JsonProperty("Occu_X1")
    private Integer occuX1;
    @Field("Occu_X2")
    @JsonProperty("Occu_X2")
    private Integer occuX2;
    @Field("EN_X1")
    @JsonProperty("EN_X1")
    private Double enX1;
    @Field("EN_X2")
    @JsonProperty("EN_X2")
    private Integer enX2;
    @Field("avg_EN_X")
    @JsonProperty("avg_EN_X")
    private Double avgEnX;
    @Field("Radius_X1")
    @JsonProperty("Radius_X1")
    private Double radiusX1;
    @Field("Radius_X2")
    @JsonProperty("Radius_X2")
    private Integer radiusX2;
    @Field("avg_Radius_X")
    @JsonProperty("avg_Radius_X")
    private Double avgRadiusX;
    @Field("Valence_X1")
    @JsonProperty("Valence_X1")
    private Integer valenceX1;
    @Field("Valence_X2")
    @JsonProperty("Valence_X2")
    private Integer valenceX2;
    @Field("avg_Valence_X")
    @JsonProperty("avg_Valence_X")
    private Integer avgValenceX;
    @Field("a")
    @JsonProperty("a")
    private Double a;
    @Field("c")
    @JsonProperty("c")
    private Double c;
    @Field("V")
    @JsonProperty("V")
    private Double v;
    @Field("V_MO6")
    @JsonProperty("V_MO6")
    private Double vMo6;
    @Field("V_XO4")
    @JsonProperty("V_XO4")
    private Double vXo4;
    @Field("V_Na(1)O6")
    @JsonProperty("V_Na(1)O6")
    private Double V_Na1O672;// FIXME check this code
    @Field("V_Na(2)O8")
    @JsonProperty("V_Na(2)O8")
    private Double _$VNa2O8287;// FIXME check this code
    @Field("V_Na(3)O5" )
    @JsonProperty("V_Na(3)O5")
    private Double _$VNa3O557;// FIXME check this code
    @Field("BT2")
    @JsonProperty("BT2")
    private Double bt2;
    @Field("BT1")
    @JsonProperty("BT1")
    private Double bt1;
    @Field("Min_BT")
    @JsonProperty("Min_BT")
    private Double minBt;
    @Field("RT")
    @JsonProperty("RT")
    private Double rt;
    @Field("Entropy_6b")
    @JsonProperty("Entropy_6b")
    private Double entropy6b;
    @Field("Entropy_18e")
    @JsonProperty("Entropy_18e")
    private Double entropy18e;
    @Field("Entropy_36f")
    @JsonProperty("Entropy_36f")
    private Integer entropy36f;
    @Field("Entropy_Na")
    @JsonProperty("Entropy_Na")
    private Double entropyNa;
    @Field("Entropy_M")
    @JsonProperty("Entropy_M")
    private Double entropyM;
    @Field("Entropy_X")
    @JsonProperty("Entropy_X")
    private Integer entropyX;
    @Field("T")
    @JsonProperty("T")
    private Integer T;
    @Field("BVSE energy barrier ")
    @JsonProperty("BVSE energy barrier ")
    private Double BVSEEnergyBarrier;// FIXME check this code
}
