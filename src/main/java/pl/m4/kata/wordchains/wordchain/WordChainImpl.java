package pl.m4.kata.wordchains.wordchain;

import pl.m4.kata.wordchains.dictionary.Dictionary;

import java.util.*;
import java.util.stream.Collectors;

public class WordChainImpl implements WordChain {
    private String startWord;
    private String endWord;
    private Dictionary dictionary;
    private List<Node> nodes;

    public WordChainImpl(Dictionary dictionary, String startWord, String endWord) {
        this.startWord = startWord;
        this.endWord = endWord;
        this.dictionary = dictionary;
        this.nodes = readAndMapWordsToNodes();
    }

    @Override
    public List<String> findWordsPath() throws WordChainException {
        Optional<Node> opEndNode = findEndNodeInTree();
        Node endNode = opEndNode.orElseThrow(
                () -> new WordChainException("Can not found path from: " + startWord + " to: " + endWord));
        return generatePathToRoot(endNode);
    }

    private List<String> generatePathToRoot(Node node) {
        List<String> path = new LinkedList<>();
        path.add(node.getName());
        Node parent = node.getParent();
        while (parent != null) {
            path.add(0, parent.getName());
            parent = parent.getParent();
        }
        return path;
    }

    /**
     * Function to generate tree of nodes(words) and return searching node(word).
     *
     * @return Searching end Node, or if not found return root Node.
     */
    private Optional<Node> findEndNodeInTree() {
        Optional<Node> endNode;
        Queue<Node> queue = new LinkedList<>();

        Node rootNode = new Node(startWord);
        rootNode.setVisited(true);
        queue.add(rootNode);

        while (!queue.isEmpty()) {
            endNode = Optional.of(queue.peek());
            if (endNode.isPresent() && endNode.get().getName().equals(endWord))
                return endNode;

            Node parent = queue.poll();
            List<Node> nextChildNodes = findChildNodes(parent);
            for (Node nextChild : nextChildNodes) {
                if (!nextChild.isVisited()) {
                    nextChild.setVisited(true);
                    nextChild.setParent(parent);
                    queue.add(nextChild);
                }
            }
        }
        return Optional.empty();
    }

    private List<Node> findChildNodes(Node parent) {
        List<Node> result = new ArrayList<>();

        for (Node word : nodes) {
            if (isOneLetterDiff(parent.getName(), word.getName())) {
                result.add(word);
            }
        }
        return result;
    }

    private boolean isOneLetterDiff(String startWord, String endWord) {
        int count = 0;

        for (int n = 0; n < startWord.length(); n++) {
            if (startWord.charAt(n) != endWord.charAt(n))
                count++;
        }
        return count == 1;
    }

    private List<Node> readAndMapWordsToNodes() {
        return dictionary.getWords()
                .parallelStream()
                .map(Node::new)
                .collect(Collectors.toList());
    }
}
