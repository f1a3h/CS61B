public class GuitarHero {
    private static final double CONCERT_A = 440.0;
    private static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static final int TOTAL_NOTES = 37;

    private static void initGuitarString(synthesizer.GuitarString[] gs) {
        for (int i = 0; i < TOTAL_NOTES; ++i) {
            double freq = CONCERT_A * Math.pow(2, ((double) (i - 24)) / 12.0);
            gs[i] = new synthesizer.GuitarString(freq);
        }
    }

    public static void main(String[] args) {
        synthesizer.GuitarString[] gs = new synthesizer.GuitarString[TOTAL_NOTES];
        initGuitarString(gs);

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = KEYBOARD.indexOf(key);
                if (index < 0) {
                    System.out.println("Invalid key: " + key);
                    continue;
                }
                gs[index].pluck();
            }

            double sample = 0.0;
            for (int i = 0; i < TOTAL_NOTES; ++i) {
                sample += gs[i].sample();
            }

            StdAudio.play(sample);

            for (int i = 0; i < TOTAL_NOTES; ++i) {
                gs[i].tic();
            }
        }
    }
}
