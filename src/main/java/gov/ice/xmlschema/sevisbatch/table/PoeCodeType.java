//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.25 at 10:25:22 AM CST 
//


package gov.ice.xmlschema.sevisbatch.table;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PoeCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PoeCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ABG"/>
 *     &lt;enumeration value="ABQ"/>
 *     &lt;enumeration value="ABS"/>
 *     &lt;enumeration value="ADT"/>
 *     &lt;enumeration value="AGA"/>
 *     &lt;enumeration value="AGN"/>
 *     &lt;enumeration value="ALB"/>
 *     &lt;enumeration value="ALC"/>
 *     &lt;enumeration value="AMB"/>
 *     &lt;enumeration value="ANC"/>
 *     &lt;enumeration value="AND"/>
 *     &lt;enumeration value="ANT"/>
 *     &lt;enumeration value="ARB"/>
 *     &lt;enumeration value="AST"/>
 *     &lt;enumeration value="ATL"/>
 *     &lt;enumeration value="AUS"/>
 *     &lt;enumeration value="BAL"/>
 *     &lt;enumeration value="BAU"/>
 *     &lt;enumeration value="BBM"/>
 *     &lt;enumeration value="BEB"/>
 *     &lt;enumeration value="BEE"/>
 *     &lt;enumeration value="BEL"/>
 *     &lt;enumeration value="BGM"/>
 *     &lt;enumeration value="BLA"/>
 *     &lt;enumeration value="BOA"/>
 *     &lt;enumeration value="BOS"/>
 *     &lt;enumeration value="BRG"/>
 *     &lt;enumeration value="BRO"/>
 *     &lt;enumeration value="BTN"/>
 *     &lt;enumeration value="BWA"/>
 *     &lt;enumeration value="BWM"/>
 *     &lt;enumeration value="BYO"/>
 *     &lt;enumeration value="CAL"/>
 *     &lt;enumeration value="CHA"/>
 *     &lt;enumeration value="CHF"/>
 *     &lt;enumeration value="CHI"/>
 *     &lt;enumeration value="CHL"/>
 *     &lt;enumeration value="CHM"/>
 *     &lt;enumeration value="CHR"/>
 *     &lt;enumeration value="CHT"/>
 *     &lt;enumeration value="CIN"/>
 *     &lt;enumeration value="CLE"/>
 *     &lt;enumeration value="CLG"/>
 *     &lt;enumeration value="CLM"/>
 *     &lt;enumeration value="CLS"/>
 *     &lt;enumeration value="CLT"/>
 *     &lt;enumeration value="CNA"/>
 *     &lt;enumeration value="CNJ"/>
 *     &lt;enumeration value="COB"/>
 *     &lt;enumeration value="COL"/>
 *     &lt;enumeration value="COO"/>
 *     &lt;enumeration value="COW"/>
 *     &lt;enumeration value="CRP"/>
 *     &lt;enumeration value="CRU"/>
 *     &lt;enumeration value="CRY"/>
 *     &lt;enumeration value="DAC"/>
 *     &lt;enumeration value="DAL"/>
 *     &lt;enumeration value="DCB"/>
 *     &lt;enumeration value="DCT"/>
 *     &lt;enumeration value="DEN"/>
 *     &lt;enumeration value="DER"/>
 *     &lt;enumeration value="DET"/>
 *     &lt;enumeration value="DLB"/>
 *     &lt;enumeration value="DLR"/>
 *     &lt;enumeration value="DLV"/>
 *     &lt;enumeration value="DNS"/>
 *     &lt;enumeration value="DOU"/>
 *     &lt;enumeration value="DTH"/>
 *     &lt;enumeration value="DUL"/>
 *     &lt;enumeration value="DVD"/>
 *     &lt;enumeration value="DVL"/>
 *     &lt;enumeration value="EAS"/>
 *     &lt;enumeration value="EDA"/>
 *     &lt;enumeration value="EGP"/>
 *     &lt;enumeration value="EPI"/>
 *     &lt;enumeration value="EPM"/>
 *     &lt;enumeration value="ERC"/>
 *     &lt;enumeration value="EST"/>
 *     &lt;enumeration value="FAB"/>
 *     &lt;enumeration value="FAL"/>
 *     &lt;enumeration value="FBA"/>
 *     &lt;enumeration value="FER"/>
 *     &lt;enumeration value="FMY"/>
 *     &lt;enumeration value="FOR"/>
 *     &lt;enumeration value="FRB"/>
 *     &lt;enumeration value="FRT"/>
 *     &lt;enumeration value="FTC"/>
 *     &lt;enumeration value="FTF"/>
 *     &lt;enumeration value="FTH"/>
 *     &lt;enumeration value="FTK"/>
 *     &lt;enumeration value="FWA"/>
 *     &lt;enumeration value="GAL"/>
 *     &lt;enumeration value="GPM"/>
 *     &lt;enumeration value="GRR"/>
 *     &lt;enumeration value="GUL"/>
 *     &lt;enumeration value="HAM"/>
 *     &lt;enumeration value="HAR"/>
 *     &lt;enumeration value="HEL"/>
 *     &lt;enumeration value="HHW"/>
 *     &lt;enumeration value="HID"/>
 *     &lt;enumeration value="HIG"/>
 *     &lt;enumeration value="HML"/>
 *     &lt;enumeration value="HNN"/>
 *     &lt;enumeration value="HNS"/>
 *     &lt;enumeration value="HOU"/>
 *     &lt;enumeration value="HTM"/>
 *     &lt;enumeration value="INP"/>
 *     &lt;enumeration value="INT"/>
 *     &lt;enumeration value="IVP"/>
 *     &lt;enumeration value="JAC"/>
 *     &lt;enumeration value="JKM"/>
 *     &lt;enumeration value="KAN"/>
 *     &lt;enumeration value="KET"/>
 *     &lt;enumeration value="KEY"/>
 *     &lt;enumeration value="KOA"/>
 *     &lt;enumeration value="LAN"/>
 *     &lt;enumeration value="LAR"/>
 *     &lt;enumeration value="LAU"/>
 *     &lt;enumeration value="LCB"/>
 *     &lt;enumeration value="LIM"/>
 *     &lt;enumeration value="LKC"/>
 *     &lt;enumeration value="LLB"/>
 *     &lt;enumeration value="LOI"/>
 *     &lt;enumeration value="LON"/>
 *     &lt;enumeration value="LOS"/>
 *     &lt;enumeration value="LOU"/>
 *     &lt;enumeration value="LSE"/>
 *     &lt;enumeration value="LUB"/>
 *     &lt;enumeration value="LUK"/>
 *     &lt;enumeration value="LVG"/>
 *     &lt;enumeration value="LYN"/>
 *     &lt;enumeration value="MAD"/>
 *     &lt;enumeration value="MAG"/>
 *     &lt;enumeration value="MAI"/>
 *     &lt;enumeration value="MAP"/>
 *     &lt;enumeration value="MAS"/>
 *     &lt;enumeration value="MAY"/>
 *     &lt;enumeration value="MEM"/>
 *     &lt;enumeration value="MET"/>
 *     &lt;enumeration value="MGM"/>
 *     &lt;enumeration value="MIA"/>
 *     &lt;enumeration value="MIL"/>
 *     &lt;enumeration value="MND"/>
 *     &lt;enumeration value="MOB"/>
 *     &lt;enumeration value="MON"/>
 *     &lt;enumeration value="MOO"/>
 *     &lt;enumeration value="MOR"/>
 *     &lt;enumeration value="MRC"/>
 *     &lt;enumeration value="MSP"/>
 *     &lt;enumeration value="MTC"/>
 *     &lt;enumeration value="NAC"/>
 *     &lt;enumeration value="NAS"/>
 *     &lt;enumeration value="NEC"/>
 *     &lt;enumeration value="NEW"/>
 *     &lt;enumeration value="NIA"/>
 *     &lt;enumeration value="NIG"/>
 *     &lt;enumeration value="NOG"/>
 *     &lt;enumeration value="NOL"/>
 *     &lt;enumeration value="NOM"/>
 *     &lt;enumeration value="NOO"/>
 *     &lt;enumeration value="NOR"/>
 *     &lt;enumeration value="NOY"/>
 *     &lt;enumeration value="NPT"/>
 *     &lt;enumeration value="NRG"/>
 *     &lt;enumeration value="NRN"/>
 *     &lt;enumeration value="NRT"/>
 *     &lt;enumeration value="NSV"/>
 *     &lt;enumeration value="NYC"/>
 *     &lt;enumeration value="OGD"/>
 *     &lt;enumeration value="OKC"/>
 *     &lt;enumeration value="OMA"/>
 *     &lt;enumeration value="OPH"/>
 *     &lt;enumeration value="ORI"/>
 *     &lt;enumeration value="ORL"/>
 *     &lt;enumeration value="ORO"/>
 *     &lt;enumeration value="OTM"/>
 *     &lt;enumeration value="OTT"/>
 *     &lt;enumeration value="PAN"/>
 *     &lt;enumeration value="PAR"/>
 *     &lt;enumeration value="PBB"/>
 *     &lt;enumeration value="PCF"/>
 *     &lt;enumeration value="PDN"/>
 *     &lt;enumeration value="PEM"/>
 *     &lt;enumeration value="PEV"/>
 *     &lt;enumeration value="PGR"/>
 *     &lt;enumeration value="PHI"/>
 *     &lt;enumeration value="PHO"/>
 *     &lt;enumeration value="PHR"/>
 *     &lt;enumeration value="PHU"/>
 *     &lt;enumeration value="PHY"/>
 *     &lt;enumeration value="PIB"/>
 *     &lt;enumeration value="PIE"/>
 *     &lt;enumeration value="PIN"/>
 *     &lt;enumeration value="PIT"/>
 *     &lt;enumeration value="PIV"/>
 *     &lt;enumeration value="PKC"/>
 *     &lt;enumeration value="PNG"/>
 *     &lt;enumeration value="PNH"/>
 *     &lt;enumeration value="POM"/>
 *     &lt;enumeration value="PON"/>
 *     &lt;enumeration value="POO"/>
 *     &lt;enumeration value="POR"/>
 *     &lt;enumeration value="PRE"/>
 *     &lt;enumeration value="PRO"/>
 *     &lt;enumeration value="PTL"/>
 *     &lt;enumeration value="PTR"/>
 *     &lt;enumeration value="RAY"/>
 *     &lt;enumeration value="RDU"/>
 *     &lt;enumeration value="REN"/>
 *     &lt;enumeration value="RIF"/>
 *     &lt;enumeration value="RIO"/>
 *     &lt;enumeration value="ROM"/>
 *     &lt;enumeration value="ROO"/>
 *     &lt;enumeration value="ROS"/>
 *     &lt;enumeration value="ROU"/>
 *     &lt;enumeration value="SAJ"/>
 *     &lt;enumeration value="SAR"/>
 *     &lt;enumeration value="SAS"/>
 *     &lt;enumeration value="SAU"/>
 *     &lt;enumeration value="SAV"/>
 *     &lt;enumeration value="SCO"/>
 *     &lt;enumeration value="SDP"/>
 *     &lt;enumeration value="SDY"/>
 *     &lt;enumeration value="SEA"/>
 *     &lt;enumeration value="SET"/>
 *     &lt;enumeration value="SFB"/>
 *     &lt;enumeration value="SFR"/>
 *     &lt;enumeration value="SHR"/>
 *     &lt;enumeration value="SJO"/>
 *     &lt;enumeration value="SKA"/>
 *     &lt;enumeration value="SLC"/>
 *     &lt;enumeration value="SLU"/>
 *     &lt;enumeration value="SNA"/>
 *     &lt;enumeration value="SND"/>
 *     &lt;enumeration value="SNJ"/>
 *     &lt;enumeration value="SNN"/>
 *     &lt;enumeration value="SPA"/>
 *     &lt;enumeration value="SPE"/>
 *     &lt;enumeration value="SPM"/>
 *     &lt;enumeration value="SPO"/>
 *     &lt;enumeration value="SRL"/>
 *     &lt;enumeration value="SSB"/>
 *     &lt;enumeration value="SSM"/>
 *     &lt;enumeration value="STL"/>
 *     &lt;enumeration value="STR"/>
 *     &lt;enumeration value="STT"/>
 *     &lt;enumeration value="SUM"/>
 *     &lt;enumeration value="SWE"/>
 *     &lt;enumeration value="SYS"/>
 *     &lt;enumeration value="TAC"/>
 *     &lt;enumeration value="TAM"/>
 *     &lt;enumeration value="TEC"/>
 *     &lt;enumeration value="THO"/>
 *     &lt;enumeration value="TOL"/>
 *     &lt;enumeration value="TOR"/>
 *     &lt;enumeration value="TRO"/>
 *     &lt;enumeration value="TUC"/>
 *     &lt;enumeration value="TUR"/>
 *     &lt;enumeration value="VCB"/>
 *     &lt;enumeration value="VCV"/>
 *     &lt;enumeration value="VIC"/>
 *     &lt;enumeration value="VNB"/>
 *     &lt;enumeration value="WAL"/>
 *     &lt;enumeration value="WAR"/>
 *     &lt;enumeration value="WAS"/>
 *     &lt;enumeration value="WBE"/>
 *     &lt;enumeration value="WCM"/>
 *     &lt;enumeration value="WHI"/>
 *     &lt;enumeration value="WHM"/>
 *     &lt;enumeration value="WHO"/>
 *     &lt;enumeration value="WIL"/>
 *     &lt;enumeration value="WIN"/>
 *     &lt;enumeration value="WND"/>
 *     &lt;enumeration value="WPB"/>
 *     &lt;enumeration value="YSL"/>
 *     &lt;enumeration value="YUI"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PoeCodeType", namespace = "http://www.ice.gov/xmlschema/sevisbatch/Table")
