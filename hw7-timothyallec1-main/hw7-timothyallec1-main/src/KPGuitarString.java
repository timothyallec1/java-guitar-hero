import java.util.*;

/**

 * @Author Timothy Allec
 * @Date November 29, 2022
 */

//this class mimics a vibrating guitar string
public class KPGuitarString implements GuitarString{
    private Queue<Double> ringBuffer;
    public static final double ENERGY_DECAY_FACTOR = 0.996;
    private int sampleNum;

    /**
     * TODO: Constructor: Implement your code here
     * change comments
     */

    //creates guitar string for the given frequency
    public KPGuitarString(double frequency){

        sampleNum = Math.round((float)(StdAudio.SAMPLE_RATE/frequency));

        if( frequency <= 0 || sampleNum < 2 ){
            throw new IllegalArgumentException();
        }

        ringBuffer = new LinkedList<>();
        for( int i = 0; i < sampleNum; i++){
            ringBuffer.add(0.0);

        }
    }

    /* constructs a guitar string and stores contents of the ring buffer into an array
    if array has less than two elements, throws an exception
    */

    public KPGuitarString(double[] init){
        sampleNum = init.length;

        if(sampleNum < 2) {
            throw new IllegalArgumentException();
        }

        ringBuffer = new LinkedList<>();

        for(Double values: init){
            ringBuffer.add(values);
        }

        System.out.println(ringBuffer);
    }

    //replaces the elements in the ringBuffer with random values
    @Override
    public void pluck(){
        for(int i = 0; i < sampleNum; i++){
            ringBuffer.add(Math.random() - 0.5);
            ringBuffer.remove();
        }
    }

    /* this method removes front of the ring buffer and adds

    */
    @Override
    public void tic(){
        double first = ringBuffer.remove();
        double second = ringBuffer.peek();
        ringBuffer.add(ENERGY_DECAY_FACTOR * 0.5 * (first + second));
    }

    @Override
    public double sample(){
        return -1;
    }
}
