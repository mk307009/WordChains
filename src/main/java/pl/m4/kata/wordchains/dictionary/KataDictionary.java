package pl.m4.kata.wordchains.dictionary;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KataDictionary implements Dictionary {
    private static final String FILE_PATH = "resources/wordlist.txt";
    private Set<String> words;
    private int wordLength;

    public KataDictionary(int wordLength) throws DictionaryException {
        this.wordLength = wordLength;
        loadWords();
    }

    @Override
    public Optional<String> findWord(String searchWord) {
        return words.parallelStream()
                .filter(s -> s.equals(searchWord))
                .findFirst();
    }

    @Override
    public Set<String> getWords() {
        return words;
    }

    private void loadWords() throws DictionaryException {
        try (Stream<String> stream = Files.lines(
                Paths.get(FILE_PATH), StandardCharsets.ISO_8859_1)) {
            words = stream.parallel()
                    .filter(word -> word.length() == wordLength)
                    .map(String::toLowerCase)
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new DictionaryException("Can not read file: " + FILE_PATH, e);
        }
    }
}
