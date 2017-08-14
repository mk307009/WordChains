package pl.m4.kata.wordchains.dictionary;

import java.util.Optional;
import java.util.Set;

public interface Dictionary {
    Optional<String> findWord(String searchWord);

    Set<String> getWords();
}
