public class GuitarHero {
    private static final double CONCERT_A = 440.0;
    private static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static final int totalNotes = 37;

    private static void initGuitarString(synthesizer.GuitarString[] gs) {
        for (int i = 0; i < totalNotes; ++i) {
            double freq = CONCERT_A * Math.pow(2, ((double)(i - 24)) / 12.0);
            gs[i] = new synthesizer.GuitarString(freq);
        }
    }

    public static void main(String[] args) {
        synthesizer.GuitarString[] gs = new synthesizer.GuitarString[totalNotes];
        initGuitarString(gs);

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index < 0) {
                    System.out.println("Invalid key: " + key);
                    continue;
                }
                gs[index].pluck();
            }

            double sample = 0.0;
            for (int i = 0; i < totalNotes; ++i) {
                sample += gs[i].sample();
            }

            StdAudio.play(sample);

            for (int i = 0 ; i < totalNotes; ++i) {
                gs[i].tic();
            }
        }
    }
}
