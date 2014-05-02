package com.company.Chainables;

/*
 * Created by Garrett on 4/21/14.
 */
import java.util.ArrayList;

public class Volume extends Chainable {

    // level of the volume
    public double vol;

    // tolerance for comparison
    private final double tolerance = 1e-6;

    // quantization step size
    private final double step_size = 0.02;

    // upper and lower bounds of volume range
    private static final double upperBound = 1.0;
    private static final double lowerBound = 0.0;

    // Java MIDI library's double range of velocities
    private static final double velocity_range = 127.0;

    private int midi_velocity;

    // Default constructor (for rests)
    public Volume()
    {
        vol = 0.0;
    }

    // Standard constructor (for notes w/ input velocities)
    public Volume(int start_velocity, int end_velocity)
    {
        // no end velocity -- map just starting velocity
        if (end_velocity == 0)
        {
            // Map 0.0 - 127.0 to 0.0 to 1.0
            vol = (upperBound - lowerBound) * (start_velocity / velocity_range);

            // set midi velocity to input (used for decoding afterward)
            midi_velocity = start_velocity;
        }
        else
        {
            System.out.println("REST");
            // add algorithm to average velocities if need be
        }
        // linearly map velocity to volume
    }

    public double getVol()
    {
        return vol;
    }

    @Override
    public String toString() { return String.format("%1$,.2f", vol); }

    // return class name
    public static String classToString() { return "Volume"; }

    // TODO
    public int compareTo(Chainable v)
    {
        double diff = vol - ((Volume) v).getVol();

        if (Math.abs(diff) < tolerance) return 0;

        else if (diff < 0) return -1;

        else return 1;
    }

    // return velocity
    public int getMidi_velocity ()
    {
        return midi_velocity;
    }

    // method to round given step size
    // rounds up to the nearest step
    public void round()
    {
        vol = vol - (vol % step_size) + step_size;
    }
}
