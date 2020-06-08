package edu.depaul.g6.commons;

/**
 * @author nardos
 * Currently not used.
 * Sources:
 * (1) https://en.wikipedia.org/wiki/Street_suffix
 * (2) https://pe.usps.com/text/pub28/28apc_003.htm#ep538629
 */
public class USStreets {
    private final static String[] STREET_SUFFIXES = new String[] {"Alley","Annex","Apartment","Approach","Arcade","Avenue","Barrel","Bay","Bayou","Beach","Bend","Bluff","Bottom","Boulevard","Box","Bow","Branch","Bridge","Brook","Burg","Bypass","Camp","Can","Canoe","Canyon","Cape","Cartway","Causeway","Center","Chain","Circle","Cliff","Club","Cobble","Common","Condos","Corns","Corner","Cottage","Course","Court","Cove","Creek","Crescent","Crest","Crosscut","Crossing","Crossroad","Curve","Dale","Dam","Divide","Drive(Dr","Driveway(Dwy","East","Edge","Estate","Expressway","Extension","Fall","Fence","Ferry","Field","Flat","Ford","Forest","Forge","Fork","Fort","Freeway","Front","Garden","Gate","Gateway","Glass","Glen","Green","Grove","Harbor","Haven","Heights","Highlands","Highway","Hill","Hollow","Horn","Inlet","Island","Isle","Joist","Jump","Junction","Kame","Key","Knoll","Lake","Land","Landing","Lane","Lanterns","Lawn","Latch","Leaf","Light","Loaf","Lock","Lodge","Loop","Mall","Manor","Meadow","Meadows","Mews","Mill","Mission","Motorway","Mount","Mountain","Neck","Nene","Nook","North","Northeast","Northwest","Nvnohi","Orchard","Oval","Overpass","Park","Parkway","Pass","Passage","Passway","Pasture","Path","Perch","Pike","Pine","Pipes","Place","Plain","Plate","Plaza","Point","Pointe","Port","Post","Prairie","Radial","Ramp","Ranch","Rapid","Reach","Rear","Rest","Ridge","Rise","River","Road","Rock","Route","Row","Rue","Ruelle","Run","Shale","Shoal","Shore","Skies","Skyway","Sorrel","South","Southeast","Southwest","Sprig","Spring","Spur","Square","Station","Stick","Stone","Stravenue","Stream","Street","Stroll","Summit","Terrace","Trellis","Throughway","Throw","Trace","Track","Trafficway","Trail","Trailer","Tunnel","Turnpike","Underpass","Union","Vale","Valley","Viaduct","View","Villa","Village","Ville","Vista","Walk","Wall","Way","Waye","Well","Wells","West","Wind","Wood","Wharf","Yard"};
    private static final String[][] UNIT_DESIGNATORS = new String[][] {
            { "APT", "Apartment" },
            { "BSMT", "Basement" },
            { "BLDG", "Building" },
            { "DEPT", "Department" },
            { "FL", "Floor" },
            { "FRNT", "Front" },
            { "HNGR", "Hanger" },
            { "KEY", "Key" },
            { "LBBY", "Lobby" },
            { "LOT", "Lot" },
            { "LOWR", "Lower" },
            { "OFC", "Office" },
            { "PH", "Penthouse" },
            { "PIER", "Pier" },
            { "REAR", "Rear" },
            { "RM", "Room" },
            { "SIDE", "Side" },
            { "SLIP", "Slip" },
            { "SPC", "Space" },
            { "STOP", "Stop" },
            { "STE", "Suite" },
            { "TRLR", "Trailer" },
            { "UNIT", "Unit" },
            { "UPPR", "Upper" }
    };
}
