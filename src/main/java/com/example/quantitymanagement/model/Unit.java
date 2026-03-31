/*
 * @author: Developer
 * version: 1.0
 */

package com.example.quantitymanagement.model;

public enum Unit {

    METER(MeasurementType.LENGTH, 1.0),
    CENTIMETER(MeasurementType.LENGTH, 0.01),
    INCH(MeasurementType.LENGTH, 0.0254),
    FOOT(MeasurementType.LENGTH, 0.3048),

    KILOGRAM(MeasurementType.WEIGHT, 1.0),
    GRAM(MeasurementType.WEIGHT, 0.001),

    LITER(MeasurementType.VOLUME, 1.0),
    MILLILITER(MeasurementType.VOLUME, 0.001),

    CELSIUS(MeasurementType.TEMPERATURE, 1.0) {
        @Override
        public double toBase(double value) {
            return value; // base = Celsius
        }

        @Override
        public double fromBase(double value) {
            return value;
        }
    },

    FAHRENHEIT(MeasurementType.TEMPERATURE, 1.0) {
        @Override
        public double toBase(double value) {
            return (value - 32) * 5 / 9; // F -> C
        }

        @Override
        public double fromBase(double value) {
            return (value * 9 / 5) + 32; // C -> F
        }
    };

    private final MeasurementType type;
    private final double toBaseFactor;

    Unit(MeasurementType type, double toBaseFactor) {
        this.type = type;
        this.toBaseFactor = toBaseFactor;
    }

    public MeasurementType getType() {
        return type;
    }

    public double toBase(double value) {
        return value * toBaseFactor;
    }

    public double fromBase(double value) {
        return value / toBaseFactor;
    }
}