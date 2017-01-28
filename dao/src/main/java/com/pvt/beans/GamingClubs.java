package com.pvt.beans;

/**
 *
 */
public enum GamingClubs {
    BRVGAGARINA(1),
    BRVREVOLUTSII(2),
    BRNFROLENKOVA(3),
    GMLKISILEVA(4),
    GRDANTONOVA(5),
    GRDKLETSKOVA(6),
    ZHDNMIRA(7),
    LIDSOVETSKAYA(8),
    MGLPERVOMAJSKAYA(9),
    MLDPRITICKOGO(10),
    MNSKUKURUZA(11),
    MNSKOKETKA(12),
    MNSPRITICKOGO(13),
    MNSPROSTOR(15),
    MNSTURIST(14),
    MNSCHKALOVA(16),
    MNSODINCOVA(17),
    SVTLENINA(18);


    private final int id;

    GamingClubs(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
