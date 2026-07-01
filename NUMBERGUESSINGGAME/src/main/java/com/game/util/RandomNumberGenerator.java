package com.game.util;

import com.game.model.DifficultyLevel;

import java.util.Random;

public class RandomNumberGenerator {
    private final Random random;

    public RandomNumberGenerator() {
        this(new Random());
    }

    public RandomNumberGenerator(Random random) {
        this.random = random;
    }

    public int generate(DifficultyLevel difficultyLevel) {
        int min = difficultyLevel.getMinimumNumber();
        int max = difficultyLevel.getMaximumNumber();
        return random.nextInt(max - min + 1) + min;
    }
}