@XmlEnum
public enum PoeCodeType {

    ABG,
    ABQ,
    ABS,
    ADT,
    AGA,
    AGN,
    ALB,
    ALC,
    AMB,
    ANC,
    AND,
    ANT,
    ARB,
    AST,
    ATL,
    AUS,
    BAL,
    BAU,
    BBM,
    BEB,
    BEE,
    BEL,
    BGM,
    BLA,
    BOA,
    BOS,
    BRG,
    BRO,
    BTN,
    BWA,
    BWM,
    BYO,
    CAL,
    CHA,
    CHF,
    CHI,
    CHL,
    CHM,
    CHR,
    CHT,
    CIN,
    CLE,
    CLG,
    CLM,
    CLS,
    CLT,
    CNA,
    CNJ,
    COB,
    COL,
    COO,
    COW,
    CRP,
    CRU,
    CRY,
    DAC,
    DAL,
    DCB,
    DCT,
    DEN,
    DER,
    DET,
    DLB,
    DLR,
    DLV,
    DNS,
    DOU,
    DTH,
    DUL,
    DVD,
    DVL,
    EAS,
    EDA,
    EGP,
    EPI,
    EPM,
    ERC,
    EST,
    FAB,
    FAL,
    FBA,
    FER,
    FMY,
    FOR,
    FRB,
    FRT,
    FTC,
    FTF,
    FTH,
    FTK,
    FWA,
    GAL,
    GPM,
    GRR,
    GUL,
    HAM,
    HAR,
    HEL,
    HHW,
    HID,
    HIG,
    HML,
    HNN,
    HNS,
    HOU,
    HTM,
    INP,
    INT,
    IVP,
    JAC,
    JKM,
    KAN,
    KET,
    KEY,
    KOA,
    LAN,
    LAR,
    LAU,
    LCB,
    LIM,
    LKC,
    LLB,
    LOI,
    LON,
    LOS,
    LOU,
    LSE,
    LUB,
    LUK,
    LVG,
    LYN,
    MAD,
    MAG,
    MAI,
    MAP,
    MAS,
    MAY,
    MEM,
    MET,
    MGM,
    MIA,
    MIL,
    MND,
    MOB,
    MON,
    MOO,
    MOR,
    MRC,
    MSP,
    MTC,
    NAC,
    NAS,
    NEC,
    NEW,
    NIA,
    NIG,
    NOG,
    NOL,
    NOM,
    NOO,
    NOR,
    NOY,
    NPT,
    NRG,
    NRN,
    NRT,
    NSV,
    NYC,
    OGD,
    OKC,
    OMA,
    OPH,
    ORI,
    ORL,
    ORO,
    OTM,
    OTT,
    PAN,
    PAR,
    PBB,
    PCF,
    PDN,
    PEM,
    PEV,
    PGR,
    PHI,
    PHO,
    PHR,
    PHU,
    PHY,
    PIB,
    PIE,
    PIN,
    PIT,
    PIV,
    PKC,
    PNG,
    PNH,
    POM,
    PON,
    POO,
    POR,
    PRE,
    PRO,
    PTL,
    PTR,
    RAY,
    RDU,
    REN,
    RIF,
    RIO,
    ROM,
    ROO,
    ROS,
    ROU,
    SAJ,
    SAR,
    SAS,
    SAU,
    SAV,
    SCO,
    SDP,
    SDY,
    SEA,
    SET,
    SFB,
    SFR,
    SHR,
    SJO,
    SKA,
    SLC,
    SLU,
    SNA,
    SND,
    SNJ,
    SNN,
    SPA,
    SPE,
    SPM,
    SPO,
    SRL,
    SSB,
    SSM,
    STL,
    STR,
    STT,
    SUM,
    SWE,
    SYS,
    TAC,
    TAM,
    TEC,
    THO,
    TOL,
    TOR,
    TRO,
    TUC,
    TUR,
    VCB,
    VCV,
    VIC,
    VNB,
    WAL,
    WAR,
    WAS,
    WBE,
    WCM,
    WHI,
    WHM,
    WHO,
    WIL,
    WIN,
    WND,
    WPB,
    YSL,
    YUI;

    public String value() {
        return name();
    }

    public static PoeCodeType fromValue(String v) {
        return valueOf(v);
    }

}
