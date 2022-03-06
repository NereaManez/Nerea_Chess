package model;

import com.diogonunes.jcolor.Attribute;

/**
 * @author Nerea Mañez
 */
public enum PieceColor {
    BLACK(Attribute.TEXT_COLOR(0,0,0)),
    WHITE(Attribute.TEXT_COLOR(255,255,255));

    private Attribute color;

    /**
     * Create a new color from a piece
     *
     * @param color
     */
    PieceColor(Attribute color) {
        this.color = color;
    }

    /**
     * Return the rgb color
     *
     * @return attribute
     */
    public Attribute getAttribute() {
        return color;
    }

    /**
     * Return the next color
     *
     * @return a piece color
     */
    public PieceColor next() {
        return PieceColor.values()[((ordinal()+1)%PieceColor.values().length)];
    }
}
