package com.blasco991.simpleElections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by blasco991 on 06/04/17.
 */
public class NameGenerator {

    private final Random random = ThreadLocalRandom.current();
    private List<String> vocals = new ArrayList<>();
    private List<String> startConsonants = new ArrayList<>();
    private List<String> endConsonants = new ArrayList<>();
    private List<String> nameInstructions = new ArrayList<>();

    public NameGenerator() {
        String demoVocals[] = {"a", "e", "i", "o", "u", "ei", "ai", "ou", "j",
                "ji", "y", "oi", "au", "oo"};

        String demoStartConsonants[] = {"b", "c", "d", "f", "g", "h", "k",
                "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "z",
                "ch", "bl", "br", "fl", "gl", "gr", "kl", "pr", "st", "sh",
                "th"};

        String demoEndConsonants[] = {"b", "d", "f", "g", "h", "k", "l", "m",
                "n", "p", "r", "s", "t", "v", "w", "z", "ch", "gh", "nn", "st",
                "sh", "th", "tt", "ss", "pf", "nt"};

        String nameInstructions[] = {"vd", "cvdvd", "cvd", "vdvd"};

        this.vocals.addAll(Arrays.asList(demoVocals));
        this.startConsonants.addAll(Arrays.asList(demoStartConsonants));
        this.endConsonants.addAll(Arrays.asList(demoEndConsonants));
        this.nameInstructions.addAll(Arrays.asList(nameInstructions));
    }

    /**
     * The names will look like this
     * (v=vocal,c=startConsonsonant,d=endConsonants): vd, cvdvd, cvd, vdvd
     *
     * @param vocals          pass something like {"a","e","ou",..}
     * @param startConsonants pass something like {"s","f","kl",..}
     * @param endConsonants   pass something like {"th","sh","f",..}
     */
    public NameGenerator(String[] vocals, String[] startConsonants,
                         String[] endConsonants) {
        this.vocals.addAll(Arrays.asList(vocals));
        this.startConsonants.addAll(Arrays.asList(startConsonants));
        this.endConsonants.addAll(Arrays.asList(endConsonants));
    }

    /**
     * see {@link NameGenerator#NameGenerator(String[], String[], String[])}
     *
     * @param vocals
     * @param startConsonants
     * @param endConsonants
     * @param nameInstructions Use only the following letters:
     *                         (v=vocal,c=startConsonsonant,d=endConsonants)! Pass something
     *                         like {"vd", "cvdvd", "cvd", "vdvd"}
     */
    public NameGenerator(String[] vocals, String[] startConsonants,
                         String[] endConsonants, String[] nameInstructions) {
        this(vocals, startConsonants, endConsonants);
        this.nameInstructions.addAll(Arrays.asList(nameInstructions));
    }

    public String getName() {
        return capitalize(getNameByInstructions(getRandomElementFrom(nameInstructions)));
    }

    private String getNameByInstructions(String nameInstructions) {
        StringBuilder name = new StringBuilder();
        int l = nameInstructions.length();

        for (int i = 0; i < l; i++) {
            char x = nameInstructions.charAt(0);
            switch (x) {
                case 'v':
                    name.append(getRandomElementFrom(vocals));
                    break;
                case 'c':
                    name.append(getRandomElementFrom(startConsonants));
                    break;
                case 'd':
                    name.append(getRandomElementFrom(endConsonants));
                    break;
            }
            nameInstructions = nameInstructions.substring(1);
        }
        return name.toString();
    }

    private String capitalize(String name) {
        return Character.toString(name.charAt(0)).toUpperCase() + name.substring(1);
    }

    private String getRandomElementFrom(List v) {
        return v.get(random.nextInt(v.size() - 1)).toString();
    }
}
